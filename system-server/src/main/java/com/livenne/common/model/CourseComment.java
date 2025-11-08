package com.livenne.common.model;

import jakarta.persistence.*;

@Entity
@Table(name = "course_comment")
public class CourseComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private Long courseId;
    private Long userId;
    private Long createTime;
    private Integer rating;
    private String content;

    public CourseComment() {}

    public CourseComment(Long commentId, Long courseId, Long userId, Long createTime, Integer rating, String content) {
        this.commentId = commentId;
        this.courseId = courseId;
        this.userId = userId;
        this.createTime = createTime;
        this.rating = rating;
        this.content = content;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
