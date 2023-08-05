package com.pokedex.service;

import com.pokedex.dto.user.UserCreateRequestDto;
import com.pokedex.dto.user.UserDetailDto;
import com.pokedex.dto.user.UserListDto;
import com.pokedex.dto.user.UserUpdateRequestDto;
import com.pokedex.entity.User;
import com.pokedex.exception.PokedexDatabaseException;
import com.pokedex.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }


    public UserDetailDto createUser(UserCreateRequestDto dto){
        if (userRepository.existsByUsername(dto.getUsername())){
            throw new PokedexDatabaseException("This username already exists! Try another one");
        }
        if (userRepository.existsByEmail(dto.getEmail())){
            throw new PokedexDatabaseException("This email already exists! Try another one");
        }
        User user = modelMapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setActive(true);
        userRepository.save(user);
        UserDetailDto responseDto = modelMapper.map(user, UserDetailDto.class);
        return responseDto;
    }

    public UserDetailDto updateUser(Long id, UserUpdateRequestDto dto){
        User user = userRepository.findById(id).orElseThrow(()->new PokedexDatabaseException("User not found with id :" +id));
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setThumbnail(dto.getThumbnail());
        user.setAbout(dto.getAbout());
        userRepository.save(user);
        UserDetailDto responseDto = modelMapper.map(user, UserDetailDto.class);
        return responseDto;
    }

    public UserDetailDto getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new PokedexDatabaseException("User not found with id :" +id));
        UserDetailDto dto = modelMapper.map(user, UserDetailDto.class);
        return dto;
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public List<UserListDto> findAll(){
        List<User> users = userRepository.findAll();
        List<UserListDto> userListDtos = users.stream().map( u -> modelMapper.map(u, UserListDto.class)).collect(Collectors.toList());
        return userListDtos;
    }

    public Page<UserListDto> findAll(Pageable pageable){
        Page<User> users = userRepository.findAll(pageable);
        List<UserListDto> userListDtos = users.stream().map( u -> modelMapper.map(u, UserListDto.class)).collect(Collectors.toList());
        return new PageImpl<>(userListDtos, pageable, users.getTotalElements());
    }


    public Page<UserListDto> search(UserListDto dto, Pageable pageable){
        User exampleUser = new User();
        exampleUser.setUsername(dto.getUsername());
        exampleUser.setName(dto.getName());
        exampleUser.setSurname(dto.getSurname());
        exampleUser.setEmail(dto.getEmail());
        exampleUser.setRole(dto.getRole());
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnorePaths("isActive").withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<User> example = Example.of(exampleUser, matcher);
        Page<User> users = userRepository.findAll(example,pageable);
        List<UserListDto> userListDtos = users.stream().map( u -> modelMapper.map(u, UserListDto.class)).collect(Collectors.toList());
        return new PageImpl<>(userListDtos, pageable, users.getTotalElements());
    }
}
