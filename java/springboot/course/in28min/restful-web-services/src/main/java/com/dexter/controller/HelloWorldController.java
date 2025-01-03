package com.dexter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dexter.bean.HelloWorldBean;

@RestController
public class HelloWorldController {
    
    @RequestMapping(method=RequestMethod.GET, path="/hello")
    public String getHello(){
        return "String : Hello";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean getHelloBean(){
        return new HelloWorldBean("HelloWorldBean : Hello World");
    }

    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorldBean getHelloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello,  %s", name));
    }
}


