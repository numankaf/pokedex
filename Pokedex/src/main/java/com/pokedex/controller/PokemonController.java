package com.pokedex.controller;

import com.pokedex.dto.pokemon.PokemonDetailDto;
import com.pokedex.dto.pokemon.PokemonSearchDto;
import com.pokedex.repository.PokemonDao;
import com.pokedex.service.PokemonService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {
    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pokemonService.getById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody PokemonDetailDto dto) {
        return ResponseEntity.ok(pokemonService.createPokemon(dto));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PokemonDetailDto dto) {
        return ResponseEntity.ok(pokemonService.updatePokemon(id, dto));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        pokemonService.deletePokemon(id);
        return ResponseEntity.ok("Successfully deleted pokemon with id: " + id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(pokemonService.findAll());
    }

    @GetMapping()
    public ResponseEntity<?> findAllAsPageable(Pageable pageable){
        return ResponseEntity.ok(pokemonService.findAll(pageable));
    }

    @GetMapping("/search")
    public  ResponseEntity<?> search(@RequestBody PokemonSearchDto dto, Pageable pageable){
        return ResponseEntity.ok(pokemonService.search(dto, pageable));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/catch/{id}")
    public  ResponseEntity<?> getUsersWhoCatched(@PathVariable Long id){
        return ResponseEntity.ok(pokemonService.getUsersWhoCatch(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/wish/{id}")
    public  ResponseEntity<?> getUsersWhoAddedWishList(@PathVariable Long id){
        return ResponseEntity.ok(pokemonService.getUsersWhoAddedWishList(id));
    }


}
