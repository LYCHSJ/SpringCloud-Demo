package com.alex.springcloud.configclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class HelloController {

    @Value("${target}")
    String target;

    @Autowired
    Environment environment;

    @RequestMapping("/target")
    public String getTarget(){
        return this.target;
    }

    @RequestMapping("/target2")
    public String getTargetFromEnv(){
        return environment.getProperty("target","为定义");
    }
}
