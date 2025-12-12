package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.*;
import com.livenne.common.model.dto.QuestionCommentDTO;
import com.livenne.common.model.dto.QuestionDTO;
import com.livenne.common.model.dto.QuestionLikeDTO;
import com.livenne.common.model.entity.Question;
import com.livenne.common.model.entity.QuestionComment;
import com.livenne.common.model.entity.QuestionLike;
import com.livenne.common.model.vo.QuestionCommentVO;
import com.livenne.common.model.vo.QuestionVO;
import com.livenne.service.QuestionService;

import java.util.List;

@Controller("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionVO> getQuestion(@PathVariable("questionId") Long questionId,@Attribute("userId") Long userId){
        if (!questionService.isExistById(questionId)){
            return ResponseEntity.failureMsg("问题: %d 不存在", questionId);
        }
        Question question = questionService.getById(questionId);
        QuestionVO questionVO = new QuestionVO(
                questionId,
                question.getQuestion(),
                question.getImageUrl(),
                question.getUserId(),
                question.getCreateTime(),
                questionService.likes(questionId),
                questionService.comments(questionId),
                questionService.isLike(new QuestionLikeDTO(userId,questionId))
        );
        return ResponseEntity.success(questionVO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<QuestionVO>> getAll(@Attribute("userId") Long userId){
        return ResponseEntity.success(questionService.getAll()
                .stream()
                .map(question -> getQuestion(question.getQuestionId(),userId).getData())
                .toList());
    }

    @PostMapping("/list")
    public ResponseEntity<List<QuestionVO>> getList(@RequestBody List<Long> idList,@Attribute("userId") Long userId){
        return ResponseEntity.success(questionService.getByIdList(idList)
                .stream()
                .map(question -> getQuestion(question.getQuestionId(),userId).getData())
                .toList());
    }

    @GetMapping("/{questionId}/comment")
    public ResponseEntity<List<QuestionCommentVO>> getComment(@PathVariable("questionId") Long questionId){
        return ResponseEntity.success(questionService.getComment(questionId).stream().map(QuestionCommentVO::new).toList());
    }

    @PostMapping("/{questionId}/comment")
    public ResponseEntity<Long> comment(@PathVariable("questionId") Long questionId,@Attribute("userId") Long userId, @RequestBody QuestionCommentDTO questionCommentDTO){
        if (!questionService.isExistById(questionId)){
            return ResponseEntity.failureMsg("问题: %d 不存在", questionId);
        }
        questionCommentDTO.setUserId(userId);
        questionCommentDTO.setCreateTime(System.currentTimeMillis());
        return ResponseEntity.success(questionService.comment(questionId,questionCommentDTO));
    }

    @PostMapping("/{questionId}/like")
    public ResponseEntity<Boolean> like(@PathVariable("questionId") Long questionId,@Attribute("userId") Long userId){
        if (!questionService.isExistById(questionId)){
            return ResponseEntity.failureMsg("问题: %d 不存在", questionId);
        }
        return ResponseEntity.success(questionService.like(new QuestionLikeDTO(userId,questionId)));
    }

    @PostMapping
    public ResponseEntity<Long> question(@Attribute("userId") Long userId, @RequestBody QuestionDTO questionDTO){
        questionDTO.setUserId(userId);
        questionDTO.setCreateTime(System.currentTimeMillis());
        return ResponseEntity.success(questionService.question(questionDTO,userId));
    }

}
