package com.alex.springcloud.feignconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("hello-service")
public interface HelloService {
    @RequestMapping("/hello")
    String hello();
}
