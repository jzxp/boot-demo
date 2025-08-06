package org.example.demo.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("get")
public class TestController {



    @GetMapping("/get")
    public String get(){
        return "ceshi";
    }

    @GetMapping("/set")
    public String set(){
        return "ceshi";
    }
}
