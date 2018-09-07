package com.alex.springcloud.feignconsumer.service;

import com.alex.springcloud.RestTemplate.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient("hello-service")
public interface HelloService {
    @RequestMapping("/hello")
    String hello();

    @RequestMapping(value = "/hello_with_name", method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    @RequestMapping(value = "/hello_in_header", method = RequestMethod.GET)
    Book hello(@RequestHeader("name") String name, @RequestHeader("author") String author,
               @RequestHeader("price") Integer price, @RequestHeader("publisher") String publisher);

    @RequestMapping(value = "/hello_in_body", method = RequestMethod.POST)
    String hello(@RequestBody Book book);
}
