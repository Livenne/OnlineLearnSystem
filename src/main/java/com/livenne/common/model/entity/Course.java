package com.livenne.common.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.github.livenne.annotation.orm.Entity;
import io.github.livenne.annotation.orm.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity("course")
public class Course {
    @Id
    private Long courseId;
    private String name;
    private String introduction;
    private String coverUrl;
    private Long price;
    private String teacher;
}
