package com.livenne;

import com.livenne.annotation.SqlExecute;

import java.util.List;

public interface RepositoryDemo{

    @SqlExecute("select username from User where userId = ?")
    String getUsername(Long userId);

    @SqlExecute("select * from User")
    List<?> getUserList();

    @SqlExecute("delete from User where userId = ?")
    void deleteUser(Long userId);
}
