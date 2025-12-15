package com.nzimbu_yeno.nzimbu.service.impl;

import com.nzimbu_yeno.nzimbu.domain.TransactionType;
import com.nzimbu_yeno.nzimbu.domain.dto.TransactionsRequest;
import com.nzimbu_yeno.nzimbu.domain.entity.Account;
import com.nzimbu_yeno.nzimbu.domain.entity.Transactions;
import com.nzimbu_yeno.nzimbu.repository.AccountRepository;
import com.nzimbu_yeno.nzimbu.repository.TransactionsRepository;
import com.nzimbu_yeno.nzimbu.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionsRepository transactionsRepository;


    @Override
    public List<Transactions> getTransactions() {
        return transactionsRepository.findAll();
    }

    @Override
    public Transactions withdrawal(TransactionsRequest transactionsRequest) {

        TransactionType withdraw = TransactionType.WITHDRAWAL;

        Account foundAccount = accountRepository.findByAccountNumber(transactionsRequest.getAccountNumber())
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        BigDecimal currentBalance = foundAccount.getAccountBalance();
        BigDecimal withdrawalAmount = transactionsRequest.getAmount();

        if(withdrawalAmount.compareTo(BigDecimal.valueOf(20)) < 0){
            throw new IllegalArgumentException("Withdrawal amount must be a multiple of 10 and cannot be less than R20");
        }

        if(withdrawalAmount.compareTo(currentBalance) > 0){
            throw new IllegalArgumentException("Insufficient funds");
        }

        foundAccount.setAccountBalance(currentBalance.subtract(withdrawalAmount));

        Transactions transactions = new Transactions();
        transactions.setTransactionType(withdraw);
        transactions.setAccount(foundAccount);
        transactions.setAmount(withdrawalAmount);

        transactionsRepository.save(transactions);
        accountRepository.save(foundAccount);

        return transactions;

    }

    @Override
    public Transactions deposit(TransactionsRequest transactionsRequest) {

        TransactionType deposit = TransactionType.DEPOSIT;

        Account account = accountRepository.findByAccountNumber(transactionsRequest.getAccountNumber())
                .orElseThrow(() -> new  EntityNotFoundException("Account not found"));

        BigDecimal amount = transactionsRequest.getAmount();

        String description = transactionsRequest.getDescription();

        if(amount.compareTo(BigDecimal.valueOf(20)) < 0){
            throw new IllegalArgumentException("A minimum amount of R20 is required for deposits");
        }

        BigDecimal newBalance = account.getAccountBalance().add(amount).setScale(2, RoundingMode.HALF_UP);

        account.setAccountBalance(newBalance);

        Transactions transactions = new Transactions();
        transactions.setTransactionType(deposit);
        transactions.setDescription(description);
        transactions.setAccount(account);
        transactions.setAmount(amount);

        accountRepository.save(account);
        transactionsRepository.save(transactions);
        return transactions;
    }
}
