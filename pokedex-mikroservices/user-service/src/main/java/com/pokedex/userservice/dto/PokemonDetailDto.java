package com.pokedex.userservice.dto;


import lombok.Data;

@Data
public class PokemonDetailDto {
    private Long id;

    private String createdBy;

    private String createdDate;

    private String lastModifiedBy;

    private String lastModifiedDate;

    private Boolean isActive;

    private String name;

    private String thumbnail;



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
