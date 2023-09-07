package com.pokedex.userservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", url = "http://localhost:8080", path = "/pokedex/feign/pokemon")
public interface PokemonFeignClient {

    @PostMapping()
    public ResponseEntity<String> addUserId( @RequestHeader("Authorization") String token) ;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserId(@PathVariable Long id, @RequestHeader("Authorization") String token);

}
