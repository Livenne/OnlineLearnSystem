package com.livenne.service.impl;

import com.livenne.common.model.dto.QuestionCommentDTO;
import com.livenne.common.model.dto.QuestionDTO;
import com.livenne.common.model.dto.QuestionLikeDTO;
import com.livenne.common.model.entity.Question;
import com.livenne.common.model.entity.QuestionComment;
import com.livenne.common.model.entity.QuestionLike;
import com.livenne.repository.QuestionCommentRepository;
import com.livenne.repository.QuestionLikeRepository;
import com.livenne.repository.QuestionRepository;
import com.livenne.service.QuestionService;
import io.github.livenne.annotation.container.Autowired;
import io.github.livenne.annotation.container.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionCommentRepository questionCommentRepository;
    @Autowired
    private QuestionLikeRepository questionLikeRepository;

    @Override
    public Question getById(Long id) {
        return questionRepository.getById(id);
    }

    @Override
    public List<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public List<Question> getByIdList(List<Long> idList) {
        return idList.stream().map(this::getById).toList();
    }

    @Override
    public List<Question> getByUserId(Long userId) {
        return questionRepository.getByUserId(userId);
    }

    @Override
    public Boolean isExistById(Long id) {
        Question question = getById(id);
        return question != null && question.getQuestionId() != null;
    }

    @Override
    public List<QuestionComment> getComment(Long id) {
        return questionCommentRepository.getByQuestionId(id);
    }

    @Override
    public Boolean like(QuestionLikeDTO questionLikeDTO) {
        QuestionLike like = questionLikeRepository.isLike(questionLikeDTO.getQuestionId(),questionLikeDTO.getUserId());
        if (isLike(questionLikeDTO)){
            questionLikeRepository.delete(like.getId());
            return false;
        }
        questionLikeRepository.save(questionLikeDTO);
        return true;
    }

    @Override
    public Boolean isLike(QuestionLikeDTO questionLikeDTO) {
        QuestionLike like = questionLikeRepository.isLike(questionLikeDTO.getQuestionId(), questionLikeDTO.getUserId());
        return like != null && like.getId() != null;
    }

    @Override
    public Long likes(Long id) {
        return (long) questionLikeRepository.getByQuestionId(id).size();
    }

    @Override
    public Long comment(Long questionId, QuestionCommentDTO questionCommentDTO) {
        return questionCommentRepository.save(questionCommentDTO);
    }

    @Override
    public Long comments(Long id) {
        return (long) questionCommentRepository.getByQuestionId(id).size();
    }

    @Override
    public Long question(QuestionDTO questionDTO, Long userId) {
        return questionRepository.save(questionDTO);
    }
}
