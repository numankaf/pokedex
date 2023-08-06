package com.pokedex.service;

import com.pokedex.dto.pokemon.PokemonListDto;
import com.pokedex.entity.CatchList;
import com.pokedex.entity.Pokemon;
import com.pokedex.entity.User;
import com.pokedex.entity.WishList;
import com.pokedex.exception.PokedexDatabaseException;
import com.pokedex.repository.PokemonDao;
import com.pokedex.repository.PokemonRepository;
import com.pokedex.repository.UserRepository;
import com.pokedex.repository.WishListRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishListService {
    private final PokemonRepository pokemonRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final WishListRepository wishListRepository;
    private final PokemonDao pokemonDao;

    public WishListService(PokemonRepository pokemonRepository, UserRepository userRepository, ModelMapper modelMapper,
                           WishListRepository wishListRepository, PokemonDao pokemonDao) {
        this.pokemonRepository = pokemonRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.wishListRepository = wishListRepository;
        this.pokemonDao = pokemonDao;
    }


    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username);
    }

    public void addToWishList(Long pokemonId) {
        WishList wishList = getCurrentUser().getWishList();
        CatchList catchList = getCurrentUser().getCatchList();
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokedexDatabaseException("Pokemon not found with id: " + pokemonId));
        List<Pokemon> wishListPokemons = wishList.getPokemons();

        if(catchList.getPokemons().contains(pokemon)){
            throw new PokedexDatabaseException("This pokemon is already in catch list. You can't add it to your wish list ");
        }

        if(wishListPokemons.contains(pokemon)){
            throw new PokedexDatabaseException("Pokemon already exist with id: "+ pokemonId);
        }
        wishListPokemons.add(pokemon);
        wishList.setPokemons(wishListPokemons);
        wishListRepository.save(wishList);

    }

    public void removeFromWishList(Long pokemonId) {
        WishList wishList = getCurrentUser().getWishList();
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokedexDatabaseException("Pokemon not found with id: " + pokemonId));
        List<Pokemon> wishListPokemons = wishList.getPokemons();
        wishListPokemons.remove(pokemon);
        wishList.setPokemons(wishListPokemons);
        wishListRepository.save(wishList);
    }

    public List<PokemonListDto> getPokemonsInWishList() {
        WishList wishList = getCurrentUser().getWishList();
        return wishList.getPokemons().stream().map(p -> modelMapper.map(p, PokemonListDto.class)).collect(Collectors.toList());
    }

    public Page<PokemonListDto> getPokemonsInWishList(Pageable pageable) {
        Long id = getCurrentUser().getWishList().getId();
        return pokemonDao.getAllInWishList(id, pageable);
    }

}
