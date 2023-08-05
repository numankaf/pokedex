package com.pokedex.controller;

import com.pokedex.service.CatchListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok("Successfully added to catch list with pokemon id : "+ id);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable  Long id){
        catchListService.removeFromCatchList(id);
        return ResponseEntity.ok("Successfully removed to catch list with pokemon id : "+ id);
    }

    @GetMapping()
    public ResponseEntity<?> getAllInCatchList(){
        return ResponseEntity.ok(catchListService.getPokemonsInCatchList());
    }
}
