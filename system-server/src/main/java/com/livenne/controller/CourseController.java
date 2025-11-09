package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.Controller;
import com.livenne.annotation.GetMapping;
import com.livenne.annotation.RequestParm;
import com.livenne.common.model.Course;
import com.livenne.service.CourseCommentService;
import com.livenne.service.CourseItemService;
import com.livenne.service.CourseService;
import com.livenne.service.impl.CourseCommentServiceImpl;
import com.livenne.service.impl.CourseItemServiceImpl;
import com.livenne.service.impl.CourseServiceImpl;
import jakarta.servlet.http.HttpServlet;

@Controller("/course")
public class CourseController extends HttpServlet {

    public CourseService courseService = CourseServiceImpl.instance;
    public CourseItemService courseItemService = CourseItemServiceImpl.instance;
    public CourseCommentService courseCommentService = CourseCommentServiceImpl.instance;

    @GetMapping
    public ResponseEntity<?> getCourse(@RequestParm("courseId") Long courseId){
        Course course = courseService.get(courseId);
        if (course == null) return ResponseEntity.failureMsg("");
        return ResponseEntity.success(course);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getCourseList(){
        System.out.println("test");
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

}
