package com.mycompany.mock.transactions.service;

import com.mycompany.mock.transactions.entity.TransactionDetails;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    List<TransactionDetails> getTransactionsForCustomer(String acctIban, Integer txnYear, Integer txnMonth, Integer pageSize, Integer startPage);
}
