package com.coal.contorller.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("test")
    public String getTest(String test){
        System.out.println("getTest = " + test);
        return test;
    }
    @PostMapping ("test")
    public String postTest(String test){
        System.out.println("postTest = " + test);
        return test;
    }
}
