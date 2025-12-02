package com.livenne.repository;

import com.livenne.annotation.Repository;
import com.livenne.annotation.SqlExecute;
import com.livenne.common.model.User;

import java.util.List;

@Repository
public interface UserRepo {
    @SqlExecute("select * from user where userId = ?")
    User getUser(Long userId);

    @SqlExecute("select * from user")
    List<User> getUserList();

    @SqlExecute("select username from user where userId = ?")
    String getUsername(Long userId);
}
