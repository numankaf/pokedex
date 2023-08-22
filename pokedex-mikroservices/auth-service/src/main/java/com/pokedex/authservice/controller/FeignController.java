package com.pokedex.authservice.controller;

import com.pokedex.authservice.dto.ChangePasswordDto;
import com.pokedex.authservice.dto.UserCreateRequestDto;
import com.pokedex.authservice.service.FeignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feign/auth")
public class FeignController {
    private final FeignService feignService;

    public FeignController(FeignService feignService) {
        this.feignService = feignService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserCreateRequestDto dto , @RequestHeader("Authorization") String token){
        feignService.createUser(dto);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable  Long id, @RequestHeader("Authorization") String token){
        feignService.deleteUser(id);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/change-password/{id}")
    public ResponseEntity<String> changePassword(@PathVariable Long id, @RequestBody ChangePasswordDto dto ,@RequestHeader("Authorization") String token){
        feignService.changePassword(id, dto);
        return ResponseEntity.ok("Success");
    }

}
