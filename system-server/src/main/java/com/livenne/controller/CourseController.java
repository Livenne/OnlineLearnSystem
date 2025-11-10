package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.*;
import com.livenne.common.model.Course;
import com.livenne.common.model.CourseComment;
import com.livenne.common.model.CourseLike;
import com.livenne.common.model.UserFavorite;
import com.livenne.service.*;
import com.livenne.service.impl.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@Controller("/course")
public class CourseController extends HttpServlet {

    public CourseService courseService = CourseServiceImpl.instance;
    public CourseItemService courseItemService = CourseItemServiceImpl.instance;
    public CourseCommentService courseCommentService = CourseCommentServiceImpl.instance;
    public CourseLikeService courseLikeService = CourseLikeServiceImpl.instance;
    public UserFavoriteService userFavoriteService = UserFavoriteServiceImpl.instance;

    @Autowired
    public HttpServletRequest request;

    @GetMapping
    public ResponseEntity<?> getCourse(@RequestParm("courseId") Long courseId){
        Course course = courseService.get(courseId);
        if (course == null) return ResponseEntity.failureMsg("");
        return ResponseEntity.success(course);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getCourseList(){
        return ResponseEntity.success(courseService.getList());
    }

    @GetMapping("/item")
    public ResponseEntity<?> getCourseItem(@RequestParm("courseId") Long courseId){
        return ResponseEntity.success(courseItemService.getListByCourseId(courseId));
    }

    @GetMapping("/comment")
    public  ResponseEntity<?> getCourseComment(@RequestParm("courseId") Long courseId){
        return ResponseEntity.success(courseCommentService.getListByCourseId(courseId));
    }
    @PostMapping("/comment")
    public ResponseEntity<?> courseComment(@RequestBody CourseComment courseComment){
        Long userId = (Long) request.getAttribute("userId");
        courseComment.setCommentId(null);
        courseComment.setUserId(userId);
        courseComment.setCreateTime(System.currentTimeMillis());
        courseCommentService.add(courseComment);
        return ResponseEntity.success(null);
    }


    @GetMapping("/like")
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

    @GetMapping("/favorite")
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

    @GetMapping("/store")
    public ResponseEntity<?> store(@RequestParm("courseId") Long courseId){

        return ResponseEntity.success(null);
    }

}
