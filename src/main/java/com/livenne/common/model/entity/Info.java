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
@Entity("info")
public class Info {
    @Id
    private Long infoId;
    private String name;
    private String teacher;
    private Long createTime;
    private String content;
    private String coverUrl;
}
