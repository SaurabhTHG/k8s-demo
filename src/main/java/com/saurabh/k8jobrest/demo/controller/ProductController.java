package com.saurabh.k8jobrest.demo.controller;

import com.saurabh.k8jobrest.demo.entity.Product;
import com.saurabh.k8jobrest.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        System.out.println("Running in POD : "+ System.getenv("HOSTNAME"));
        return productService.getAllProducts(page, size);
    }

    @PatchMapping("/{id}/rating")
    public Product updateProductRating(@PathVariable Long id, @RequestParam Double rating) {
        return productService.updateProductRating(id, rating);
    }
}

