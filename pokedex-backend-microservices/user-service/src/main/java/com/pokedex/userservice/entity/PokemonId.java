package com.pokedex.userservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "POKEMON_ID")
public class PokemonId extends BaseEntity{

    @ManyToMany(mappedBy = "wishListIds")
    private List<User> wishLists;

    @ManyToMany(mappedBy = "catchListIds")
    private List<User> catchLists;
}
