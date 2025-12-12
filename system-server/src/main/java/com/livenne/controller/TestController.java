package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.Autowired;
import com.livenne.annotation.Controller;
import com.livenne.annotation.GetMapping;
import com.livenne.annotation.RequestParm;
import com.livenne.repository.UserRepository;
import com.livenne.service.UserService;

@Controller("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> test(@RequestParm("userId") Long userId){
        return ResponseEntity.success(userService.isExistById(userId));
    }
    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParm("username") String username){
        return ResponseEntity.success(userService.getByName(username));
    }
}
