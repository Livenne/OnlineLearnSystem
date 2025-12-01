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
@Entity("chapter")
public class Chapter {
    @Id
    private Long id;
    private Long courseId;
    private String name;
    private String videoUrl;
}
