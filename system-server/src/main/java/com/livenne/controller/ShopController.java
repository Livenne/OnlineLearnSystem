package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.*;
import com.livenne.common.model.dto.UserShoppingCartDTO;
import com.livenne.common.model.entity.Course;
import com.livenne.common.model.vo.CourseVO;
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
    public ResponseEntity<Boolean> buyCourse(@PathVariable("courseId") Long courseId,@Attribute("userId") Long userId){
        if (!courseService.isExistById(courseId)){
            return ResponseEntity.failureMsg("课程: %d 不存在", courseId);
        }
        long diff = userService.getById(userId).getScore() - courseService.getById(courseId).getPrice();
        if (diff < 0){
            return ResponseEntity.success(false,"积分不足,你还需要 %d 积分", Math.abs(diff));
        }

        return ResponseEntity.success(shopService.buy(userId,courseId));
    }
    @PostMapping("/buy")
    public ResponseEntity<Boolean> buyCourse(@RequestBody List<Long> idList,@Attribute("userId") Long userId){
        long total = 0;
        for (Long courseId : idList) {
            if (!courseService.isExistById(courseId)){
                return ResponseEntity.failureMsg("课程: %d 不存在", courseId);
            }
            total += courseService.getById(courseId).getPrice();
        }
        long diff = userService.getById(userId).getScore() - total;
        if (diff < 0){
            return ResponseEntity.success(false,"积分不足,你还需要 %d 积分", Math.abs(diff));
        }
        return ResponseEntity.success(shopService.buy(userId,idList));
    }
    @PostMapping("/cart/join/{courseId}")
    public ResponseEntity<Boolean> joinCart(@PathVariable("courseId") Long courseId,@Attribute("userId") Long userId){
        if (!courseService.isExistById(courseId)){
            return ResponseEntity.failureMsg("课程: %d 不存在", courseId);
        }
        if (userService.getShoppingCart(userId).stream().map(Course::getCourseId).toList().contains(courseId)){
            return ResponseEntity.success(false,"课程已在购物车中");
        }
        return ResponseEntity.success(userService.joinCart(new UserShoppingCartDTO(userId,courseId)));
    }
    @PostMapping("/cart/leave/{courseId}")
    public ResponseEntity<Boolean> leaveCart(@PathVariable("courseId") Long courseId,@Attribute("userId") Long userId){
        if (!courseService.isExistById(courseId)){
            return ResponseEntity.failureMsg("课程: %d 不存在", courseId);
        }
        if (!userService.getShoppingCart(userId).stream().map(Course::getCourseId).toList().contains(courseId)){
            return ResponseEntity.success(false,"课程不在购物车中");
        }
        return ResponseEntity.success(userService.leaveCart(new UserShoppingCartDTO(userId, courseId)));
    }

    @GetMapping("/cart/list")
    public ResponseEntity<List<CourseVO>> getCart(@Attribute("userId") Long userId){
        return ResponseEntity.success(userService.getShoppingCart(userId)
                .stream()
                .map(Course::getCourseId)
                .map(id->courseController.getCourse(id,userId).getData())
                .toList());
    }
    @GetMapping("/order/list")
    public ResponseEntity<List<CourseVO>> getOrderList(@Attribute("userId") Long userId){
        return ResponseEntity.success(userService.getOrder(userId)
                .stream()
                .map(Course::getCourseId)
                .map(id->courseController.getCourse(id,userId).getData())
                .toList());
    }
}
