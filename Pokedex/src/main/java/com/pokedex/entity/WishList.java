package com.pokedex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "WISH_LIST")
public class WishList extends BaseEntity{
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "WISH_LIST_POKEMONS",
            joinColumns = {@JoinColumn(name = "WISH_LIST_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "POKEMONS_ID", referencedColumnName = "ID")})
    @JsonIgnore
    private List<Pokemon> pokemons;

    @OneToOne(mappedBy = "wishList")
    private User user;

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
