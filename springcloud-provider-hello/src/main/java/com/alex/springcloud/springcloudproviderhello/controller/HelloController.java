package com.alex.springcloud.springcloudproviderhello.controller;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
