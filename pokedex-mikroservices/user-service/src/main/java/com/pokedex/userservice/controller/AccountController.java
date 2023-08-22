package com.pokedex.userservice.controller;

import com.pokedex.userservice.dto.AccountDetailDto;
import com.pokedex.userservice.dto.AccountTopbarDto;
import com.pokedex.userservice.dto.ChangePasswordDto;
import com.pokedex.userservice.dto.UserUpdateRequestDto;
import com.pokedex.userservice.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public ResponseEntity<?> getDetail(@RequestHeader("Authorization") String token){
        AccountDetailDto responseDto = accountService.getDetail(token);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserUpdateRequestDto dto,@RequestHeader("Authorization") String token){
        AccountDetailDto responseDto = accountService.update(dto,token);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto dto, @RequestHeader("Authorization") String token){
        accountService.changePassword(dto,token);
        return ResponseEntity.ok(Map.of("message", "Updated Successfully"));
    }

    @GetMapping("/topbar")
    public ResponseEntity<?> getDetailForTopbar(@RequestHeader("Authorization") String token){
        AccountTopbarDto responseDto = accountService.getDetailForTopbar(token);
        return ResponseEntity.ok(responseDto);
    }

}
