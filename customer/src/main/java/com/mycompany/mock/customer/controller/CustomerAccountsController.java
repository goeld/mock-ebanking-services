package com.mycompany.mock.customer.controller;

import com.mycompany.mock.customer.entity.AccountDetails;
import com.mycompany.mock.customer.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerAccountsController {

    @Autowired
    private CustomerAccountService customerAccountService;
    @GetMapping("/customer/account/{customerId}/{currency}")
    public AccountDetails getCustomerAccount(@PathVariable String customerId, @PathVariable String currency){
        return customerAccountService.getCustomerAccountDetails(customerId, currency);
    }

}
