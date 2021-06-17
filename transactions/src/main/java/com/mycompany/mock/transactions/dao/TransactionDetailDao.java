package com.mycompany.mock.transactions.dao;

import com.mycompany.mock.transactions.entity.TransactionDetails;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TransactionDetailDao {


    private static Map<String, List<TransactionDetails>> transactionDetailsMap ;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Map<String, List<TransactionDetails>> getAccountTransactions() {

        if (!CollectionUtils.isEmpty(transactionDetailsMap)) {
            return transactionDetailsMap;
        }
        transactionDetailsMap = new HashMap<>();
        JSONParser parser = new JSONParser();

        try {

            URL res = getClass().getClassLoader().getResource("transactions.json");
            File file = Paths.get(res.toURI()).toFile();
            JSONArray txns = (JSONArray) parser.parse(new FileReader(file));

            txns.forEach(txnObj -> {
                TransactionDetails details = parseToTransactionDetails((JSONObject) txnObj);

                List<TransactionDetails> transactionDetails = transactionDetailsMap.get(details.getAcctIBank());
                if(transactionDetails == null){
                    transactionDetails = new ArrayList<>();
                }
                transactionDetails.add(details);
                transactionDetailsMap.put(details.getAcctIBank(), transactionDetails);
            });
        } catch (IOException | ParseException | URISyntaxException e) {
            e.printStackTrace();
        }
        return transactionDetailsMap;
    }

    private TransactionDetails parseToTransactionDetails(JSONObject txnObj) {
        JSONObject txn = txnObj;
        String transactionId = (String) txn.get("transactionId");
        String amountAndCurrency = (String) txn.get("amount");
        String acctIban = (String) txn.get("acctIban");
        String date = (String) txn.get("valueDate");
        String description = (String) txn.get("description");

        // Amount
        String[] split = amountAndCurrency.split(" ");
        boolean isNegative = split[1].indexOf("-") != -1;
        String anountStr = split[1].replace("-", "");
        Double amount = Double.parseDouble(anountStr);
        if (isNegative) {
            amount = amount * -1;
        }

        // Value Date
        String currency = split[0];
        LocalDate valueDate = LocalDate.parse(date, formatter);
        TransactionDetails details = new TransactionDetails(transactionId, amount, currency, acctIban, valueDate, description);
        return details;
    }
}
