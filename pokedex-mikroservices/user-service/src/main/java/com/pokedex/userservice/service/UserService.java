package com.pokedex.userservice.service;

import com.pokedex.userservice.dto.UserCreateRequestDto;
import com.pokedex.userservice.dto.UserDetailDto;
import com.pokedex.userservice.dto.UserListDto;
import com.pokedex.userservice.dto.UserUpdateRequestDto;
import com.pokedex.userservice.entity.PokemonId;
import com.pokedex.userservice.entity.User;
import com.pokedex.userservice.exception.PokedexUserException;
import com.pokedex.userservice.feign.AuthFeignClient;
import com.pokedex.userservice.feign.PokemonFeignClient;
import com.pokedex.userservice.repository.PokemonIdRepository;
import com.pokedex.userservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new PokedexUserException("This username already exists! Try another one");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new PokedexUserException("This email already exists! Try another one");
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

        User user = userRepository.findById(id).orElseThrow(() -> new PokedexUserException("User not found with id :" + id));
        modelMapper.map(dto, user);
        userRepository.save(user);
        return modelMapper.map(user, UserDetailDto.class);
    }


    public UserDetailDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new PokedexUserException("User not found with id :" + id));
        return modelMapper.map(user, UserDetailDto.class);
    }


    public void deleteUser(Long id, String token) {
        User user = userRepository.findById(id).orElseThrow(()->new PokedexUserException("User not found with id"+id));
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
        return users.stream().map(u -> modelMapper.map(u, UserListDto.class)).toList();
    }

    public Page<UserListDto> findAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        List<UserListDto> userListDtos = users.stream().map(u -> modelMapper.map(u, UserListDto.class)).toList();
        return new PageImpl<>(userListDtos, pageable, users.getTotalElements());
    }


    public Page<UserListDto> search(UserListDto dto, Pageable pageable) {
        User exampleUser = new User();
        modelMapper.map(dto, exampleUser);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnorePaths("isActive").withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<User> example = Example.of(exampleUser, matcher);
        Page<User> users = userRepository.findAll(example, pageable);
        List<UserListDto> userListDtos = users.stream().map(u -> modelMapper.map(u, UserListDto.class)).toList();
        return new PageImpl<>(userListDtos, pageable, users.getTotalElements());
    }

    public List<UserListDto> getUsersWhoCatched(Long pokemonId){
        PokemonId pokemon = pokemonIdRepository.findById(pokemonId).orElseThrow(() -> new PokedexUserException("Pokemon not found with id : " + pokemonId));
        return pokemon.getCatchLists().stream().map(u -> modelMapper.map(u, UserListDto.class)).toList();
    }

    public List<UserListDto> getUsersWhoWished(Long pokemonId){
        PokemonId pokemon = pokemonIdRepository.findById(pokemonId).orElseThrow(() -> new PokedexUserException("Pokemon not found with id : " + pokemonId));
        return pokemon.getWishLists().stream().map(u -> modelMapper.map(u, UserListDto.class)).toList();
    }

}
