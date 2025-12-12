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
@Entity("course_like")
public class CourseLike {
    @Id
    private Long id;
    private Long userId;
    private Long courseId;
}
