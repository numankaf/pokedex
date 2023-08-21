package com.pokedex.authservice.entity;


import com.pokedex.authservice.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "USERS")
public class User extends BaseEntity{
    @Column(name = "USERNAME", length = 20, unique = true)
    private String username;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSWORD")
    private String password;

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

}
