package com.livenne.common.model.vo;

import com.livenne.common.model.entity.Chapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChapterVO {
    private Long id;
    private Long courseId;
    private String name;
    private String videoUrl;

    public ChapterVO(Chapter chapter){
        this.id = chapter.getId();
        this.courseId = chapter.getCourseId();
        this.name = chapter.getName();
        this.videoUrl = chapter.getVideoUrl();
    }
}
