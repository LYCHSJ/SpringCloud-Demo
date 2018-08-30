package com.alex.springcloud.resttemplate.provider.controller;

import com.alex.springcloud.RestTemplate.entity.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderBookController {

    @RequestMapping("/book1")
    public Book book1(){
        return new Book("巴黎圣母院",80,"大仲马","译林出版社");
    }


}
