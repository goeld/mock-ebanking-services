package com.mycompany.mock.app.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppHealthCheckController {
    @GetMapping("/app/health")
    public String getVersion() {
        return "1.0";
    }

}