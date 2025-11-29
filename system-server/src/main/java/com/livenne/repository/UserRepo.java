package com.livenne.repository;

import com.livenne.annotation.Repository;
import com.livenne.annotation.SqlExecute;

@Repository
public interface UserRepo {
    @SqlExecute("select username from User where userId = ?")
    String getUsername(Long userId);
}
