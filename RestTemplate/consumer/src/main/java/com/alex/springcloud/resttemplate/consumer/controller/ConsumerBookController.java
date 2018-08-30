package com.alex.springcloud.resttemplate.consumer.controller;

import com.alex.springcloud.RestTemplate.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class ConsumerBookController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/gethello")
    public String getHello(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class);
        String body = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();
        Integer statusCodeValue = responseEntity.getStatusCodeValue();
        HttpHeaders httpHeaders = responseEntity.getHeaders();
        StringBuffer result = new StringBuffer()
                .append("responseEntity.getBody():").append(body).append("<hr>")
                .append("responseEntity.getStatusCode()").append(statusCode).append("<hr>")
                .append("responseEntity.getStatusCodeValue():").append(statusCodeValue).append("<hr>")
                .append("responseEntity.getHeaders()").append(httpHeaders).append("<hr>");
        return result.toString();
    }

    /**
     * 通过url传递参数
     * @return
     */
    @RequestMapping(value = "/sayhello")
    public String sayHello(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/hello?name={1}",String.class,"张三");
        return responseEntity.getBody();
    }

    /**
     * 通过Map传递参数
     * @return
     */
    @RequestMapping("/sayhello2")
    public String sayHello2(){
        Map<String,String> map = new HashMap<>();
        map.put("name","李四");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/hello?name={name}",String.class,map);
        return responseEntity.getBody();
    }

    /**
     * 通过Spring中提供的UriComponents来构建Uri传递参数
     * @return
     */
    @RequestMapping("/sayhello3")
    public String  sayHello3(){
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://HELLO-SERVICE/hello?name={name}").build().expand("王五").encode();
        URI uri = uriComponents.toUri();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri,String.class);
        return responseEntity.toString();
    }

    /**
     * 返回Book类Json对象
     * @return
     */
    @RequestMapping("/book1")
    public Book book1() {
        ResponseEntity<Book> responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/getbook1", Book.class);
        return responseEntity.getBody();
    }

    /**
     * 通过getForObject方法获取返回的Book类Json对象
     * @return
     */
    @RequestMapping("/book2")
    public Book book2() {
        Book book = restTemplate.getForObject("http://HELLO-SERVICE/getbook1", Book.class);
        return book;
    }

    /**
     * post方法提交请求
     * @return
     */
    @RequestMapping("/book3")
    public Book book3() {
        Book book = new Book();
        book.setName("红楼梦");
        ResponseEntity<Book> responseEntity = restTemplate.postForEntity("http://HELLO-SERVICE/getbook2", book, Book.class);
        return responseEntity.getBody();
    }

    /**
     * put方法提交请求
     */
    @RequestMapping("/put")
    public void put() {
        Book book = new Book();
        book.setName("红楼梦");
        restTemplate.put("http://HELLO-SERVICE/getbook3/{1}", book, 99);
    }

    @RequestMapping("/delete")
    public void delete() {
        restTemplate.delete("http://HELLO-SERVICE/getbook4/{1}", 100);
    }
}
