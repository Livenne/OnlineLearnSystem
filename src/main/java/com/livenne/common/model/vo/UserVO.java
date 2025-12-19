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
public class UserVO {
    private Long userId;
    private String username;
    private String avatarUrl;

    public UserVO(User user){
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.avatarUrl = user.getAvatarUrl();
    }

}
