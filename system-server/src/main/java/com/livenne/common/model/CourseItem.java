package com.livenne.common.model;

import jakarta.persistence.*;

@Entity
@Table(name = "course_item")
public class CourseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private Long courseId;
    private String itemName;
    private String videoUrl;

    public CourseItem() {}

    public CourseItem(Long itemId, Long courseId, String itemName, String videoUrl) {
        this.itemId = itemId;
        this.courseId = courseId;
        this.itemName = itemName;
        this.videoUrl = videoUrl;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
