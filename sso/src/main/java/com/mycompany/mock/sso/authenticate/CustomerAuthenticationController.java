package com.mycompany.mock.sso.authenticate;

import com.mycompany.mock.sso.entity.CustomerDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerAuthenticationController {

    private static List<String> validCustomerIds;

    @GetMapping("/sso/authenticate/customer")
    public CustomerDetails authenticateCustomer(@RequestParam String token) {

        if (validCustomerIds == null) {
            validCustomerIds = new ArrayList<>();
            validCustomerIds.add("P-0123456789");
            validCustomerIds.add("P-0123456790");
            validCustomerIds.add("P-0123456791");
        }


        String customerId = validCustomerIds.stream().filter(id -> token.contains(id)).collect(Collectors.toList()).get(0);
        return new CustomerDetails(customerId);
    }
}
