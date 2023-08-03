package com.pokedex.controller;

import com.pokedex.dto.auth.UserCreateRequestDto;
import com.pokedex.entity.User;
import com.pokedex.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody  UserCreateRequestDto dto){
        User createdUser = userService.createUser(dto);
        return ResponseEntity.ok("User created with username : "+ createdUser.getUsername());
    }

}
