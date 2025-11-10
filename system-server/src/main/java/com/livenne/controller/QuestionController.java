package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.*;
import com.livenne.common.model.Question;
import com.livenne.common.model.QuestionComment;
import com.livenne.common.model.QuestionLike;
import com.livenne.service.QuestionCommentService;
import com.livenne.service.QuestionLikeService;
import com.livenne.service.QuestionService;
import com.livenne.service.impl.QuestionCommentServiceImpl;
import com.livenne.service.impl.QuestionLikeServiceImpl;
import com.livenne.service.impl.QuestionServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@Controller("/question")
public class QuestionController extends HttpServlet {



    public QuestionService questionService = QuestionServiceImpl.instance;
    public QuestionCommentService questionCommentService = QuestionCommentServiceImpl.instance;
    public QuestionLikeService questionLikeService = QuestionLikeServiceImpl.instance;

    @Autowired
    public HttpServletRequest request;

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

    @PostMapping("/comment")
    public ResponseEntity<?> questionComment(@RequestBody QuestionComment questionComment){
        Long userId = (Long) request.getAttribute("userId");
        questionComment.setCommentId(null);
        questionComment.setUserId(userId);
        questionComment.setCreateTime(System.currentTimeMillis());
        questionCommentService.add(questionComment);
        return ResponseEntity.success(null);
    }
    
    @GetMapping("/like")
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
    @PostMapping("/publish")
    public  ResponseEntity<?> questionPublish(@RequestBody Question question){
        Long userId = (Long) request.getAttribute("userId");
        question.setQuestionId(null);
        question.setUserId(userId);
        question.setLikes(0L);
        question.setComments(0L);
        question.setCreateTime(System.currentTimeMillis());
        Question qs = questionService.add(question);
        System.out.println(qs.getQuestionId());
        return ResponseEntity.success(qs.getQuestionId());
    }

}
