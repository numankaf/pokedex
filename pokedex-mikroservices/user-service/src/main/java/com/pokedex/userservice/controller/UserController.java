package com.pokedex.userservice.controller;

import com.pokedex.userservice.dto.*;
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
    public ResponseEntity<UserDetailDto> getById(@PathVariable Long id){
        UserDetailDto userDetailDto = userService.getUserById(id);
        return ResponseEntity.ok(userDetailDto);
    }

    @PostMapping()
    public ResponseEntity<UserDetailDto> createUser(@RequestBody UserCreateRequestDto dto, @RequestHeader("Authorization") String token){
        UserDetailDto createdUser = userService.createUser(dto, token);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserDetailDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequestDto dto){
        UserDetailDto updateUser = userService.updateUser(id, dto);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id, @RequestHeader("Authorization") String token){
        userService.deleteUser(id, token);
        return ResponseEntity.ok(Map.of("messages", "User deleted with id: "+id));
    }

    @GetMapping()
    public ResponseEntity<Page<UserListDto>> findAllPageable(Pageable pageable){
        Page<UserListDto> dtos = userService.findAll(pageable);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserListDto>> findAll(){
        List<UserListDto> dtos = userService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<UserListDto>> search(@RequestBody UserListDto searchDto, Pageable pageable){
        Page<UserListDto> dtos = userService.search(searchDto, pageable);
        return ResponseEntity.ok(dtos);
    }


    @GetMapping("/catched/{id}")
    public ResponseEntity<List<UserListDto>> getUsersWhoCatched(@PathVariable Long id){
        List<UserListDto> dtos = userService.getUsersWhoCatched(id);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/wished/{id}")
    public ResponseEntity<List<UserListDto>> getUsersWhoWished(@PathVariable Long id){
        List<UserListDto> dtos = userService.getUsersWhoWished(id);
        return ResponseEntity.ok(dtos);
    }
}
