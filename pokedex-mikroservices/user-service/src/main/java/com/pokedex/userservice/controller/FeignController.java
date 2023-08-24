package com.pokedex.userservice.controller;

import com.pokedex.userservice.dto.RegisterRequestDto;
import com.pokedex.userservice.service.FeignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feign/users")
public class FeignController {

    private final FeignService feignService;

    public FeignController(FeignService feignService) {
        this.feignService = feignService;
    }

    @PostMapping("/catchlist/{userId}/{pokemonId}")
    public ResponseEntity<String> addToCatchList(@PathVariable  Long userId, @PathVariable Long pokemonId, @RequestHeader("Authorization") String token){
        feignService.addToCatchList(userId, pokemonId);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/catchlist/{userId}/{pokemonId}")
    public ResponseEntity<String> removeFromCatchList(@PathVariable  Long userId, @PathVariable Long pokemonId, @RequestHeader("Authorization") String token){
        feignService.removeFromCatchList(userId, pokemonId);
        return ResponseEntity.ok("Success");
    }


    @PostMapping("/wishlist/{userId}/{pokemonId}")
    public ResponseEntity<String> addToWishList(@PathVariable  Long userId, @PathVariable Long pokemonId, @RequestHeader("Authorization") String token){
        feignService.addToWishList(userId, pokemonId);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/wishlist/{userId}/{pokemonId}")
    public ResponseEntity<String> removeFromWishList(@PathVariable  Long userId, @PathVariable Long pokemonId, @RequestHeader("Authorization") String token){
        feignService.removeFromWishList(userId, pokemonId);
        return ResponseEntity.ok("Success");
    }


    @PostMapping("/pokemon")
    public ResponseEntity<String> addPokemon(@RequestHeader("Authorization") String token){
        feignService.addPokemonId();
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/pokemon/{id}")
    public ResponseEntity<String> deletePokemonId(@PathVariable  Long id, @RequestHeader("Authorization") String token){
        feignService.deletePokemonId(id);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto dto, @RequestHeader("Authorization") String token){
        feignService.createUser(dto,token);
        return ResponseEntity.ok("Success");
    }

}
