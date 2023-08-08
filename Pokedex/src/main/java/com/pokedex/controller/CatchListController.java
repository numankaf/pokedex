package com.pokedex.controller;

import com.pokedex.repository.PokemonDao;
import com.pokedex.service.CatchListService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/catch-list")
public class CatchListController {
    private final CatchListService catchListService;

    public CatchListController(CatchListService catchListService) {
        this.catchListService = catchListService;
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<?> add(@PathVariable Long id){
        catchListService.addToCatchList(id);
        return ResponseEntity.ok(Map.of("message", "Successfully added to catch list with pokemon id : "+ id));
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable  Long id){
        catchListService.removeFromCatchList(id);
        return ResponseEntity.ok(Map.of("message", "Successfully removed to catch list with pokemon id : "+ id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllInCatchList(){
        return ResponseEntity.ok(catchListService.getPokemonsInCatchList());
    }

    @GetMapping()
    public ResponseEntity<?> getAllInCathListPageable(Pageable pageable){
        return ResponseEntity.ok(catchListService.getPokemonsInCatchList(pageable));
    }
}
