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
@Entity("consultation")
public class Consultation {
    @Id
    private Long consultationId;
    private String name;
    private String teacher;
    private Long createTime;
    private String content;
    private String coverUrl;
}
