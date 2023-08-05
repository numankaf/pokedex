package com.pokedex.dto.pokemon;

import com.pokedex.enums.PokemonType;

import java.util.Set;

public class PokemonListDto {
    private Long id;

    private String name;

    private String thumbnail;

    private Set<PokemonType> types;

    private Integer hp;

    private Integer attack;

    private Integer defense;

    private Integer speed;

    private Integer specialAttack;

    private Integer specialDefense;

    private Boolean isInCatchList;

    private Boolean isInWishList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Boolean getInCatchList() {
        return isInCatchList;
    }

    public void setInCatchList(Boolean inCatchList) {
        isInCatchList = inCatchList;
    }

    public Boolean getInWishList() {
        return isInWishList;
    }

    public void setInWishList(Boolean inWishList) {
        isInWishList = inWishList;
    }
}
