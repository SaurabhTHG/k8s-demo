package com.saurabh.k8jobrest.demo.repository;

import com.saurabh.k8jobrest.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

