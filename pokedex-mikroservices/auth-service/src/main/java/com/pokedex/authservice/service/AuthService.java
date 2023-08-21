package com.pokedex.authservice.service;

import com.pokedex.authservice.auth.JwtTokenProvider;
import com.pokedex.authservice.auth.UserDetailsImpl;
import com.pokedex.authservice.dto.LoginRequestDto;
import com.pokedex.authservice.dto.LoginResponseDto;
import com.pokedex.authservice.dto.RegisterRequestDto;
import com.pokedex.authservice.entity.User;
import com.pokedex.authservice.enums.UserRole;
import com.pokedex.authservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public AuthService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
                       JwtTokenProvider tokenProvider, UserRepository userRepository, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public LoginResponseDto login(LoginRequestDto dto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication auth = authenticationManager.authenticate(token);
        String jwtToken = tokenProvider.generateToken(auth);
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setUsername(((UserDetailsImpl) auth.getPrincipal()).getUsername());
        User user = userRepository.findByUsername(((UserDetailsImpl) auth.getPrincipal()).getUsername());
        loginResponseDto.setToken("Bearer " + jwtToken);
        loginResponseDto.setRole(user.getRole());
        return loginResponseDto;
    }

    public Map<String, String> register(RegisterRequestDto dto) {
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
        user.setRole(UserRole.TRAINER);
        userRepository.save(user);
        return Map.of("message", "Success");
    }


}
