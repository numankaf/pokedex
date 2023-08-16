package com.pokedex.service;

import com.pokedex.dto.pokemon.PokemonDetailDto;
import com.pokedex.dto.pokemon.PokemonListDto;
import com.pokedex.dto.pokemon.PokemonSearchDto;
import com.pokedex.dto.user.UserListDto;
import com.pokedex.entity.CatchList;
import com.pokedex.entity.Pokemon;
import com.pokedex.entity.User;
import com.pokedex.entity.WishList;
import com.pokedex.exception.PokedexDatabaseException;
import com.pokedex.repository.PokemonRepository;
import com.pokedex.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {
    private final PokemonRepository pokemonRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(PokemonService.class);

    public PokemonService(PokemonRepository pokemonRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.pokemonRepository = pokemonRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Cacheable(cacheNames = "currentUser")
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username);
    }

    public PokemonDetailDto createPokemon(PokemonDetailDto pokemonDetailDto) {
        Pokemon pokemon = modelMapper.map(pokemonDetailDto, Pokemon.class);
        pokemonRepository.save(pokemon);
        return modelMapper.map(pokemon, PokemonDetailDto.class);
    }

    @CachePut(cacheNames = "pokemons", key = "#id")
    public PokemonDetailDto updatePokemon(Long id, PokemonDetailDto dto) {
        logger.info("UPDATE pokemon by id : " + id);
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokedexDatabaseException("Pokemon not found with id : " + id));
        pokemon.setName(dto.getName());
        pokemon.setThumbnail(dto.getThumbnail());
        pokemon.setTypes(dto.getTypes());
        pokemon.setWeight(dto.getWeight());
        pokemon.setHeight(dto.getHeight());
        pokemon.setSpecie(dto.getSpecie());
        pokemon.setAbilities(dto.getAbilities());
        pokemon.setHp(dto.getHp());
        pokemon.setAttack(dto.getAttack());
        pokemon.setDefense(dto.getDefense());
        pokemon.setSpeed(dto.getSpeed());
        pokemon.setSpecialAttack(dto.getSpecialAttack());
        pokemon.setSpecialDefense(dto.getSpecialDefense());
        pokemonRepository.save(pokemon);
        return modelMapper.map(pokemon, PokemonDetailDto.class);
    }

    @Transactional
    @CacheEvict(cacheNames = "pokemons", key = "#id")
    public void deletePokemon(Long id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokedexDatabaseException("Pokemon not found with id : " + id));
        for (CatchList catchList : pokemon.getCatchLists()) {
            catchList.getPokemons().remove(pokemon);
        }
        for (WishList wishList : pokemon.getWishLists()) {
            wishList.getPokemons().remove(pokemon);
        }

        pokemonRepository.deleteById(id);
    }

    @Cacheable(cacheNames = "pokemons", key = "#id")
    public PokemonDetailDto getById(Long id) {
        logger.info("GET pokemon by id : " + id);
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokedexDatabaseException("Pokemon not found with id : " + id));
        return modelMapper.map(pokemon, PokemonDetailDto.class);
    }

    public List<PokemonListDto> findAll() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        List<PokemonListDto> dtos = pokemons.stream().map(this::toListDto).collect(Collectors.toList());
        return dtos;
    }

    @Cacheable("pokemonsPageable")
    public Page<PokemonListDto> findAll(Pageable pageable) {
        logger.info("FIND ALL pokemons pageable. Page : " + pageable.getPageNumber());
        Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);
        List<PokemonListDto> dtos = pokemons.stream().map(this::toListDto).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, pokemons.getTotalElements());
    }

    public Page<PokemonListDto> search(PokemonSearchDto dto, Pageable pageable) {
        Pokemon examplePokemon = new Pokemon();
        examplePokemon.setName(dto.getName());
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnorePaths("isActive").withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Pokemon> example = Example.of(examplePokemon, matcher);
        Page<Pokemon> pokemons = pokemonRepository.findAll(example, pageable);
        List<PokemonListDto> dtos = pokemons.stream().map(this::toListDto).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, pokemons.getTotalElements());
    }

    public PokemonListDto toListDto(Pokemon pokemon) {
        PokemonListDto dto = modelMapper.map(pokemon, PokemonListDto.class);
        dto.setInCatchList(getCurrentUser().getCatchList().getPokemons().contains(pokemon));
        dto.setInWishList(getCurrentUser().getWishList().getPokemons().contains(pokemon));
        return dto;
    }

    @Cacheable(cacheNames = "whoCatched", key = "#id")
    public List<UserListDto> getUsersWhoCatch(Long id) {
        logger.info("GET USERS who catched  with pokemon id:" + id);
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokedexDatabaseException("Pokemon not found with id : " + id));
        List<UserListDto> dtos = pokemon.getCatchLists().stream().map(CatchList::getUser)
                .map(user -> modelMapper.map(user, UserListDto.class)).collect(Collectors.toList());
        return dtos;
    }

    @Cacheable(cacheNames = "whoWished", key = "#id")
    public List<UserListDto> getUsersWhoAddedWishList(Long id) {
        logger.info("GET USERS who wished  with pokemon id:" + id);
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokedexDatabaseException("Pokemon not found with id : " + id));
        List<UserListDto> dtos = pokemon.getWishLists().stream().map(WishList::getUser)
                .map(user -> modelMapper.map(user, UserListDto.class)).collect(Collectors.toList());
        return dtos;
    }
}
