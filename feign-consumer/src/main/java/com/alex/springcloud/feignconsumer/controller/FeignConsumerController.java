package com.alex.springcloud.feignconsumer.controller;

import com.alex.springcloud.RestTemplate.entity.Book;
import com.alex.springcloud.feignconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
public class FeignConsumerController {
    @Autowired
    HelloService helloService;

    @RequestMapping("/hello")
    public String hello(){
        return helloService.hello();
    }

    @RequestMapping("/hello_with_name")
    public String helloWithName(){
        return helloService.hello("王尼玛");
    }

    @RequestMapping("/hello_in_header")
    public Book bookInHeader() throws UnsupportedEncodingException{
        Book book = helloService.hello(URLEncoder.encode("如何学习Spring-Cloud","utf-8"),
                URLEncoder.encode("Silence Celin","utf-8"),998,
                URLEncoder.encode("Plan B","utf-8"));
        return book;
    }

    @RequestMapping("/hello_in_body")
    public String bookInBody() {
        Book book = new Book();
        book.setName("<<爸爸在这儿>>");
        book.setAuthor("Silence Celin");
        book.setPrice(998);
        book.setPublisher("Plan B");
        return helloService.hello(book);
    }
}
