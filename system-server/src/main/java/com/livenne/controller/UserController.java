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
    public QuestionService questionService = QuestionServiceImpl.instance;
    public UserCourseService userCourseService = UserCourseServiceImpl.instance;
    public UserFavoriteService userFavoriteService = UserFavoriteServiceImpl.instance;
    public UserOrderService  userOrderService = UserOrderServiceImpl.instance;
    public UserShoppingCartService userShoppingCartService = UserShoppingCartServiceImpl.instance;
    public QuestionLikeService questionLikeService = QuestionLikeServiceImpl.instance;
    public CourseLikeService courseLikeService = CourseLikeServiceImpl.instance;

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

    @GetMapping("/course/like")
    public ResponseEntity<?> courseLike(@RequestParm("courseId") Long courseId){
        return ResponseEntity.success(null);
    }
    @GetMapping("/course/favorite")
    public ResponseEntity<?> courseFavorite(@RequestParm("courseId") Long courseId){

        return ResponseEntity.success(null);
    }
    @GetMapping("/shoppingcart/join")
    public ResponseEntity<?> shoppingCartJoin(@RequestParm("courseId") Long courseId){

        return ResponseEntity.success(null);
    }
    @GetMapping("/shoppingcart/leave")
    public ResponseEntity<?> shoppingCartLeave(@RequestParm("courseId") Long courseId){

        return ResponseEntity.success(null);
    }
    @GetMapping("/shoppingcart/list")
    public ResponseEntity<?> getShoppingCartList(){
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.success(userShoppingCartService.getListByUserId(userId));
    }
    @GetMapping("/store")
    public ResponseEntity<?> store(@RequestParm("courseId") Long courseId){

        return ResponseEntity.success(null);
    }
    @GetMapping("/order/list")
    public ResponseEntity<?> getOrderList(){
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.success(userOrderService.getListByUserId(userId));
    }
    @PostMapping("/course/comment")
    public ResponseEntity<?> courseComment(@RequestBody CourseComment courseComment,@RequestParm("courseId") Long courseId){

        return ResponseEntity.success(null);
    }
    @GetMapping("/question/list")
    public ResponseEntity<?> getQuestionList(){
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.success(questionService.getListByUserId(userId));
    }
    @GetMapping("/question/like")
    public ResponseEntity<?> questionLike(@RequestParm("questionId") Long questionId){

        return ResponseEntity.success(null);
    }
    @PostMapping("/question/comment")
    public ResponseEntity<?> questionComment(@RequestBody QuestionComment questionComment,@RequestParm("questionId") Long questionId){

        return ResponseEntity.success(null);
    }
}
