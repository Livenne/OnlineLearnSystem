package com.livenne.common.model.vo;

import com.livenne.common.model.entity.Info;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfoVO {
    private Long infoId;
    private String name;
    private String teacher;
    private Long createTime;
    private String content;
    private String coverUrl;

    public InfoVO(Info info) {
        this.infoId = info.getInfoId();
        this.name = info.getName();
        this.teacher = info.getTeacher();
        this.createTime = info.getCreateTime();
        this.content = info.getContent();
        this.coverUrl = info.getCoverUrl();
    }
}
