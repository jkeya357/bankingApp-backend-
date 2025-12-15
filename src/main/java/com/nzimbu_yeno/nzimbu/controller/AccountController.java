package com.nzimbu_yeno.nzimbu.controller;

import com.nzimbu_yeno.nzimbu.domain.dto.*;
import com.nzimbu_yeno.nzimbu.domain.entity.Account;
import com.nzimbu_yeno.nzimbu.mapper.AccountMapper;
import com.nzimbu_yeno.nzimbu.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @GetMapping
    public ResponseEntity<List<AccountDto>> findAccounts(){
        List<Account> account  = accountService.getAccount();
        List<AccountDto> accountDto = accountMapper.toAccountDtoList(account);
        return ResponseEntity.ok(accountDto);
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(
            @RequestBody AccountRequestDto accountRequestDto
    ){

        AccountRequest accountRequest = accountMapper.toAccountRequest(accountRequestDto);
        Account account = accountService.createAccount(accountRequest);
        AccountDto accountDto = accountMapper.toAccountDto(account);
        return ResponseEntity.ok(accountDto);

    }

    @DeleteMapping
    public ResponseEntity<DeleteAccountDto> deleteAccount(@RequestBody DeleteAccountRequest deleteAccountRequest){
        accountService.deleteAccount(deleteAccountRequest);

        DeleteAccountDto response = DeleteAccountDto.builder()
                .message("Account deleted successfully")
                .build();
        return ResponseEntity.ok(response);
    }
}
