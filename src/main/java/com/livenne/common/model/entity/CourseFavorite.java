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
@Entity("course_favorite")
public class CourseFavorite {
    @Id
    private Long id;
    private Long userId;
    private Long courseId;
}
