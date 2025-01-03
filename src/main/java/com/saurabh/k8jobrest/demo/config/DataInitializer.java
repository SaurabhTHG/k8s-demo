package com.saurabh.k8jobrest.demo.config;

import com.saurabh.k8jobrest.demo.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initializeDatabase(ProductService productService) {
        return args -> productService.initializeProducts();
    }
}

