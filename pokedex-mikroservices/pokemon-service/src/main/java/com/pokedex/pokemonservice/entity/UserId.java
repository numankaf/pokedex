package com.pokedex.pokemonservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "USER_ID")
public class UserId extends BaseEntity{

    @ManyToMany(mappedBy = "wishListIds")
    private List<Pokemon> wishLists;

    @ManyToMany(mappedBy = "catchListIds")
    private List<Pokemon> catchLists;
}
