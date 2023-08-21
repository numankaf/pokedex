package com.pokedex.pokemonservice.repository;

import com.pokedex.pokemonservice.entity.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserIdRepository extends JpaRepository<UserId, Long> {
}
