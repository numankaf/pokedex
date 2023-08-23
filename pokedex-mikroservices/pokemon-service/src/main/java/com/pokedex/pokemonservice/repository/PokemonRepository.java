package com.pokedex.pokemonservice.repository;

import com.pokedex.pokemonservice.dto.PokemonListDto;
import com.pokedex.pokemonservice.entity.Pokemon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    @Query(value = "select u.catchLists from UserId u where u.id = :id")
    Page<Pokemon> getCatchListPokemons(Long id, Pageable pageable);

    @Query(value = "select u.wishLists from UserId u where u.id = :id")
    Page<Pokemon> getWishlistPokemons(Long id, Pageable pageable);
}
