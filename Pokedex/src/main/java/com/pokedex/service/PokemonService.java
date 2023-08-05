package com.pokedex.service;

import com.pokedex.dto.pokemon.PokemonDetailDto;
import com.pokedex.dto.pokemon.PokemonListDto;
import com.pokedex.dto.pokemon.PokemonSearchDto;
import com.pokedex.entity.Pokemon;
import com.pokedex.entity.User;
import com.pokedex.exception.PokedexDatabaseException;
import com.pokedex.repository.PokemonRepository;
import com.pokedex.repository.UserRepository;
import org.modelmapper.ModelMapper;
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

    public PokemonService(PokemonRepository pokemonRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.pokemonRepository = pokemonRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username);
    }

    public PokemonDetailDto createPokemon(PokemonDetailDto pokemonDetailDto) {
        Pokemon pokemon = modelMapper.map(pokemonDetailDto, Pokemon.class);
        pokemonRepository.save(pokemon);
        return modelMapper.map(pokemon, PokemonDetailDto.class);
    }

    public PokemonDetailDto updatePokemon(Long id, PokemonDetailDto dto) {
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

    public void deletePokemon(Long id) {
        pokemonRepository.deleteById(id);
    }

    public PokemonDetailDto getById(Long id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokedexDatabaseException("Pokemon not found with id : " + id));
        return modelMapper.map(pokemon, PokemonDetailDto.class);
    }

    public List<PokemonListDto> findAll() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        List<PokemonListDto> dtos = pokemons.stream().map(this::toListDto).collect(Collectors.toList());
        return dtos;
    }

    public Page<PokemonListDto> findAll(Pageable pageable) {
        Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);
        List<PokemonListDto> dtos = pokemons.stream().map(this::toListDto).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, pokemons.getTotalElements());
    }

    public Page<PokemonListDto> search(PokemonSearchDto dto, Pageable pageable) {
        Pokemon examplePokemon = new Pokemon();
        examplePokemon.setName(dto.getName());
        examplePokemon.setTypes(dto.getTypes());
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnorePaths("isActive").withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Pokemon> example = Example.of(examplePokemon, matcher);
        Page<Pokemon> pokemons = pokemonRepository.findAll(example, pageable);
        List<PokemonListDto> dtos = pokemons.stream().map(this::toListDto).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, pokemons.getTotalElements());
    }

    public PokemonListDto toListDto(Pokemon pokemon){
        PokemonListDto dto = modelMapper.map(pokemon, PokemonListDto.class);
        dto.setInCatchList(getCurrentUser().getCatchList().getPokemons().contains(pokemon));
        dto.setInWishList(getCurrentUser().getWishList().getPokemons().contains(pokemon));
        return dto;
    }
}
