package com.livenne.controller;

import io.github.livenne.ResponseEntity;
import com.livenne.common.model.dto.QuestionLikeDTO;
import com.livenne.common.model.entity.Course;
import com.livenne.common.model.entity.User;
import io.github.livenne.annotation.container.*;
import io.github.livenne.annotation.servlet.*;
import com.livenne.common.model.vo.QuestionVO;
import com.livenne.common.model.vo.UserProfileVO;
import com.livenne.common.model.vo.UserVO;
import com.livenne.service.impl.*;
import com.livenne.service.*;

import java.util.List;

@Controller("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CourseController courseController;

    @GetMapping("/info/{userId}")
    public ResponseEntity getUser(@PathVariable("userId") Long userId){
        if (!userService.isExistById(userId)){
            return ResponseEntity.notFound().message("用户: %d 不存在", userId);
        }
        return ResponseEntity.ok(new UserVO(userService.getById(userId)));
    }

    @PostMapping("/info")
    public ResponseEntity getList(@RequestBody List<Long> idList){
        return ResponseEntity.ok(userService.getByIdList(idList).stream().map(UserVO::new).toList());
    }

    @GetMapping("/profile")
    public ResponseEntity getProfile(@Attribute("userId") Long userId){
        return ResponseEntity.ok(new UserProfileVO(userService.getById(userId)));
    }

    @GetMapping("/course")
    public ResponseEntity getCourse(@Attribute("userId") Long userId){
        return ResponseEntity.ok(courseService.getByUserId(userId)
                .stream()
                .map(Course::getCourseId)
                .map(id->courseController.getCourse(id,userId).getData())
                .toList());
    }

    @GetMapping("/question")
    public ResponseEntity getQuestion(@Attribute("userId") Long userId){
        User user = userService.getById(userId);
        return ResponseEntity.ok(questionService.getByUserId(userId)
                .stream()
                .map(question -> QuestionVO.builder()
                        .questionId(question.getQuestionId())
                        .question(question.getQuestion())
                        .createTime(question.getCreateTime())
                        .avatarUrl(user.getAvatarUrl())
                        .username(user.getUsername())
                        .likes(questionService.likes(question.getQuestionId()))
                        .like(questionService.isLike(new QuestionLikeDTO(userId,question.getQuestionId())))
                        .comments(questionService.comments(question.getQuestionId()))
                        .build()
                )
                .toList());
    }
}
