package com.pokedex.controller;

import com.pokedex.dto.account.AccountDetailDto;
import com.pokedex.dto.account.ChangePasswordDto;
import com.pokedex.dto.user.UserDetailDto;
import com.pokedex.dto.user.UserUpdateRequestDto;
import com.pokedex.service.AccountService;
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
    public ResponseEntity<?> getDetail(){
        AccountDetailDto responseDto = accountService.getDetail();
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserUpdateRequestDto dto){
        AccountDetailDto responseDto = accountService.update(dto);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto dto){
        accountService.changePassword(dto);
        return ResponseEntity.ok(Map.of("message", "Updated Successfully"));
    }
}
