package com.pokedex.pokemonservice.dto;

import com.pokedex.pokemonservice.enums.PokemonType;
import lombok.Data;

import java.util.Set;

@Data
public class PokemonDetailDto {
    private Long id;

    private String createdBy;

    private String createdDate;

    private String lastModifiedBy;

    private String lastModifiedDate;

    private Boolean isActive;

    private String name;

    private String thumbnail;

    private Set<PokemonType> types;

    private Double weight;

    private Double height;

    private String specie;

    private String abilities;

    private Integer hp;

    private Integer attack;

    private Integer defense;

    private Integer speed;

    private Integer specialAttack;

    private Integer specialDefense;
}
