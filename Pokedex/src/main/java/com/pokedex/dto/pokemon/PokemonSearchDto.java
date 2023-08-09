package com.pokedex.dto.pokemon;

import com.pokedex.enums.PokemonType;

import java.util.Set;

public class PokemonSearchDto {
    private String name;

    private PokemonType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PokemonType getType() {
        return type;
    }

    public void setType(PokemonType type) {
        this.type = type;
    }
}
