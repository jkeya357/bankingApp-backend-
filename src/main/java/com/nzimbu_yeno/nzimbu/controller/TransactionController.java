package com.nzimbu_yeno.nzimbu.controller;

import com.nzimbu_yeno.nzimbu.domain.dto.TransactionRequestDto;
import com.nzimbu_yeno.nzimbu.domain.dto.TransactionsDto;
import com.nzimbu_yeno.nzimbu.domain.dto.TransactionsRequest;
import com.nzimbu_yeno.nzimbu.domain.entity.Transactions;
import com.nzimbu_yeno.nzimbu.mapper.TransactionMapper;
import com.nzimbu_yeno.nzimbu.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @GetMapping
    public ResponseEntity<List<TransactionsDto>> getAllTransactions() {
        List<Transactions> transactions = transactionService.getTransactions();
        List<TransactionsDto> transactionsDto = transactionMapper.toDtoList(transactions);
        return ResponseEntity.ok(transactionsDto);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionsDto> withdraw(@RequestBody TransactionRequestDto transactionRequestDto){

        TransactionsRequest transactionsRequest = transactionMapper.toTransactionsRequest(transactionRequestDto);
        Transactions transactions = transactionService.withdrawal(transactionsRequest);
        TransactionsDto transactionsDto = transactionMapper.toDto(transactions);
        return ResponseEntity.ok(transactionsDto);
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionsDto> deposit(@RequestBody TransactionRequestDto transactionRequestDto){
        TransactionsRequest transactionsRequest = transactionMapper.toTransactionsRequest(transactionRequestDto);
        Transactions transactions = transactionService.deposit(transactionsRequest);
        TransactionsDto transactionsDto = transactionMapper.toDto(transactions);
        return ResponseEntity.ok(transactionsDto);
    }
}
