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
@Entity("user")
public class User {
    @Id
    private Long userId;
    private String username;
    private String password;
    private Long score;
    private String avatarUrl;
}
