package com.alex.springcloud.springcloudproviderhello.controller;

import com.alex.springcloud.RestTemplate.entity.Book;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index(){
        List<ServiceInstance> instances = client.getInstances("hello-Service");
        for(int i=0;i<instances.size();i++){
            logger.info("/hello,host:"+instances.get(i).getHost()+"service_id"+instances.get(i).getServiceId());
        }
        return "Hello World";
    }

    @RequestMapping(value = "/hello_with_name", method = RequestMethod.GET)
    public String helloWithName(@RequestParam String name){
        StringBuffer sb = new StringBuffer()
                .append("hello,")
                .append(name);
        return sb.toString();
    }

    @RequestMapping(value = "/hello_in_header", method = RequestMethod.GET)
    public Book helloInHeader(@RequestHeader String name, @RequestHeader String author,
                              @RequestHeader Integer price, @RequestHeader String publisher){
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPrice(price);
        book.setPublisher(publisher);
        return book;
    }

    @RequestMapping(value = "/hello_in_body", method = RequestMethod.POST)
    public String helloInBody(@RequestBody Book book){
        StringBuffer sb = new StringBuffer()
                .append("this book: ")
                .append(book.getName())
                .append(" is not bad.");
        return sb.toString();
    }
}
