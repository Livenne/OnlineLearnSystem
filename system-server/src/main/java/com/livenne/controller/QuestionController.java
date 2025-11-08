package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.Controller;
import com.livenne.annotation.GetMapping;
import com.livenne.annotation.RequestParm;
import com.livenne.common.model.Question;
import com.livenne.service.QuestionCommentService;
import com.livenne.service.QuestionService;
import com.livenne.service.impl.QuestionCommentServiceImpl;
import com.livenne.service.impl.QuestionServiceImpl;
import jakarta.servlet.http.HttpServlet;

@Controller("/question")
public class QuestionController extends HttpServlet {

    public QuestionService questionService = QuestionServiceImpl.instance;
    public QuestionCommentService questionCommentService = QuestionCommentServiceImpl.instance;

    @GetMapping
    public ResponseEntity<?> getQuestion(@RequestParm("questionId") Long questionId){
        Question question = questionService.get(questionId);
        if (question == null) return ResponseEntity.failureMsg("");
        return ResponseEntity.success(question);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getQuestionList(){
        return ResponseEntity.success(questionService.getList());
    }

    @GetMapping("/comment")
    public ResponseEntity<?> getQuestionComment(@RequestParm("questionId") Long questionId){
        return ResponseEntity.success(questionCommentService.getListByQuestionId(questionId));
    }

}
