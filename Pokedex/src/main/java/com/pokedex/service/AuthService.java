package com.pokedex.service;


import com.pokedex.auth.JwtTokenProvider;
import com.pokedex.auth.UserDetailsImpl;
import com.pokedex.dto.auth.LoginRequestDto;
import com.pokedex.dto.auth.LoginResponseDto;
import com.pokedex.dto.auth.RegisterRequestDto;
import com.pokedex.entity.CatchList;
import com.pokedex.entity.User;
import com.pokedex.entity.WishList;
import com.pokedex.enums.UserRole;
import com.pokedex.exception.PokedexDatabaseException;
import com.pokedex.repository.CatchListRepository;
import com.pokedex.repository.UserRepository;
import com.pokedex.repository.WishListRepository;
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
    private final WishListRepository wishListRepository;
    private final CatchListRepository catchListRepository;
    private final ModelMapper modelMapper;

    public AuthService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
                       JwtTokenProvider tokenProvider, UserRepository userRepository, WishListRepository wishListRepository,
                       CatchListRepository catchListRepository, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.wishListRepository = wishListRepository;
        this.catchListRepository = catchListRepository;
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
        return  loginResponseDto;
    }

    public Map<String, String> register(RegisterRequestDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new PokedexDatabaseException("This username already exists! Try another one");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new PokedexDatabaseException("This email already exists! Try another one");
        }

        User user = modelMapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setActive(true);
        user.setRole(UserRole.TRAINER);
        WishList wishList = new WishList();
        wishListRepository.save(wishList);
        user.setWishList(wishList);
        CatchList catchList = new CatchList();
        catchListRepository.save(catchList);
        user.setCatchList(catchList);
        userRepository.save(user);
        return Map.of("message","Success");
    }
}
