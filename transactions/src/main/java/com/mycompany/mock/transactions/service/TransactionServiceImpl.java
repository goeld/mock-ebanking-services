package com.mycompany.mock.transactions.service;

import com.mycompany.mock.transactions.dao.TransactionDetailDao;
import com.mycompany.mock.transactions.entity.TransactionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionDetailDao dao;

    @Override
    public List<TransactionDetails> getTransactionsForCustomer(String acctIban, Integer txnYear, Integer txnMonth, Integer pageSize, Integer startPage){


        Map<String, List<TransactionDetails>> txnMap = dao.getTransactionsForCustomer();
        List<TransactionDetails> transactionDetails = txnMap.get(acctIban);

        transactionDetails.sort(Comparator.comparing(TransactionDetails::getValueDate));
        if(txnYear != null && txnMonth != null) {
            LocalDate monthStart = LocalDate.of(txnYear, txnMonth, 1);
            int monthLength = monthStart.lengthOfMonth();
            LocalDate monthEnd = LocalDate.of(txnYear, txnMonth, monthLength);
            transactionDetails = transactionDetails.stream().filter(txn -> {
                LocalDate txnDate = txn.getValueDate();
                return (txnDate.isEqual(monthStart) || txnDate.isAfter(monthStart))
                        && (txnDate.isBefore(monthEnd) || txnDate.isEqual(monthEnd));
            }).collect(Collectors.toList());
        }

        if (pageSize == null ){
            pageSize = 5;
        }
        if(startPage == null || startPage <=1 ){
            startPage = 1;
        }

        Integer toSkip = pageSize * ( startPage -1);
        transactionDetails = transactionDetails.stream().skip(toSkip).limit(pageSize).collect(Collectors.toList());

        return transactionDetails;
    }
}
