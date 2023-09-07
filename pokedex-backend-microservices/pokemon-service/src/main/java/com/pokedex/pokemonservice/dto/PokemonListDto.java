package com.pokedex.pokemonservice.dto;

import com.pokedex.pokemonservice.enums.PokemonType;
import lombok.Data;

import java.util.Set;

@Data
public class PokemonListDto {
    private Long id;

    private String name;

    private String thumbnail;

    private Set<PokemonType> types;

    private Integer hp;

    private Integer attack;

    private Integer defense;

    private Integer speed;

    private Integer specialAttack;

    private Integer specialDefense;

    private Boolean isInCatchList;

    private Boolean isInWishList;
}
