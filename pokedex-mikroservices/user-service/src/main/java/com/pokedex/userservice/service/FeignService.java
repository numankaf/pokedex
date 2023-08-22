package com.pokedex.userservice.service;

import com.pokedex.userservice.dto.RegisterRequestDto;
import com.pokedex.userservice.entity.PokemonId;
import com.pokedex.userservice.entity.User;
import com.pokedex.userservice.enums.UserRole;
import com.pokedex.userservice.feign.PokemonFeignClient;
import com.pokedex.userservice.repository.PokemonIdRepository;
import com.pokedex.userservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FeignService {
    private final UserRepository userRepository;
    private final PokemonIdRepository pokemonIdRepository;
    private final ModelMapper modelMapper;
    private final PokemonFeignClient pokemonFeignClient;

    public FeignService(UserRepository userRepository, PokemonIdRepository pokemonIdRepository, ModelMapper modelMapper, PokemonFeignClient pokemonFeignClient) {
        this.userRepository = userRepository;
        this.pokemonIdRepository = pokemonIdRepository;
        this.modelMapper = modelMapper;
        this.pokemonFeignClient = pokemonFeignClient;
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

    public void createUser(RegisterRequestDto dto,String token){
        User user = modelMapper.map(dto, User.class);
        user.setIsActive(true);
        user.setThumbnail("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_640.png");
        user.setRole(UserRole.TRAINER);
        pokemonFeignClient.addUserId(token);
        userRepository.save(user);
    }

}
