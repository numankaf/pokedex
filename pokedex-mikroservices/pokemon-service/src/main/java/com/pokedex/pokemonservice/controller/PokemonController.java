package com.pokedex.pokemonservice.controller;

import com.pokedex.pokemonservice.dto.PokemonDetailDto;
import com.pokedex.pokemonservice.dto.PokemonSearchDto;
import com.pokedex.pokemonservice.service.PokemonService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("pokemon")
public class PokemonController {
    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pokemonService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody PokemonDetailDto dto) {
        return ResponseEntity.ok(pokemonService.createPokemon(dto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PokemonDetailDto dto) {
        return ResponseEntity.ok(pokemonService.updatePokemon(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        pokemonService.deletePokemon(id);
        return ResponseEntity.ok(Map.of("message", "Successfully deleted pokemon with id: " + id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(pokemonService.findAll());
    }

    @GetMapping()
    public ResponseEntity<?> findAllAsPageable(Pageable pageable) {
        return ResponseEntity.ok(pokemonService.findAll(pageable));
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody PokemonSearchDto dto, Pageable pageable) {
        return ResponseEntity.ok(pokemonService.search(dto, pageable));
    }

    @GetMapping("/catchlist")
    public ResponseEntity<?> getCatchListPokemonsCurrentUser(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(pokemonService.getCatchListPokemonsCurrentUser(token));
    }

    @GetMapping("/wishlist")
    public ResponseEntity<?> getWishListPokemonsCurrentUser(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(pokemonService.getWishListPokemonsCurrentUser(token));
    }

    @PostMapping("/catchlist/{id}")
    public ResponseEntity<?> addToCatchList(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        pokemonService.addToCatchList(id,token);
        return ResponseEntity.ok(Map.of("message", "Successfully added to catch list with pokemon id : "+ id));
    }

    @DeleteMapping("/catchlist/{id}")
    public ResponseEntity<?> deleteFromCatchList(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        pokemonService.removeFromCatchList(id,token);
        return ResponseEntity.ok(Map.of("message", "Successfully removed to catch list with pokemon id : "+ id));
    }

    @PostMapping("/wishlist/{id}")
    public ResponseEntity<?> addToWishList(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        pokemonService.addToWishList(id, token);
        return ResponseEntity.ok(Map.of("message", "Successfully added to wish list with pokemon id : "+ id));
    }

    @DeleteMapping("/wishlist/{id}")
    public ResponseEntity<?> deleteFromWishList(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        pokemonService.removeFromWishList(id, token);
        return ResponseEntity.ok(Map.of("message", "Successfully removed to wish list with pokemon id : "+ id));
    }



}
