package com.livenne.controller;

import io.github.livenne.ResponseEntity;
import com.livenne.common.model.dto.QuestionCommentDTO;
import com.livenne.common.model.dto.QuestionDTO;
import com.livenne.common.model.dto.QuestionLikeDTO;
import com.livenne.common.model.entity.Question;
import com.livenne.common.model.entity.User;
import com.livenne.common.model.vo.QuestionCommentVO;
import com.livenne.common.model.vo.QuestionVO;
import com.livenne.service.QuestionService;
import com.livenne.service.UserService;
import io.github.livenne.annotation.container.*;
import io.github.livenne.annotation.servlet.*;

import java.util.List;

@Controller("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;

    @GetMapping("/get/{questionId}")
    public ResponseEntity getQuestion(@PathVariable("questionId") Long questionId,@Attribute("userId") Long userId){
        if (!questionService.isExistById(questionId)){
            return ResponseEntity.notFound().message("问题: %d 不存在", questionId);
        }
        Question question = questionService.getById(questionId);
        User user = userService.getById(userId);
        QuestionVO questionVO = new QuestionVO(
                questionId,
                question.getQuestion(),
                user.getAvatarUrl(),
                user.getUsername(),
                question.getCreateTime(),
                questionService.likes(questionId),
                questionService.comments(questionId),
                questionService.isLike(new QuestionLikeDTO(userId,questionId))
        );
        return ResponseEntity.ok(questionVO);
    }

    @GetMapping("/list")
    public ResponseEntity getAll(@Attribute("userId") Long userId){
        return ResponseEntity.ok(questionService.getAll()
                .stream()
                .map(question -> getQuestion(question.getQuestionId(),userId).getData())
                .toList());
    }

    @PostMapping("/list")
    public ResponseEntity getList(@RequestBody List<Long> idList,@Attribute("userId") Long userId){
        return ResponseEntity.ok(questionService.getByIdList(idList)
                .stream()
                .map(question -> getQuestion(question.getQuestionId(),userId).getData())
                .toList());
    }

    @GetMapping("/{questionId}/comment")
    public ResponseEntity getComment(@PathVariable("questionId") Long questionId){
        return ResponseEntity.ok(questionService.getComment(questionId).stream().map(QuestionCommentVO::new).toList());
    }

    @PostMapping("/{questionId}/comment")
    public ResponseEntity comment(@PathVariable("questionId") Long questionId,@Attribute("userId") Long userId, @RequestBody QuestionCommentDTO questionCommentDTO){
        if (!questionService.isExistById(questionId)){
            return ResponseEntity.notFound().message("问题: %d 不存在", questionId);
        }
        questionCommentDTO.setUserId(userId);
        questionCommentDTO.setCreateTime(System.currentTimeMillis());
        return ResponseEntity.ok(questionService.comment(questionId,questionCommentDTO));
    }

    @PostMapping("/{questionId}/like")
    public ResponseEntity like(@PathVariable("questionId") Long questionId,@Attribute("userId") Long userId){
        if (!questionService.isExistById(questionId)){
            return ResponseEntity.notFound().message("问题: %d 不存在", questionId);
        }
        return ResponseEntity.ok(questionService.like(new QuestionLikeDTO(userId,questionId)));
    }

    @PostMapping
    public ResponseEntity question(@Attribute("userId") Long userId, @RequestBody QuestionDTO questionDTO){
        questionDTO.setUserId(userId);
        questionDTO.setCreateTime(System.currentTimeMillis());
        return ResponseEntity.ok(questionService.question(questionDTO,userId));
    }

}
