package com.livenne.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCommentDTO {
    private Long questionId;
    private Long userId;
    private String content;
    private Long createTime;
}
