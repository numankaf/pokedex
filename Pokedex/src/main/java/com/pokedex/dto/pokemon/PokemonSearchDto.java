package com.pokedex.dto.pokemon;

import com.pokedex.enums.PokemonType;

import java.util.Set;

public class PokemonSearchDto {
    private String name;

    private Set<PokemonType> types;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(Set<PokemonType> types) {
        this.types = types;
    }
}
