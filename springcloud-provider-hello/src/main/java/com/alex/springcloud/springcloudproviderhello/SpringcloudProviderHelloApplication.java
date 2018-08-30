package com.alex.springcloud.springcloudproviderhello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudProviderHelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudProviderHelloApplication.class, args);
    }
}
