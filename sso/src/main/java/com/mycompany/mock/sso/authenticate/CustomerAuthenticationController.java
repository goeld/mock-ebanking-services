package com.mycompany.mock.sso.authenticate;

import com.mycompany.mock.sso.entity.CustomerDetails;
import com.mycompany.mock.sso.service.CustomerAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerAuthenticationController {

    @Autowired
    private CustomerAuthenticationService customerAuthenticationService;

    @GetMapping("/sso/authenticate/customer")
    public CustomerDetails authenticateCustomer(@RequestParam String token) throws Exception {
        return customerAuthenticationService.authenticateToken(token);
    }
}
