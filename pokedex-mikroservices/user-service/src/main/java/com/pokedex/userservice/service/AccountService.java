package com.pokedex.userservice.service;

import com.pokedex.userservice.config.JwtTokenProvider;
import com.pokedex.userservice.dto.AccountDetailDto;
import com.pokedex.userservice.dto.AccountTopbarDto;
import com.pokedex.userservice.dto.ChangePasswordDto;
import com.pokedex.userservice.dto.UserUpdateRequestDto;
import com.pokedex.userservice.entity.User;
import com.pokedex.userservice.feign.AuthFeignClient;
import com.pokedex.userservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AuthFeignClient feignClient;

    public AccountService(UserRepository userRepository, ModelMapper modelMapper,
                          PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider, AuthFeignClient feignClient) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.feignClient = feignClient;
    }

    public AccountDetailDto getDetail(String token) {
        Long id = tokenProvider.getUserIdFromToken(token.substring(7));
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id : " + id));
        AccountDetailDto detailDto = modelMapper.map(user, AccountDetailDto.class);
        return detailDto;
    }

    public AccountDetailDto update(UserUpdateRequestDto dto, String token) {
        Long id = tokenProvider.getUserIdFromToken(token.substring(7));
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id : " + id));
        modelMapper.map(dto, user);
        userRepository.save(user);
        AccountDetailDto responseDto = modelMapper.map(user, AccountDetailDto.class);
        return responseDto;
    }

    public void changePassword(ChangePasswordDto dto, String token) {
        Long id = tokenProvider.getUserIdFromToken(token.substring(7));
        feignClient.changePassword(id, dto, token);
    }

    public AccountTopbarDto getDetailForTopbar(String token) {
        Long id = tokenProvider.getUserIdFromToken(token.substring(7));
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id : " + id));
        return modelMapper.map(user, AccountTopbarDto.class);
    }

}
