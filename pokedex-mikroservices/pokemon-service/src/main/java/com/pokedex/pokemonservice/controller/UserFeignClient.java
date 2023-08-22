package com.pokedex.pokemonservice.controller;

import com.pokedex.pokemonservice.service.FeignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feign/pokemon")
public class UserFeignClient {

    private final FeignService feignService;

    public UserFeignClient(FeignService feignService) {
        this.feignService = feignService;
    }

    @PostMapping()
    public ResponseEntity<?> addUserId() {
        feignService.addUserId();
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserId(@PathVariable Long id) {
        feignService.removeUserId(id);
        return ResponseEntity.ok("Success");
    }


}
