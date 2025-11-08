package com.livenne.common.model;

import jakarta.persistence.*;

@Entity
@Table(name = "question_comment")
public class QuestionComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private Long questionId;
    private Long userId;
    private Long createTime;
    private String content;

    public QuestionComment() {}

    public QuestionComment(Long commentId, Long questionId, Long userId, Long createTime, String content) {
        this.commentId = commentId;
        this.questionId = questionId;
        this.userId = userId;
        this.createTime = createTime;
        this.content = content;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
