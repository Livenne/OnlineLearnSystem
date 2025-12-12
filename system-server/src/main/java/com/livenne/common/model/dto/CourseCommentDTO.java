package com.livenne.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseCommentDTO {
    private Long userId;
    private Integer rating;
    private String content;
    private Long createTime;
}
