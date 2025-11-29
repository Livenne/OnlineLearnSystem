package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.*;
import com.livenne.common.model.Question;
import com.livenne.common.model.QuestionComment;
import com.livenne.common.model.QuestionLike;
import com.livenne.service.QuestionService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@Controller("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestion(@PathVariable("questionId") Long questionId){
        return ResponseEntity.success(questionService.getById(questionId));
    }


    @GetMapping("/{questionId}/like")
    public ResponseEntity<QuestionLike> getQuestionLike(@PathVariable("questionId") Long questionId){
        return ResponseEntity.success();
    }
    @GetMapping("/{questionId}/favorite")
    public ResponseEntity<Question> getQuestionFavorite(@PathVariable("questionId") Long questionId){
        return ResponseEntity.success();
    }
    @GetMapping("/{questionId}/comment/list")
    public ResponseEntity<?> getCommentList(@PathVariable("questionId") Long questionId){
        return ResponseEntity.success();
    }
    @GetMapping("/{questionId}/comment/like")
    public ResponseEntity<?> getCommentLike(@PathVariable("questionId") Long questionId){
        return ResponseEntity.success();
    }
    @PostMapping("/{questionId}/comment")
    public ResponseEntity<?> addComment(@PathVariable("questionId") Long questionId, @RequestBody QuestionComment comment){
        return ResponseEntity.success();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Question>> getAll(){
        return ResponseEntity.success(questionService.getAll());
    }

    @PostMapping("/list")
    public ResponseEntity<List<Question>> getList(@RequestBody List<Long> idList){
        return ResponseEntity.success(questionService.getByIdList(idList));
    }

    @PostMapping("/push")
    public ResponseEntity<?> push(@RequestBody Question question){
        return ResponseEntity.success();
    }

}
