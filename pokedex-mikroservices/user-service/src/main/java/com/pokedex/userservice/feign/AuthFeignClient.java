package com.pokedex.userservice.feign;

import com.pokedex.userservice.dto.ChangePasswordDto;
import com.pokedex.userservice.dto.UserCreateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "auth-service", url = "http://localhost:8080", path = "/pokedex/feign/auth")
public interface AuthFeignClient {
    @PostMapping("/create")
    ResponseEntity<String> createUser(@RequestBody UserCreateRequestDto dto , @RequestHeader("Authorization") String token);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteUser(@PathVariable Long id, @RequestHeader("Authorization") String token);

    @PostMapping("/change-password/{id}")
    ResponseEntity<String> changePassword(@PathVariable Long id, @RequestBody ChangePasswordDto dto , @RequestHeader("Authorization") String token);
}
