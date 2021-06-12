package com.mycompany.mock.transactions.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionHealthCheckController {
    @GetMapping("/txn/health")
    public String getVersion() {
        return "1.0";
    }

}