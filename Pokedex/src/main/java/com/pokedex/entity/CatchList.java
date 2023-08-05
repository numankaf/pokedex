package com.pokedex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CATCH_LIST")
public class CatchList extends BaseEntity{
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "CATCH_LIST_POKEMONS",
            joinColumns = {@JoinColumn(name = "CATCH_LIST_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "POKEMONS_ID", referencedColumnName = "ID")})
    @JsonIgnore
    private List<Pokemon> pokemons;

    @OneToOne(mappedBy = "catchList")
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
