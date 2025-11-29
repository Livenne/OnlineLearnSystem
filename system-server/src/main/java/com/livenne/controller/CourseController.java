package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.*;
import com.livenne.common.model.*;
import com.livenne.service.*;
import com.livenne.service.impl.*;
import jakarta.servlet.http.HttpServlet;

import java.util.List;

@Controller("/course")
public class CourseController{

    @Autowired
    private CourseService courseService;

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable("courseId") Long courseId){
        return ResponseEntity.success(courseService.getById(courseId));
    }

    @GetMapping("/{courseId}/like")
    public ResponseEntity<?> getLikeCourse(@PathVariable("courseId") Long courseId){
        return ResponseEntity.success();
    }
    @GetMapping("/{courseId}/favorite")
    public ResponseEntity<?> getFavoriteCourse(@PathVariable("courseId") Long courseId){
        return ResponseEntity.success();
    }
    @GetMapping("/{courseId}/chapter")
    public ResponseEntity<?> getChapter(@PathVariable("courseId") Long courseId){
        return ResponseEntity.success();
    }
    @GetMapping("/{courseId}/comment/list")
    public ResponseEntity<?> getCommentList(@PathVariable("courseId") Long courseId){
        return ResponseEntity.success();
    }
    @GetMapping("/{courseId}/comment/like")
    public ResponseEntity<?> getLikeComment(@PathVariable("courseId") Long courseId){
        return ResponseEntity.success();
    }
    @PostMapping("/{courseId}/comment")
    public ResponseEntity<?> addComment(@PathVariable("courseId") Long courseId){
        return ResponseEntity.success();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Course>> getAll(){
        return ResponseEntity.success(courseService.getAll());
    }

    @PostMapping("/list")
    public ResponseEntity<List<Course>> getList(@RequestBody List<Long> idList){
        return ResponseEntity.success(courseService.getByIdList(idList));
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParm("kw") String kw){
        return ResponseEntity.success();
    }
}
