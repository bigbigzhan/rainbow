//package com.github.rainbow.Controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.env.Environment;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class Test {
//
//    @Autowired
//    private Environment environment;
//    @Value("${config.username:gz}")
//    private String name;
//
//
//    @GetMapping("/test")
//    public String test(){
//        return environment.getProperty("config.username") + name;
//    }
//}
