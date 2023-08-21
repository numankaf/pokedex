package com.pokedex.pokemonservice.entity;

import com.pokedex.pokemonservice.enums.PokemonType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "POKEMON")
public class Pokemon extends BaseEntity{

    @Column(name = "NAME")
    private String name;

    @Column(name = "THUMBNAIL",  columnDefinition = "LONGTEXT")
    private String thumbnail;

    @ElementCollection(targetClass = PokemonType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "POKEMON_TYPE")
    @Column(name = "TYPES")
    private Set<PokemonType> types;

    @Column(name = "WEIGHT")
    private Double weight;

    @Column(name = "HEIGHT")
    private Double height;

    @Column(name = "SPECIE")
    private String specie;

    @Column(name = "ABILITIES")
    private String abilities;

    @Column(name = "HP")
    private Integer hp;

    @Column(name = "ATTACK")
    private Integer attack;

    @Column(name = "DEFENSE")
    private Integer defense;

    @Column(name = "SPEED")
    private Integer speed;

    @Column(name = "SPECIAL_ATTACK")
    private Integer specialAttack;

    @Column(name = "SPECIAL_DEFENSE")
    private Integer specialDefense;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "POKEMON_WISHLIST_IDS",
            joinColumns = {@JoinColumn(name = "POKEMON_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")})
    private List<UserId> wishListIds;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "POKEMON_CATCHLIST_IDS",
            joinColumns = {@JoinColumn(name = "POKEMON_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")})
    private List<UserId> catchListIds;
}
