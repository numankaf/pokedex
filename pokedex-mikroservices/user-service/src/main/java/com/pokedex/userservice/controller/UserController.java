package com.pokedex.userservice.controller;

import com.pokedex.userservice.dto.UserCreateRequestDto;
import com.pokedex.userservice.dto.UserDetailDto;
import com.pokedex.userservice.dto.UserListDto;
import com.pokedex.userservice.dto.UserUpdateRequestDto;
import com.pokedex.userservice.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        UserDetailDto userDetailDto = userService.getUserById(id);
        return ResponseEntity.ok(userDetailDto);
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserCreateRequestDto dto){
        UserDetailDto createdUser = userService.createUser(dto);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequestDto dto){
        UserDetailDto updateUser = userService.updateUser(id, dto);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok(Map.of("messages", "User deleted with id: "+id));
    }

    @GetMapping()
    public ResponseEntity<?> findAllPageable(Pageable pageable){
        Page<UserListDto> dtos = userService.findAll(pageable);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        List<UserListDto> dtos = userService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody UserListDto searchDto, Pageable pageable){
        Page<UserListDto> dtos = userService.search(searchDto, pageable);
        return ResponseEntity.ok(dtos);
    }
}
