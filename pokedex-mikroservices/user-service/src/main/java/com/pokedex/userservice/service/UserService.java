package com.pokedex.userservice.service;

import com.pokedex.userservice.config.JwtTokenProvider;
import com.pokedex.userservice.dto.UserCreateRequestDto;
import com.pokedex.userservice.dto.UserDetailDto;
import com.pokedex.userservice.dto.UserListDto;
import com.pokedex.userservice.dto.UserUpdateRequestDto;
import com.pokedex.userservice.entity.PokemonId;
import com.pokedex.userservice.entity.User;
import com.pokedex.userservice.feign.AuthFeignClient;
import com.pokedex.userservice.feign.PokemonFeignClient;
import com.pokedex.userservice.repository.PokemonIdRepository;
import com.pokedex.userservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PokemonIdRepository pokemonIdRepository;
    private final PokemonFeignClient pokemonFeignClient;
    private final AuthFeignClient authFeignClient;

    public UserService(UserRepository userRepository, ModelMapper modelMapper,
                       PokemonIdRepository pokemonIdRepository, PokemonFeignClient pokemonFeignClient,
                       AuthFeignClient authFeignClient) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.pokemonIdRepository = pokemonIdRepository;
        this.pokemonFeignClient = pokemonFeignClient;
        this.authFeignClient = authFeignClient;
    }

    public UserDetailDto createUser(UserCreateRequestDto dto, String token) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("This username already exists! Try another one");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("This email already exists! Try another one");
        }
        User user = modelMapper.map(dto, User.class);
        user.setIsActive(true);
        user.setThumbnail("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_640.png");
        userRepository.save(user);
        UserDetailDto responseDto = modelMapper.map(user, UserDetailDto.class);
        pokemonFeignClient.addUserId(token);
        authFeignClient.createUser(dto, token);
        return responseDto;
    }


    public UserDetailDto updateUser(Long id, UserUpdateRequestDto dto) {

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id :" + id));
        modelMapper.map(dto, user);
        userRepository.save(user);
        UserDetailDto responseDto = modelMapper.map(user, UserDetailDto.class);
        return responseDto;
    }


    public UserDetailDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id :" + id));
        UserDetailDto dto = modelMapper.map(user, UserDetailDto.class);
        return dto;
    }


    public void deleteUser(Long id, String token) {
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found with id"+id));
        for(PokemonId pokemonId: user.getCatchListIds()){
            pokemonId.getCatchLists().remove(pokemonId);
        }
        for(PokemonId pokemonId: user.getWishListIds()){
            pokemonId.getWishLists().remove(pokemonId);
        }
        pokemonFeignClient.deleteUserId(id, token);
        authFeignClient.deleteUser(id, token);
        userRepository.deleteById(id);
    }

    public List<UserListDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserListDto> userListDtos = users.stream().map(u -> modelMapper.map(u, UserListDto.class)).collect(Collectors.toList());
        return userListDtos;
    }

    public Page<UserListDto> findAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        List<UserListDto> userListDtos = users.stream().map(u -> modelMapper.map(u, UserListDto.class)).collect(Collectors.toList());
        return new PageImpl<>(userListDtos, pageable, users.getTotalElements());
    }


    public Page<UserListDto> search(UserListDto dto, Pageable pageable) {
        User exampleUser = new User();
        modelMapper.map(dto, exampleUser);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnorePaths("isActive").withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<User> example = Example.of(exampleUser, matcher);
        Page<User> users = userRepository.findAll(example, pageable);
        List<UserListDto> userListDtos = users.stream().map(u -> modelMapper.map(u, UserListDto.class)).collect(Collectors.toList());
        return new PageImpl<>(userListDtos, pageable, users.getTotalElements());
    }

    public List<UserListDto> getUsersWhoCatched(Long pokemonId){
        PokemonId pokemon = pokemonIdRepository.findById(pokemonId).orElseThrow(() -> new RuntimeException("Pokemon not found with id : " + pokemonId));
        List<UserListDto> dtos = pokemon.getCatchLists().stream().map(u -> modelMapper.map(u, UserListDto.class)).collect(Collectors.toList());
        return dtos;
    }

    public List<UserListDto> getUsersWhoWished(Long pokemonId){
        PokemonId pokemon = pokemonIdRepository.findById(pokemonId).orElseThrow(() -> new RuntimeException("Pokemon not found with id : " + pokemonId));
        List<UserListDto> dtos = pokemon.getWishLists().stream().map(u -> modelMapper.map(u, UserListDto.class)).collect(Collectors.toList());
        return dtos;
    }

}
