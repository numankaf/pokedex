package com.pokedex.service;


import com.pokedex.auth.JwtTokenProvider;
import com.pokedex.auth.UserDetailsImpl;
import com.pokedex.dto.auth.ForgotPasswordDto;
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
    private final EmailSenderService emailSenderService;

    public AuthService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
                       JwtTokenProvider tokenProvider, UserRepository userRepository, WishListRepository wishListRepository,
                       CatchListRepository catchListRepository, ModelMapper modelMapper, EmailSenderService emailSenderService) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.wishListRepository = wishListRepository;
        this.catchListRepository = catchListRepository;
        this.modelMapper = modelMapper;
        this.emailSenderService = emailSenderService;
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
            throw new PokedexDatabaseException("This username already exists! Try another one");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new PokedexDatabaseException("This email already exists! Try another one");
        }

        User user = modelMapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setActive(true);
        user.setThumbnail("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_640.png");
        user.setRole(UserRole.TRAINER);
        WishList wishList = new WishList();
        wishListRepository.save(wishList);
        user.setWishList(wishList);
        CatchList catchList = new CatchList();
        catchListRepository.save(catchList);
        user.setCatchList(catchList);
        userRepository.save(user);
        return Map.of("message", "Success");
    }

    public Map<String, String> forgotPassword(ForgotPasswordDto dto) {
        User user = userRepository.findByEmail(dto.getEmail());
        if (user == null) {
            throw new PokedexDatabaseException("User not found with email :" + dto.getEmail());
        }
        String newPassword = emailSenderService.generatePassword();
        String context = "Dear " + user.getName() + ",\n\n" +
                "Your password has been reset for security reasons. \nYour new password is: " + newPassword +
                "\n\nWe recommend changing your password as soon as possible to ensure the safety of your account. " +
                "Please create a strong and unique password consisting of a combination of uppercase and lowercase letters, numbers,"
                +
                " and special characters. If you did not request a password reset, please contact our customer support team immediately. "
                +
                "\n\nThank you for choosing our service. \n\nBest regards,\n\nPokedex ";
        emailSenderService.sendEmail(user.getEmail(), "Your New Pokedex Account Password",
                context);

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return Map.of("message", "Success");

    }
}
