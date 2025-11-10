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

    @GetMapping("/course/like")
    public ResponseEntity<?> courseLike(@RequestParm("courseId") Long courseId){
        Long userId = (Long) request.getAttribute("userId");
        if (courseId == null) return ResponseEntity.failureMsg("");
        Course course = courseService.get(courseId);
        if (courseLikeService.isLike(userId, courseId)) {
            courseLikeService.delete(userId, courseId);
            course.setLikes(course.getLikes() - 1);
            courseService.update(course);
        }else {
            courseLikeService.add(new CourseLike(null, userId, courseId));
            course.setLikes(course.getLikes() + 1);
            courseService.update(course);
        }
        return ResponseEntity.success(null);
    }
    @GetMapping("/course/favorite")
    public ResponseEntity<?> courseFavorite(@RequestParm("courseId") Long courseId){
        Long userId = (Long) request.getAttribute("userId");
        if (courseId == null) return ResponseEntity.failureMsg("");
        Course course = courseService.get(courseId);
        if (userFavoriteService.isFavorite(userId, courseId)) {
            userFavoriteService.delete(userId, courseId);
            course.setFavorites(course.getLikes() - 1);
            courseService.update(course);
        }else {
            userFavoriteService.add(new UserFavorite(null, userId, courseId));
            course.setLikes(course.getLikes() + 1);
            courseService.update(course);
        }
        return ResponseEntity.success(null);
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
    public ResponseEntity<?> courseComment(@RequestBody CourseComment courseComment){
        Long userId = (Long) request.getAttribute("userId");
        courseComment.setCommentId(null);
        courseComment.setUserId(userId);
        courseComment.setCreateTime(System.currentTimeMillis());
        courseCommentService.add(courseComment);
        return ResponseEntity.success(null);
    }
    @GetMapping("/question/list")
    public ResponseEntity<?> getQuestionList(){
        Long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.success(questionService.getListByUserId(userId));
    }
    @GetMapping("/question/like")
    public ResponseEntity<?> questionLike(@RequestParm("questionId") Long questionId){
        Long userId = (Long) request.getAttribute("userId");
        if (questionId == null) return ResponseEntity.failureMsg("");
        Question question = questionService.get(questionId);
        if (questionLikeService.isLike(userId, questionId)) {
            questionLikeService.delete(userId, questionId);
            question.setLikes(question.getLikes() - 1);
            questionService.update(question);
        }else {
            questionLikeService.add(new QuestionLike(null, userId, questionId));
            question.setLikes(question.getLikes() + 1);
            questionService.update(question);
        }
        return ResponseEntity.success(null);
    }
    @PostMapping("/question/comment")
    public ResponseEntity<?> questionComment(@RequestBody QuestionComment questionComment){
        Long userId = (Long) request.getAttribute("userId");
        questionComment.setCommentId(null);
        questionComment.setUserId(userId);
        questionComment.setCreateTime(System.currentTimeMillis());
        questionCommentService.add(questionComment);
        return ResponseEntity.success(null);
    }
}
