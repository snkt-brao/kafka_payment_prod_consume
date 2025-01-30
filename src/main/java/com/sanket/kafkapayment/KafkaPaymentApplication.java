package com.sanket.kafkapayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.sanket.kafkapayment")
public class KafkaPaymentApplication {


    public static void main(String[] args) {
        SpringApplication.run(KafkaPaymentApplication.class, args);
    }

}
