package com.saurabh.k8jobrest.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class K8JobRestDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(K8JobRestDemoApplication.class, args);
    }

}
