package com.livenne.common.model.entity;

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
@Entity("question")
public class Question {
    @Id
    private Long questionId;
    private String question;
    private String imageUrl;
    private Long userId;
    private Long createTime;
}
