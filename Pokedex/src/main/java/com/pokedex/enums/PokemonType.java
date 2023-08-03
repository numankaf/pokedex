package com.pokedex.enums;

import java.util.Arrays;
import java.util.Optional;

public enum PokemonType {
    NORMAL("NORMAL","#aa9"),
    FIRE("FIRE","#f42"),
    WATER("WATER","#39f"),
    ELECTRIC("ELECTRIC","#fc3"),
    GRASS("GRASS","#7c5"),
    ICE("ICE","#6cf"),
    FIGHTING("FIGHTING","#b54"),
    POISON("POISON","#a59"),
    GROUND("GROUND","#db5"),
    FLYING("FLYING","#89f"),
    PSYCHIC("PSYCHIC","#f59"),
    BUG("BUG","#ab2"),
    ROCK("ROCK","#ba6"),
    GHOST("GHOST","#66b"),
    DRAGON("DRAGON","#76e"),
    DARK("DARK","#754"),
    STEEL("STEEL","#aab"),
    FAIRY("FAIRY", "#e9e");

    private final String name;
    private final String color;

    PokemonType(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public static Optional<PokemonType> getPokemonTypeByValue(String value) {
        return Arrays.stream(PokemonType.values())
                .filter(pokemonType -> pokemonType.getName().equals(value))
                .findFirst();
    }

}
