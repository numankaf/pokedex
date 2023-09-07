package com.pokedex.service;

import com.pokedex.dto.user.UserCreateRequestDto;
import com.pokedex.dto.user.UserDetailDto;
import com.pokedex.dto.user.UserListDto;
import com.pokedex.dto.user.UserUpdateRequestDto;
import com.pokedex.entity.CatchList;
import com.pokedex.entity.User;
import com.pokedex.entity.WishList;
import com.pokedex.exception.PokedexDatabaseException;
import com.pokedex.repository.CatchListRepository;
import com.pokedex.repository.UserRepository;
import com.pokedex.repository.WishListRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    private final CatchListRepository catchListRepository;
    private final WishListRepository wishListRepository;

    private static final Logger logger= LoggerFactory.getLogger(UserService.class);
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, CatchListRepository catchListRepository, WishListRepository wishListRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.catchListRepository = catchListRepository;
        this.wishListRepository = wishListRepository;
    }


    public UserDetailDto createUser(UserCreateRequestDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new PokedexDatabaseException("This username already exists! Try another one");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new PokedexDatabaseException("This email already exists! Try another one");
        }
        User user = modelMapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setActive(true);
        user.setThumbnail("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_640.png");
        WishList wishList = new WishList();
        wishListRepository.save(wishList);
        user.setWishList(wishList);
        CatchList catchList = new CatchList();
        catchListRepository.save(catchList);
        user.setCatchList(catchList);
        userRepository.save(user);
        UserDetailDto responseDto = modelMapper.map(user, UserDetailDto.class);
        return responseDto;
    }

    @CachePut(cacheNames = "users", key = "#id")
    public UserDetailDto updateUser(Long id, UserUpdateRequestDto dto) {
        logger.info("UPDATE user by id : " + id);
        User user = userRepository.findById(id).orElseThrow(() -> new PokedexDatabaseException("User not found with id :" + id));
        modelMapper.map(dto, user);
        userRepository.save(user);
        UserDetailDto responseDto = modelMapper.map(user, UserDetailDto.class);
        return responseDto;
    }

    @Cacheable(cacheNames = "users", key = "#id")
    public UserDetailDto getUserById(Long id) {
        logger.info("GET user by id : " + id);
        User user = userRepository.findById(id).orElseThrow(() -> new PokedexDatabaseException("User not found with id :" + id));
        UserDetailDto dto = modelMapper.map(user, UserDetailDto.class);
        return dto;
    }

    @CacheEvict(cacheNames = "users", key = "#id")
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
