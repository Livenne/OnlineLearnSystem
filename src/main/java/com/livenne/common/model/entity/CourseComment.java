package com.livenne.common.model.entity;

import io.github.livenne.annotation.orm.Entity;
import io.github.livenne.annotation.orm.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity("course_comment")
public class CourseComment {
    @Id
    private Long id;
    private Long courseId;
    private Long userId;
    private Long createTime;
    private Integer rating;
    private String content;
}
