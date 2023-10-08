package com.hs.selab.health.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthRestController {
    @GetMapping("/health")
    public String health() {
        return "health good!~";
    }
}
