package com.example.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/hello"})
public class helloController {

    @RequestMapping(value = {"/springboot"})
    public String hello(){
        System.out.println("fenzhi");
        System.out.println("hello world");
        System.out.println("hello worldasda");
        return "HelloWord";
    }
}

