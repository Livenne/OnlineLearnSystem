package com.livenne.common.model;

import com.livenne.annotation.Entity;
import com.livenne.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity("question_comment")
public class QuestionComment {
    @Id
    private Long id;
    private Long questionId;
    private Long userId;
    private Long createTime;
    private String content;
}
