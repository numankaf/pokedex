package com.pokedex.userservice.repository;

import com.pokedex.userservice.entity.PokemonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonIdRepository extends JpaRepository<PokemonId, Long> {

}
