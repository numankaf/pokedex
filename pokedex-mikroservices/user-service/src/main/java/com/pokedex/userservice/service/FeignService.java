package com.pokedex.userservice.service;

import com.pokedex.userservice.entity.PokemonId;
import com.pokedex.userservice.entity.User;
import com.pokedex.userservice.repository.PokemonIdRepository;
import com.pokedex.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FeignService {
    private final UserRepository userRepository;
    private final PokemonIdRepository pokemonIdRepository;

    public FeignService(UserRepository userRepository, PokemonIdRepository pokemonIdRepository) {
        this.userRepository = userRepository;
        this.pokemonIdRepository = pokemonIdRepository;
    }


    public void addPokemonId(){
        PokemonId pokemonId = new PokemonId();
        pokemonIdRepository.save(pokemonId);
    }

    public void deletePokemonId(Long id){
        PokemonId pokemon = pokemonIdRepository.findById(id).orElseThrow(()->new RuntimeException("PokemonId not found with id: "+id));
        for (User user:pokemon.getCatchLists()){
            pokemon.getCatchLists().remove(user);
        }
        for (User user:pokemon.getWishLists()){
            pokemon.getWishLists().remove(user);
        }
        pokemonIdRepository.deleteById(id);
    }

    public void addToCatchList(Long userId, Long pokemonId){
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found with id: "+userId));
        PokemonId pokemon = pokemonIdRepository.findById(pokemonId).orElseThrow(()->new RuntimeException("PokemonId not found with id: "+pokemonId));
        user.getCatchListIds().add(pokemon);
        userRepository.save(user);
    }

    public void removeFromCatchList(Long userId, Long pokemonId){
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found with id: "+userId));
        PokemonId pokemon = pokemonIdRepository.findById(pokemonId).orElseThrow(()->new RuntimeException("PokemonId not found with id: "+pokemonId));
        user.getCatchListIds().remove(pokemon);
        userRepository.save(user);
    }
    public void addToWishList(Long userId, Long pokemonId){
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found with id: "+userId));
        PokemonId pokemon = pokemonIdRepository.findById(pokemonId).orElseThrow(()->new RuntimeException("PokemonId not found with id: "+pokemonId));
        user.getWishListIds().add(pokemon);
        userRepository.save(user);
    }

    public void removeFromWishList(Long userId, Long pokemonId){
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found with id: "+userId));
        PokemonId pokemon = pokemonIdRepository.findById(pokemonId).orElseThrow(()->new RuntimeException("PokemonId not found with id: "+pokemonId));
        user.getWishListIds().remove(pokemon);
        userRepository.save(user);
    }

}
