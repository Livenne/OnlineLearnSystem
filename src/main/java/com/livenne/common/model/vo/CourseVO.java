package com.livenne.common.model.vo;

import com.livenne.common.model.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseVO {
    private Long courseId;
    private String name;
    private String introduction;
    private String coverUrl;
    private Long price;
    private String teacher;
    private Long purchase;
    private Long likes;
    private Long favorites;
    private Long rating;
    private Long chapters;
    private Long comments;
    private Boolean like;
    private Boolean favorite;
}
