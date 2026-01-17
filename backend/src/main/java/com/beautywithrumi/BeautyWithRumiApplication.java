package com.beautywithrumi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Application Class for Beauty With Rumi E-Commerce Platform
 * 
 * This is the entry point for the Spring Boot application.
 * @SpringBootApplication enables auto-configuration and component scanning.
 */
@SpringBootApplication
public class BeautyWithRumiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeautyWithRumiApplication.class, args);
        System.out.println("\n✨ Beauty With Rumi Backend is running on http://localhost:8080 ✨\n");
    }
}
