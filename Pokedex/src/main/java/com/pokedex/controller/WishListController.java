package com.pokedex.controller;

import com.pokedex.service.WishListService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wish-list")
public class WishListController {
    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<?> add(@PathVariable Long id) {
        wishListService.addToWishList(id);
        return ResponseEntity.ok("Successfully added to wish list with pokemon id : " + id);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        wishListService.removeFromWishList(id);
        return ResponseEntity.ok("Successfully removed to wish list with pokemon id : " + id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllInWishList() {
        return ResponseEntity.ok(wishListService.getPokemonsInWishList());
    }

    @GetMapping()
    public ResponseEntity<?> getAllInWishListPageable(Pageable pageable) {
        return ResponseEntity.ok(wishListService.getPokemonsInWishList(pageable));
    }

}
