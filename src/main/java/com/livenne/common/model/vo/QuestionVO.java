package com.livenne.common.model.vo;

import com.livenne.common.model.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionVO {
    private Long questionId;
    private String question;
    private String avatarUrl;
    private String username;
    private Long createTime;
    private Long likes;
    private Long comments;
    private Boolean like;
}
