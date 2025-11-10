package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.*;
import com.livenne.common.model.*;
import com.livenne.common.model.vo.UserVO;
import com.livenne.service.impl.*;
import com.livenne.service.*;
import jakarta.servlet.http.HttpServletRequest;

@Controller("/user")
public class UserController {

    public UserService userService = UserServiceImpl.instance;
    public CourseService courseService = CourseServiceImpl.instance;
    public QuestionService questionService = QuestionServiceImpl.instance;
    public UserCourseService userCourseService = UserCourseServiceImpl.instance;
    public UserFavoriteService userFavoriteService = UserFavoriteServiceImpl.instance;
    public UserOrderService  userOrderService = UserOrderServiceImpl.instance;
    public UserShoppingCartService userShoppingCartService = UserShoppingCartServiceImpl.instance;
    public QuestionLikeService questionLikeService = QuestionLikeServiceImpl.instance;
    public CourseLikeService courseLikeService = CourseLikeServiceImpl.instance;
    public CourseCommentService courseCommentService = CourseCommentServiceImpl.instance;
    public QuestionCommentService questionCommentService = QuestionCommentServiceImpl.instance;

    @Autowired
    public HttpServletRequest request;

    @GetMapping
    public ResponseEntity<?> getUser(@RequestParm("userId") Long userId){
        User user = userService.get(userId);
        if (user == null) return ResponseEntity.failureMsg("");
        return ResponseEntity.success(new UserVO(user));
    }

    @GetMapping("/course/list")
    public ResponseEntity<?> getCourseList(){
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.success(userCourseService.getListByUserId(userId));
    }

    @GetMapping("/course/islike")
    public ResponseEntity<?> isCourseLike(@RequestParm("courseId") Long  courseId){
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.success(courseLikeService.isLike(userId,courseId));
    }

    @GetMapping("/course/isfavorite")
    public ResponseEntity<?> isCourseFavorite(@RequestParm("courseId") Long  courseId){
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.success(userFavoriteService.isFavorite(userId,courseId));
    }

    @GetMapping("/shoppingcart/join")
    public ResponseEntity<?> shoppingCartJoin(@RequestParm("courseId") Long courseId){
        Long userId = (Long) request.getAttribute("userId");
        userShoppingCartService.add(new UserShoppingCart(null,userId,courseId));
        return ResponseEntity.success(null);
    }
    @GetMapping("/shoppingcart/leave")
    public ResponseEntity<?> shoppingCartLeave(@RequestParm("courseId") Long courseId){
        Long userId = (Long) request.getAttribute("userId");
        userShoppingCartService.delete(userId,courseId);
        return ResponseEntity.success(null);
    }
    @GetMapping("/shoppingcart/list")
    public ResponseEntity<?> getShoppingCartList(){
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.success(userShoppingCartService.getListByUserId(userId));
    }
    @GetMapping("/order/list")
    public ResponseEntity<?> getOrderList(){
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.success(userOrderService.getListByUserId(userId));
    }
    @GetMapping("/question/list")
    public ResponseEntity<?> getQuestionList(){
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.success(questionService.getListByUserId(userId));
    }

    @GetMapping("/question/islike")
    public ResponseEntity<?> isQuestionLike(@RequestParm("questionId") Long questionId){
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.success(questionLikeService.isLike(userId,questionId));
    }

}
