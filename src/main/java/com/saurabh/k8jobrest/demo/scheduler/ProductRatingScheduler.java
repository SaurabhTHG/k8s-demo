package com.saurabh.k8jobrest.demo.scheduler;

import com.saurabh.k8jobrest.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProductRatingScheduler {

    @Autowired
    private ProductService productService;

    @Scheduled(initialDelay = 10000, fixedRate = 30000) // 30 sec
    public void updateProductRatings() {
        // Mock third-party service response
        Map<Long, Double> updatedRatings = Map.of(
                1L, 4.5,
                2L, 3.8
        );
        for (Map.Entry<Long, Double> entry : updatedRatings.entrySet()) {
            productService.updateProductRating(entry.getKey(), entry.getValue());
        }

    }
}

