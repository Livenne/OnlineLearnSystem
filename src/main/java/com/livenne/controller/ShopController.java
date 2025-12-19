package com.livenne.controller;

import io.github.livenne.ResponseEntity;
import com.livenne.common.model.dto.UserShoppingCartDTO;
import com.livenne.common.model.entity.Course;
import io.github.livenne.annotation.container.*;
import io.github.livenne.annotation.servlet.*;
import com.livenne.service.CourseService;
import com.livenne.service.ShopService;
import com.livenne.service.UserService;

import java.util.List;

@Controller("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseController courseController;

    @PostMapping("/buy/{courseId}")
    public ResponseEntity buyCourse(@PathVariable("courseId") Long courseId,@Attribute("userId") Long userId){
        if (!courseService.isExistById(courseId)){
            return ResponseEntity.notFound().message("课程: %d 不存在", courseId);
        }
        long diff = userService.getById(userId).getScore() - courseService.getById(courseId).getPrice();
        if (diff < 0){
            return ResponseEntity.err(false).message("积分不足,你还需要 %d 积分", Math.abs(diff));
        }

        return ResponseEntity.ok(shopService.buy(userId,courseId));
    }
    @PostMapping("/buy")
    public ResponseEntity buyCourse(@RequestBody List<Long> idList,@Attribute("userId") Long userId){
        long total = 0;
        for (Long courseId : idList) {
            if (!courseService.isExistById(courseId)){
                return ResponseEntity.notFound().message("课程: %d 不存在", courseId);
            }
            total += courseService.getById(courseId).getPrice();
        }
        long diff = userService.getById(userId).getScore() - total;
        if (diff < 0){
            return ResponseEntity.err(false).message("积分不足,你还需要 %d 积分", Math.abs(diff));
        }
        return ResponseEntity.ok(shopService.buy(userId,idList));
    }
    @PostMapping("/cart/join/{courseId}")
    public ResponseEntity joinCart(@PathVariable("courseId") Long courseId,@Attribute("userId") Long userId){
        if (!courseService.isExistById(courseId)){
            return ResponseEntity.notFound().message("课程: %d 不存在", courseId);
        }
        if (userService.getShoppingCart(userId).stream().map(Course::getCourseId).toList().contains(courseId)){
            return ResponseEntity.err(false).message("课程已在购物车中");
        }
        return ResponseEntity.ok(userService.joinCart(new UserShoppingCartDTO(userId,courseId)));
    }
    @PostMapping("/cart/leave/{courseId}")
    public ResponseEntity leaveCart(@PathVariable("courseId") Long courseId,@Attribute("userId") Long userId){
        if (!courseService.isExistById(courseId)){
            return ResponseEntity.notFound().message("课程: %d 不存在", courseId);
        }
        if (!userService.getShoppingCart(userId).stream().map(Course::getCourseId).toList().contains(courseId)){
            return ResponseEntity.err(false).message("课程不在购物车中");
        }
        return ResponseEntity.ok(userService.leaveCart(new UserShoppingCartDTO(userId, courseId)));
    }

    @GetMapping("/cart/list")
    public ResponseEntity getCart(@Attribute("userId") Long userId){
        return ResponseEntity.ok(userService.getShoppingCart(userId)
                .stream()
                .map(Course::getCourseId)
                .map(id->courseController.getCourse(id,userId).getData())
                .toList());
    }
    @GetMapping("/order/list")
    public ResponseEntity getOrderList(@Attribute("userId") Long userId){
        return ResponseEntity.ok(userService.getOrder(userId)
                .stream()
                .map(Course::getCourseId)
                .map(id->courseController.getCourse(id,userId).getData())
                .toList());
    }
}
