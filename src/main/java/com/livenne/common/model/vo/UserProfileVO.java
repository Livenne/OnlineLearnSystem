package com.livenne.common.model.vo;

import com.livenne.common.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileVO {
    private Long userId;
    private String username;
    private Long score;
    private String avatarUrl;

    public UserProfileVO(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.score = user.getScore();
        this.avatarUrl = user.getAvatarUrl();
    }
}
