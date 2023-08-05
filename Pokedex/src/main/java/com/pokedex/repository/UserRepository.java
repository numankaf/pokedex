package com.pokedex.repository;

import com.pokedex.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u from User u where u.username = :s or u.email = :s ")
    Optional<User> findByUsernameOrEmail(String s);

    User findByUsername(String  username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
