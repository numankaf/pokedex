package com.pokedex.entity;

import com.pokedex.enums.PokemonType;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "POKEMON")
public class Pokemon extends BaseEntity {
    private String name;
    private String thumbnail;

    @ElementCollection(targetClass = PokemonType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "POKEMON_TYPE")
    @Column(name = "TYPES")
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
