package com.pokedex.service;


import com.pokedex.auth.JwtTokenProvider;
import com.pokedex.dto.auth.LoginRequestDto;
import com.pokedex.dto.auth.RegisterRequestDto;
import com.pokedex.entity.User;
import com.pokedex.enums.UserRole;
import com.pokedex.exception.PokedexDatabaseException;
import com.pokedex.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public AuthService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, UserRepository userRepository, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public String login(LoginRequestDto dto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication auth = authenticationManager.authenticate(token);
        String jwtToken = tokenProvider.generateToken(auth);
        return "Bearer " + jwtToken;
    }

    public String register(RegisterRequestDto dto) {
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
        return "Success";
    }
}
