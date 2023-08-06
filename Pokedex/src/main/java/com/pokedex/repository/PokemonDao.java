package com.pokedex.repository;

import com.pokedex.dto.pokemon.PokemonListDto;
import com.pokedex.entity.CatchList;
import com.pokedex.entity.Pokemon;
import com.pokedex.entity.WishList;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PokemonDao {

    private final EntityManager entityManager;
    private final ModelMapper modelMapper;

    public PokemonDao(EntityManager entityManager, ModelMapper modelMapper) {
        this.entityManager = entityManager;
        this.modelMapper = modelMapper;
    }

    public Page<PokemonListDto> getAllInCatchList(Long catchListId, Pageable pageable){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pokemon> criteriaQuery = criteriaBuilder.createQuery(Pokemon.class);
        Root<Pokemon> root = criteriaQuery.from(Pokemon.class);
        Join<Pokemon, CatchList> catchListJoin = root.join("catchLists");
        criteriaQuery.where(criteriaBuilder.equal(catchListJoin.get("id"), catchListId));
        var query = entityManager.createQuery(criteriaQuery);
        int count = query.getResultList().size();
        query.setFirstResult((int)pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<Pokemon> pokemons = query.getResultList();
        List<PokemonListDto> dtos = pokemons.stream().map( p-> modelMapper.map(p, PokemonListDto.class)).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, count);
    }


    public Page<PokemonListDto> getAllInWishList(Long wishListId, Pageable pageable){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pokemon> criteriaQuery = criteriaBuilder.createQuery(Pokemon.class);
        Root<Pokemon> root = criteriaQuery.from(Pokemon.class);
        Join<Pokemon, WishList> wishListJoin = root.join("wishLists");
        criteriaQuery.where(criteriaBuilder.equal(wishListJoin.get("id"), wishListId));
        var query = entityManager.createQuery(criteriaQuery);
        int count = query.getResultList().size();
        query.setFirstResult((int)pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<Pokemon> pokemons = query.getResultList();
        List<PokemonListDto> dtos = pokemons.stream().map( p-> modelMapper.map(p, PokemonListDto.class)).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, count);
    }
}
