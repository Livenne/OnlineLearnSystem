package com.livenne.common.model;

import jakarta.persistence.*;

@Entity
@Table(name = "question_like")
public class QuestionLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long questionId;

    public QuestionLike(){}

    public QuestionLike(Long id, Long userId, Long questionId) {
        this.id = id;
        this.userId = userId;
        this.questionId = questionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
