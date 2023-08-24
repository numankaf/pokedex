package com.pokedex.pokemonservice.service;

import com.pokedex.pokemonservice.entity.Pokemon;
import com.pokedex.pokemonservice.entity.UserId;
import com.pokedex.pokemonservice.exception.PokedexPokemonException;
import com.pokedex.pokemonservice.repository.UserIdRepository;
import org.springframework.stereotype.Service;

@Service
public class FeignService {
    private final UserIdRepository userIdRepository;

    public FeignService(UserIdRepository userIdRepository) {
        this.userIdRepository = userIdRepository;
    }

    public void addUserId() {
        UserId userId = new UserId();
        userIdRepository.save(userId);
    }

    public void removeUserId(Long id) {
        UserId userId = userIdRepository.findById(id).orElseThrow(() -> new PokedexPokemonException("UserId not found with id : "+id));
        for (Pokemon pokemon:userId.getCatchLists()){
            userId.getCatchLists().remove(pokemon);
        }
        for (Pokemon pokemon: userId.getWishLists()){
            userId.getWishLists().remove(pokemon);
        }
        userIdRepository.deleteById(id);
    }

}
