package com.livenne.common.model.vo;

import com.livenne.common.model.User;

public class UserVO {
    private Long userId;
    private String username;
    private String avatarUrl;

    public UserVO() {

    }

    public UserVO(Long userId, String username, String avatarUrl) {
        this.userId = userId;
        this.username = username;
        this.avatarUrl = avatarUrl;
    }

    public UserVO(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.avatarUrl = user.getAvatarUrl();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
