package com.pokedex.pokemonservice.controller;

import com.pokedex.pokemonservice.dto.PokemonDetailDto;
import com.pokedex.pokemonservice.dto.PokemonListDto;
import com.pokedex.pokemonservice.dto.PokemonSearchDto;
import com.pokedex.pokemonservice.service.PokemonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("pokemon")
public class PokemonController {
    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDetailDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pokemonService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<PokemonDetailDto> create(@RequestBody PokemonDetailDto dto , @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(pokemonService.createPokemon(dto, token));
    }

    @PostMapping("/{id}")
    public ResponseEntity<PokemonDetailDto> update(@PathVariable Long id, @RequestBody PokemonDetailDto dto) {
        return ResponseEntity.ok(pokemonService.updatePokemon(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id ,@RequestHeader("Authorization") String token) {
        pokemonService.deletePokemon(id, token);
        return ResponseEntity.ok(Map.of("message", "Successfully deleted pokemon with id: " + id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PokemonListDto>> findAll(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(pokemonService.findAll(token));
    }

    @GetMapping()
    public ResponseEntity<Page<PokemonListDto>> findAllAsPageable(Pageable pageable , @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(pokemonService.findAll(pageable,token));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<PokemonListDto>> search(@RequestBody PokemonSearchDto dto, Pageable pageable, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(pokemonService.search(dto, pageable, token));
    }

    @GetMapping("/catchlist")
    public ResponseEntity<Page<PokemonListDto>> getCatchListPokemonsCurrentUser(@RequestHeader("Authorization") String token, Pageable pageable) {
        return ResponseEntity.ok(pokemonService.getCatchListPokemonsCurrentUser(token, pageable));
    }

    @GetMapping("/wishlist")
    public ResponseEntity<Page<PokemonListDto>> getWishListPokemonsCurrentUser(@RequestHeader("Authorization") String token, Pageable pageable) {
        return ResponseEntity.ok(pokemonService.getWishListPokemonsCurrentUser(token, pageable));
    }

    @PostMapping("/catchlist/{id}")
    public ResponseEntity<Map<String, String>> addToCatchList(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        pokemonService.addToCatchList(id,token);
        return ResponseEntity.ok(Map.of("message", "Successfully added to catch list with pokemon id : "+ id));
    }

    @DeleteMapping("/catchlist/{id}")
    public ResponseEntity<Map<String, String>> deleteFromCatchList(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        pokemonService.removeFromCatchList(id,token);
        return ResponseEntity.ok(Map.of("message", "Successfully removed to catch list with pokemon id : "+ id));
    }

    @PostMapping("/wishlist/{id}")
    public ResponseEntity<Map<String, String>> addToWishList(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        pokemonService.addToWishList(id, token);
        return ResponseEntity.ok(Map.of("message", "Successfully added to wish list with pokemon id : "+ id));
    }

    @DeleteMapping("/wishlist/{id}")
    public ResponseEntity<Map<String, String>> deleteFromWishList(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        pokemonService.removeFromWishList(id, token);
        return ResponseEntity.ok(Map.of("message", "Successfully removed to wish list with pokemon id : "+ id));
    }



}
