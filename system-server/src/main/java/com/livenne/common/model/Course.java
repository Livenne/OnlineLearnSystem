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
@Entity("course")
public class Course {
    @Id
    private Long courseId;
    private String name;
    private String introduction;
    private String coverUrl;
    private Long price;
    private Long purchase;
    private Long likes;
    private Long favorites;
    private String teacher;
}
