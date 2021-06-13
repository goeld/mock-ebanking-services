package com.mycompany.mock.app.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppHealthCheckController {
    @GetMapping("/mock/health")
    public String getVersion() {
        return "1.0";
    }


    @GetMapping("/")
    public String index() {
        return "Mock Portal is up";
    }

}