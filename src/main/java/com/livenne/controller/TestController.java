package com.livenne.controller;

import io.github.livenne.ResponseEntity;
import io.github.livenne.annotation.container.*;
import io.github.livenne.annotation.servlet.*;

@Controller("/test")
public class TestController {

    @GetMapping
    public ResponseEntity test(){
        System.out.println("test");
        return ResponseEntity.ok().message("Test Hello!你好");
    }
}
