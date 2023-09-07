package com.pokedex.pokemonservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "USER_ID")
public class UserId extends BaseEntity{

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "POKEMON_WISHLIST_IDS",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "POKEMON_ID", referencedColumnName = "ID")})
    private List<Pokemon> wishLists;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "POKEMON_CATCHLIST_IDS",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "POKEMON_ID", referencedColumnName = "ID")})
    private List<Pokemon> catchLists;
}
