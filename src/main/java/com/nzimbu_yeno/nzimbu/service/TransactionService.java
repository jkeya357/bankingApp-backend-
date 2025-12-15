package com.nzimbu_yeno.nzimbu.service;

import com.nzimbu_yeno.nzimbu.domain.dto.TransactionsRequest;
import com.nzimbu_yeno.nzimbu.domain.entity.Transactions;

import java.util.List;

public interface TransactionService {

    List<Transactions> getTransactions();
    Transactions withdrawal(TransactionsRequest transactionsRequest);
    Transactions deposit(TransactionsRequest transactionsRequest);
}
