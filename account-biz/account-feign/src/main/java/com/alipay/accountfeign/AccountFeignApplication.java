package com.alipay.accountfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.alipay.accountfeign.feign")
public class AccountFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountFeignApplication.class, args);
    }

}
