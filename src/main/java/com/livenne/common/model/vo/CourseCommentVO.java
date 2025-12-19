package com.livenne.common.model.vo;

import com.livenne.common.model.entity.CourseComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseCommentVO {
    private Long id;
    private Long courseId;
    private Long userId;
    private Long createTime;
    private Integer rating;
    private String content;

    public CourseCommentVO(CourseComment courseComment) {
        this.id = courseComment.getId();
        this.courseId = courseComment.getCourseId();
        this.userId = courseComment.getUserId();
        this.createTime = courseComment.getCreateTime();
        this.rating = courseComment.getRating();
        this.content = courseComment.getContent();
    }
}
