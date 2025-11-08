package com.livenne.common.model;

import jakarta.persistence.*;

@Entity
@Table(name = "consultation")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultationId;
    private String name;
    private String teacher;
    private Long createTime;
    private String content;
    private String coverUrl;

    public Consultation() {}

    public Consultation(Long consultationId, String name, String teacher, Long createTime, String content, String coverUrl) {
        this.consultationId = consultationId;
        this.name = name;
        this.teacher = teacher;
        this.createTime = createTime;
        this.content = content;
        this.coverUrl = coverUrl;
    }

    public Long getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(Long consultationId) {
        this.consultationId = consultationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
