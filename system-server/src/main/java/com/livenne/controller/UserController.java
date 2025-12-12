package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.*;
import com.livenne.common.model.dto.QuestionLikeDTO;
import com.livenne.common.model.entity.Course;
import com.livenne.common.model.vo.CourseVO;
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
    public ResponseEntity<UserVO> getUser(@PathVariable("userId") Long userId){
        if (!userService.isExistById(userId)){
            return ResponseEntity.failureMsg("用户: %d 不存在", userId);
        }
        return ResponseEntity.success(new UserVO(userService.getById(userId)));
    }

    @PostMapping("/info")
    public ResponseEntity<List<UserVO>> getList(@RequestBody List<Long> idList){
        return ResponseEntity.success(userService.getByIdList(idList).stream().map(UserVO::new).toList());
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileVO> getProfile(@Attribute("userId") Long userId){
        return ResponseEntity.success(new UserProfileVO(userService.getById(userId)));
    }

    @GetMapping("/course")
    public ResponseEntity<List<CourseVO>> getCourse(@Attribute("userId") Long userId){
        return ResponseEntity.success(courseService.getByUserId(userId)
                .stream()
                .map(Course::getCourseId)
                .map(id->courseController.getCourse(id,userId).getData())
                .toList());
    }

    @GetMapping("/question")
    public ResponseEntity<List<QuestionVO>> getQuestion(@Attribute("userId") Long userId){
        return ResponseEntity.success(questionService.getByUserId(userId)
                .stream()
                .map(question -> QuestionVO.builder()
                        .questionId(question.getQuestionId())
                        .question(question.getQuestion())
                        .createTime(question.getCreateTime())
                        .imageUrl(question.getImageUrl())
                        .userId(question.getUserId())
                        .likes(questionService.likes(question.getQuestionId()))
                        .like(questionService.isLike(new QuestionLikeDTO(userId,question.getQuestionId())))
                        .comments(questionService.comments(question.getQuestionId()))
                        .build()
                )
                .toList());
    }
}
