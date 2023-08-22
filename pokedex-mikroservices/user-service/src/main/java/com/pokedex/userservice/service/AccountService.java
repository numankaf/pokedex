package com.pokedex.userservice.service;

import com.pokedex.userservice.config.JwtTokenProvider;
import com.pokedex.userservice.dto.AccountDetailDto;
import com.pokedex.userservice.dto.AccountTopbarDto;
import com.pokedex.userservice.dto.ChangePasswordDto;
import com.pokedex.userservice.dto.UserUpdateRequestDto;
import com.pokedex.userservice.entity.User;
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

    public AccountService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    public AccountDetailDto getDetail(String token){
        Long id = tokenProvider.getUserIdFromToken(token.substring(7));
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found with id : "+id));
        AccountDetailDto detailDto = modelMapper.map(user, AccountDetailDto.class);
        return detailDto;
    }
    public AccountDetailDto update(UserUpdateRequestDto dto, String token){
        Long id = tokenProvider.getUserIdFromToken(token.substring(7));
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found with id : "+id));
        modelMapper.map(dto, user);
        userRepository.save(user);
        AccountDetailDto responseDto = modelMapper.map(user,AccountDetailDto.class);
        return responseDto;
    }

    public void changePassword(ChangePasswordDto dto, String token){
        Long id = tokenProvider.getUserIdFromToken(token.substring(7));
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found with id : "+id));
        if(!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())){
            throw new RuntimeException("You entered your current password wrong! Try again");
        }
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);
    }

    public AccountTopbarDto getDetailForTopbar(String token){
        Long id = tokenProvider.getUserIdFromToken(token.substring(7));
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found with id : "+id));
        return modelMapper.map(user, AccountTopbarDto.class);
    }

}
