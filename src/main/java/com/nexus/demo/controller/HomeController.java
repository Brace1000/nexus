package com.nexus.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Nexus Artifact Management Demo Application!";
    }

    @GetMapping("/version")
    public String version() {
        return "Version: 1.0.0-SNAPSHOT";
    }

    @GetMapping("/health")
    public String health() {
        return "Application is running";
    }
}
