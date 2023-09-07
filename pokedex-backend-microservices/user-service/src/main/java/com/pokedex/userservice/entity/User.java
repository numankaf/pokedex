package com.pokedex.userservice.entity;

import com.pokedex.userservice.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "USERS")
public class User extends BaseEntity{
    @Column(name = "USERNAME", length = 20, unique = true)
    private String username;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "ABOUT")
    private String about;

    @Column(name = "THUMBNAIL",  columnDefinition = "LONGTEXT")
    private String thumbnail;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_WISHLIST_IDS",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "POKEMON_ID", referencedColumnName = "ID")})
    private List<PokemonId> wishListIds;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_CATCHLIST_IDS",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "POKEMON_ID", referencedColumnName = "ID")})
    private List<PokemonId> catchListIds;
}
