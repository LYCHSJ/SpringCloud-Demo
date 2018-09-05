package com.alex.springcloud.feignconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

@FeignClient("hello-service")
public interface HelloService {
    @RequestMapping("/hello")
    String hello();

    @RequestMapping(value = "/hello_with_name", method = RequestMethod.GET)
    String hello(@RequestParam String name);

    @RequestMapping(value = "/hello_in_header", method = RequestMethod.GET)
    Book hello(@RequestHeader("name") String name, @RequestHeader("author") String author,
               @RequestHeader("price") Integer price);

    @RequestMapping(value = "/hello_in_body", method = RequestMethod.POST)
    String hello(@RequestBody Book book);
}
