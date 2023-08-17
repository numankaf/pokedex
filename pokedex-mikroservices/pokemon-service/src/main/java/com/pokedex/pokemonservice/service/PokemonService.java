package com.pokedex.pokemonservice.service;

import com.pokedex.pokemonservice.dto.PokemonDetailDto;
import com.pokedex.pokemonservice.dto.PokemonListDto;
import com.pokedex.pokemonservice.dto.PokemonSearchDto;
import com.pokedex.pokemonservice.entity.Pokemon;
import com.pokedex.pokemonservice.repository.PokemonRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {
    private final PokemonRepository pokemonRepository;
    private final ModelMapper modelMapper;

    public PokemonService(PokemonRepository pokemonRepository, ModelMapper modelMapper) {
        this.pokemonRepository = pokemonRepository;
        this.modelMapper = modelMapper;
    }

    public PokemonDetailDto createPokemon(PokemonDetailDto pokemonDetailDto) {
        Pokemon pokemon = modelMapper.map(pokemonDetailDto, Pokemon.class);
        pokemonRepository.save(pokemon);
        return modelMapper.map(pokemon, PokemonDetailDto.class);
    }


    public PokemonDetailDto updatePokemon(Long id, PokemonDetailDto dto) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new RuntimeException("Pokemon not found with id : " + id));
        modelMapper.map(dto, pokemon);
        pokemonRepository.save(pokemon);
        return modelMapper.map(pokemon, PokemonDetailDto.class);
    }

    @Transactional
    public void deletePokemon(Long id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new RuntimeException("Pokemon not found with id : " + id));
        pokemonRepository.deleteById(id);
    }

    public PokemonDetailDto getById(Long id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new RuntimeException("Pokemon not found with id : " + id));
        return modelMapper.map(pokemon, PokemonDetailDto.class);
    }

    public List<PokemonListDto> findAll() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        List<PokemonListDto> dtos = pokemons.stream().map(p ->modelMapper.map(p,PokemonListDto.class)).collect(Collectors.toList());
        return dtos;
    }

    public Page<PokemonListDto> findAll(Pageable pageable) {

        Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);
        List<PokemonListDto> dtos = pokemons.stream().map(p ->modelMapper.map(p,PokemonListDto.class)).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, pokemons.getTotalElements());
    }

    public Page<PokemonListDto> search(PokemonSearchDto dto, Pageable pageable) {
        Pokemon examplePokemon = new Pokemon();
        examplePokemon.setName(dto.getName());
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnorePaths("isActive").withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Pokemon> example = Example.of(examplePokemon, matcher);
        Page<Pokemon> pokemons = pokemonRepository.findAll(example, pageable);
        List<PokemonListDto> dtos = pokemons.stream().map(p ->modelMapper.map(p,PokemonListDto.class)).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, pokemons.getTotalElements());
    }



}
