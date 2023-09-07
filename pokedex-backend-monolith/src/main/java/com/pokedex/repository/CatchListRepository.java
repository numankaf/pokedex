package com.pokedex.repository;

import com.pokedex.entity.CatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatchListRepository extends JpaRepository<CatchList, Long> {

}
