package com.pokedex.pokemonservice.service;

import com.pokedex.pokemonservice.config.JwtTokenProvider;
import com.pokedex.pokemonservice.dto.PokemonDetailDto;
import com.pokedex.pokemonservice.dto.PokemonListDto;
import com.pokedex.pokemonservice.dto.PokemonSearchDto;
import com.pokedex.pokemonservice.entity.Pokemon;
import com.pokedex.pokemonservice.entity.UserId;
import com.pokedex.pokemonservice.repository.PokemonRepository;
import com.pokedex.pokemonservice.repository.UserIdRepository;
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
    private final UserIdRepository userIdRepository;
    private final JwtTokenProvider tokenProvider;

    public PokemonService(PokemonRepository pokemonRepository, ModelMapper modelMapper, UserIdRepository userIdRepository, JwtTokenProvider tokenProvider) {
        this.pokemonRepository = pokemonRepository;
        this.modelMapper = modelMapper;
        this.userIdRepository = userIdRepository;
        this.tokenProvider = tokenProvider;
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
        for (UserId userId:pokemon.getCatchListIds()){
            userId.getCatchLists().remove(pokemon);
        }
        for (UserId userId:pokemon.getWishListIds()){
            userId.getWishLists().remove(pokemon);
        }
        pokemonRepository.deleteById(id);
    }

    public PokemonDetailDto getById(Long id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new RuntimeException("Pokemon not found with id : " + id));
        return modelMapper.map(pokemon, PokemonDetailDto.class);
    }

    public List<PokemonListDto> findAll() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        List<PokemonListDto> dtos = pokemons.stream().map(p -> modelMapper.map(p, PokemonListDto.class)).collect(Collectors.toList());
        return dtos;
    }

    public Page<PokemonListDto> findAll(Pageable pageable) {

        Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);
        List<PokemonListDto> dtos = pokemons.stream().map(p -> modelMapper.map(p, PokemonListDto.class)).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, pokemons.getTotalElements());
    }

    public Page<PokemonListDto> search(PokemonSearchDto dto, Pageable pageable) {
        Pokemon examplePokemon = new Pokemon();
        examplePokemon.setName(dto.getName());
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnorePaths("isActive").withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Pokemon> example = Example.of(examplePokemon, matcher);
        Page<Pokemon> pokemons = pokemonRepository.findAll(example, pageable);
        List<PokemonListDto> dtos = pokemons.stream().map(p -> modelMapper.map(p, PokemonListDto.class)).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, pokemons.getTotalElements());
    }


    public List<PokemonListDto> getCatchListPokemonsCurrentUser(String token) {
        Long id = tokenProvider.getUserIdFromToken(token.substring(7));
        UserId userId = userIdRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userId.getCatchLists().stream().map(p->modelMapper.map(p, PokemonListDto.class)).collect(Collectors.toList());
    }

    public List<PokemonListDto> getWishListPokemonsCurrentUser(String token) {
        Long id = tokenProvider.getUserIdFromToken(token.substring(7));
        UserId userId = userIdRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userId.getWishLists().stream().map(p->modelMapper.map(p, PokemonListDto.class)).collect(Collectors.toList());
    }

    public void addToCatchList(Long pokemonId,String token){
        Long id = tokenProvider.getUserIdFromToken(token.substring(7));
        UserId userId = userIdRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new RuntimeException("Pokemon not found with id: " + id));
        if(userId.getWishLists().contains(pokemon)){
            userId.getWishLists().remove(pokemon);
            userIdRepository.save(userId);
        }
        if (userId.getCatchLists().contains(pokemon)){
            throw new RuntimeException("Pokemon already exist in catchlist with id: "+pokemonId);
        }
        List<Pokemon> catchListPokemons = userId.getCatchLists();
        catchListPokemons.add(pokemon);
        userId.setCatchLists(catchListPokemons);
        userIdRepository.save(userId);
    }

    public void removeFromCatchList(Long pokemonId,String token){
        Long id = tokenProvider.getUserIdFromToken(token.substring(7));
        UserId userId = userIdRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new RuntimeException("Pokemon 0not found with id: " + id));
        userId.getCatchLists().remove(pokemon);
        userIdRepository.save(userId);
    }

    public void addToWishList(Long pokemonId,String token){
        Long id = tokenProvider.getUserIdFromToken(token.substring(7));
        UserId userId = userIdRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new RuntimeException("Pokemon 0not found with id: " + id));
        if(userId.getCatchLists().contains(pokemon)){
            throw new RuntimeException("This pokemon is already in catch list. You can't add it to your wish list");
        }
        if (userId.getWishLists().contains(pokemon)){
            throw new RuntimeException("Pokemon already exist in wishlist with id: "+pokemonId);
        }
        userId.getWishLists().add(pokemon);
        userIdRepository.save(userId);
    }

    public void removeFromWishList(Long pokemonId,String token){
        Long id = tokenProvider.getUserIdFromToken(token.substring(7));
        UserId userId = userIdRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new RuntimeException("Pokemon 0not found with id: " + id));
        userId.getWishLists().remove(pokemon);
        userIdRepository.save(userId);
    }


}
