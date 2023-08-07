package com.pokedex.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pokedex.enums.PokemonType;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "POKEMON")
public class Pokemon extends BaseEntity {
    @Column(name = "NAME")
    private String name;

    @Column(name = "THUMBNAIL")
    private String thumbnail;

    @ElementCollection(targetClass = PokemonType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "POKEMON_TYPE")
    @Column(name = "TYPES")
    private Set<PokemonType> types;

    @ManyToMany(mappedBy = "pokemons")
    @JsonIgnore
    private List<CatchList>  catchLists;


    @ManyToMany(mappedBy = "pokemons")
    @JsonIgnore
    private List<WishList>  wishLists;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Set<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(Set<PokemonType> types) {
        this.types = types;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(Integer specialAttack) {
        this.specialAttack = specialAttack;
    }

    public Integer getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(Integer specialDefense) {
        this.specialDefense = specialDefense;
    }

    public List<CatchList> getCatchLists() {
        return catchLists;
    }

    public void setCatchLists(List<CatchList> catchLists) {
        this.catchLists = catchLists;
    }

    public List<WishList> getWishLists() {
        return wishLists;
    }

    public void setWishLists(List<WishList> wishLists) {
        this.wishLists = wishLists;
    }
}
