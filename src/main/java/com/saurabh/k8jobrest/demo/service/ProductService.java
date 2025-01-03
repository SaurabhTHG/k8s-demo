package com.saurabh.k8jobrest.demo.service;

import com.saurabh.k8jobrest.demo.entity.Product;
import com.saurabh.k8jobrest.demo.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts(int page, int size) {
        return productRepository.findAll();
    }

    public Product updateProductRating(Long id, Double newRating) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        Double old = product.getRating();
        product.setRating(newRating);
        product.setUpdatedAt(java.time.LocalDateTime.now());
        log.debug("updated product ratings for id:{}, oldRating:{}, rating:{}", id, old, newRating);

        return productRepository.save(product);
    }

    public void initializeProducts() {
        log.debug("Initializing products");
        for (int i = 1; i <= 100; i++) {
            Product product = new Product();
            product.setName("Product " + i);
            product.setDescription("Description for product " + i);
            product.setPrice((double) (i * 10));
            product.setCategory("Category " + (i % 5 + 1));
            product.setTags("Tag" + (i % 3 + 1));
            product.setRating(4.0);
            product.setCreatedAt(java.time.LocalDateTime.now());
            product.setUpdatedAt(java.time.LocalDateTime.now());
            productRepository.save(product);
        }
        log.debug("Finished initializing products");
    }
}

