package com.saurabh.k8jobrest.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class K8JobRestDemoApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(K8JobRestDemoApplication.class, args);
//    }



    public static void main(String[] args) throws InterruptedException {
        SpringApplication app = new SpringApplication(K8JobRestDemoApplication.class);
        if ("CRONJOB".equalsIgnoreCase(System.getenv("RESOURCE_TYPE"))) {
            System.out.println("######### Job started ########");
            app.setWebApplicationType(WebApplicationType.NONE);
            app.run(args);
            System.out.println("*****Host Name: "+ System.getenv("HOSTNAME"));
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
                Thread.sleep(1000);
            }
        } else  {
            System.out.println("***************** REST started ******************");
            app.run(args);
        }}
}


