package com.pokedex.pokemonservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", url = "http://localhost:8080", path = "/pokedex/feign/users")
public interface UserFeignClient {

    @PostMapping("/catchlist/{userId}/{pokemonId}")
    ResponseEntity<String> addToCatchList(@PathVariable Long userId, @PathVariable Long pokemonId, @RequestHeader("Authorization") String token);
    @DeleteMapping("/catchlist/{userId}/{pokemonId}")
    ResponseEntity<String> removeFromCatchList(@PathVariable  Long userId, @PathVariable Long pokemonId, @RequestHeader("Authorization") String token);

    @PostMapping("/wishlist/{userId}/{pokemonId}")
    ResponseEntity<String> addToWishList(@PathVariable  Long userId, @PathVariable Long pokemonId, @RequestHeader("Authorization") String token);

    @DeleteMapping("/wishlist/{userId}/{pokemonId}")
    ResponseEntity<String> removeFromWishList(@PathVariable  Long userId, @PathVariable Long pokemonId, @RequestHeader("Authorization") String token);

    @PostMapping("/pokemon")
    ResponseEntity<String> addPokemon(@RequestHeader("Authorization") String token);

    @DeleteMapping("/pokemon/{id}")
    ResponseEntity<String> deletePokemonId(@PathVariable  Long id, @RequestHeader("Authorization") String token);
}
