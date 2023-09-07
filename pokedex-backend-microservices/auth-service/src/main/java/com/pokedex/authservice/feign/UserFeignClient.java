package com.pokedex.authservice.feign;

import com.pokedex.authservice.dto.RegisterRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", url = "http://localhost:8080", path = "/pokedex/feign/users")
public interface UserFeignClient {
    @PostMapping("register")
    ResponseEntity<String> register(@RequestBody RegisterRequestDto dto, @RequestHeader("Authorization") String token);
}
