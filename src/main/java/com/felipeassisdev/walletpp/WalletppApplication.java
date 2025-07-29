package com.felipeassisdev.walletpp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WalletppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletppApplication.class, args);
    }

}
