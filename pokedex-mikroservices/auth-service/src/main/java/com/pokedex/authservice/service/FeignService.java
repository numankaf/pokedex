package com.pokedex.authservice.service;


import com.pokedex.authservice.dto.ChangePasswordDto;
import com.pokedex.authservice.dto.UserCreateRequestDto;
import com.pokedex.authservice.entity.User;
import com.pokedex.authservice.exception.PokedexAuthException;
import com.pokedex.authservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FeignService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public FeignService(PasswordEncoder passwordEncoder, UserRepository userRepository, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;

        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public void createUser(UserCreateRequestDto dto){
        User user = modelMapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setIsActive(true);
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public void changePassword(Long id, ChangePasswordDto dto){
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found with id : "+id));
        if(!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())){
            throw new PokedexAuthException("You entered your current password wrong! Try again");
        }
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);
    }

}
