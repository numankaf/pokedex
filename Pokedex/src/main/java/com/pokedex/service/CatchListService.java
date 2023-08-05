package com.pokedex.service;

import com.pokedex.dto.pokemon.PokemonListDto;
import com.pokedex.entity.CatchList;
import com.pokedex.entity.Pokemon;
import com.pokedex.entity.User;
import com.pokedex.entity.WishList;
import com.pokedex.exception.PokedexDatabaseException;
import com.pokedex.repository.CatchListRepository;
import com.pokedex.repository.PokemonRepository;
import com.pokedex.repository.UserRepository;
import com.pokedex.repository.WishListRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatchListService {
    private final CatchListRepository catchListRepository;
    private final PokemonRepository pokemonRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final WishListRepository wishListRepository;

    public CatchListService(CatchListRepository catchListRepository, PokemonRepository pokemonRepository,
                            UserRepository userRepository, ModelMapper modelMapper, WishListRepository wishListRepository) {
        this.catchListRepository = catchListRepository;
        this.pokemonRepository = pokemonRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.wishListRepository = wishListRepository;
    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username);
    }


    public void addToCatchList(Long pokemonId) {
        CatchList catchList = getCurrentUser().getCatchList();
        WishList wishList = getCurrentUser().getWishList();
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokedexDatabaseException("Pokemon not found with id: " + pokemonId));
        List<Pokemon> wishListPokemons = wishList.getPokemons();

        if (wishListPokemons.contains(pokemon)) {
            wishListPokemons.remove(pokemon);
            wishList.setPokemons(wishListPokemons);
            wishListRepository.save(wishList);
        }

        List<Pokemon> catchListPokemons = catchList.getPokemons();
        if(catchListPokemons.contains(pokemon)){
            throw new PokedexDatabaseException("Pokemon already exist with id: "+ pokemonId);
        }
        catchListPokemons.add(pokemon);
        catchList.setPokemons(catchListPokemons);
        catchListRepository.save(catchList);

    }

    public void removeFromCatchList(Long pokemonId) {
        CatchList catchList = getCurrentUser().getCatchList();
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokedexDatabaseException("Pokemon not found with id: " + pokemonId));
        List<Pokemon> pokemons = catchList.getPokemons();
        pokemons.remove(pokemon);
        catchList.setPokemons(pokemons);
        catchListRepository.save(catchList);
    }

    public List<PokemonListDto> getPokemonsInCatchList() {
        CatchList catchList = getCurrentUser().getCatchList();
        return catchList.getPokemons().stream().map(p -> modelMapper.map(p, PokemonListDto.class)).collect(Collectors.toList());
    }

}
