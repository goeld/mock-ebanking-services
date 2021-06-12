package com.mycompany.mock.customer.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerHealthCheckController {
    @GetMapping("/customer/health")
    public String getVersion() {
        return "1.0";
    }

}