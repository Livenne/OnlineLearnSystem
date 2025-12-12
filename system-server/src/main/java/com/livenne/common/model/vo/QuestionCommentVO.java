package com.livenne.common.model.vo;

import com.livenne.common.model.entity.QuestionComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCommentVO {
    private Long id;
    private Long questionId;
    private Long userId;
    private Long createTime;
    private String content;

    public QuestionCommentVO(QuestionComment questionComment) {
        this.id = questionComment.getId();
        this.questionId = questionComment.getQuestionId();
        this.userId = questionComment.getUserId();
        this.createTime = questionComment.getCreateTime();
        this.content = questionComment.getContent();
    }
}
