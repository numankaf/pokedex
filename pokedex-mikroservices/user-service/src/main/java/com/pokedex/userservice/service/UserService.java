package com.pokedex.userservice.service;

import com.pokedex.userservice.dto.UserCreateRequestDto;
import com.pokedex.userservice.dto.UserDetailDto;
import com.pokedex.userservice.dto.UserListDto;
import com.pokedex.userservice.dto.UserUpdateRequestDto;
import com.pokedex.userservice.entity.User;
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
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetailDto createUser(UserCreateRequestDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("This username already exists! Try another one");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("This email already exists! Try another one");
        }
        User user = modelMapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setIsActive(true);
        user.setThumbnail("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_640.png");
        userRepository.save(user);
        UserDetailDto responseDto = modelMapper.map(user, UserDetailDto.class);
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


    public void deleteUser(Long id) {
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
}
