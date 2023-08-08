package com.pokedex.service;

import com.pokedex.dto.account.AccountDetailDto;
import com.pokedex.dto.account.ChangePasswordDto;
import com.pokedex.dto.auth.AccountTopbarDto;
import com.pokedex.dto.user.UserUpdateRequestDto;
import com.pokedex.entity.User;
import com.pokedex.exception.PokedexDatabaseException;
import com.pokedex.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public AccountService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public AccountDetailDto getDetail(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        AccountDetailDto detailDto = modelMapper.map(user, AccountDetailDto.class);
        return detailDto;
    }

    public AccountDetailDto update(UserUpdateRequestDto dto){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setAbout(dto.getAbout());
        user.setThumbnail(dto.getThumbnail());
        userRepository.save(user);
        AccountDetailDto responseDto = modelMapper.map(user,AccountDetailDto.class);
        return responseDto;
    }

    public void changePassword(ChangePasswordDto dto){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        if(!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())){
            throw new PokedexDatabaseException("You entered your current password wrong! Try again");
        }
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);
    }

    public AccountTopbarDto getDetailForTopbar(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        return modelMapper.map(user, AccountTopbarDto.class);
    }

}
