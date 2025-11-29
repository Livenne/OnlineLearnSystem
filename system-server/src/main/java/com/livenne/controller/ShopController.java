package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.*;
import com.livenne.common.model.Course;

import java.util.List;

@Controller("/shop")
public class ShopController {
    @GetMapping("/buy/{courseId}")
    public ResponseEntity<?> getBuyCourse(@PathVariable("courseId") String courseId){
        return ResponseEntity.success();
    }
    @PostMapping("/buy")
    public ResponseEntity<?> buyCourse(@RequestBody List<Long> idList){
        return ResponseEntity.success();
    }
    @GetMapping("/cart/join/{courseId}")
    public ResponseEntity<?> getJoinCourse(@PathVariable("courseId") String courseId){
        return ResponseEntity.success();
    }
    @GetMapping("/cart/leave/{courseId}")
    public ResponseEntity<?> getLeaveCourse(@PathVariable("courseId") String courseId){
        return ResponseEntity.success();
    }
    @GetMapping("/cart/list}")
    public ResponseEntity<?> getCartList(){
        return ResponseEntity.success();
    }
    @GetMapping("/order/list}")
    public ResponseEntity<?> getOrderList(){
        return ResponseEntity.success();
    }
    @GetMapping("/order/search}")
    public ResponseEntity<?> getOrderSearchList(){
        return ResponseEntity.success();
    }
}
