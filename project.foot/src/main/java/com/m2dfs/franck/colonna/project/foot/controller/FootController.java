package com.m2dfs.franck.colonna.project.foot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FootController {

    @GetMapping("/result")
    public String myMethod(){
        return "result";
    }
}
