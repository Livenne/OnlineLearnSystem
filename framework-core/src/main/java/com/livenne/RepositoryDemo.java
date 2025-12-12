package com.livenne;

import com.livenne.annotation.*;

import java.util.List;

public interface RepositoryDemo extends BaseMapper<Long>{

    String getUsername(Long userId);

//    @Insert
//    Long insert(User user);

    @Query
    List<?> getUserList();

    @Delete
    void deleteUser(Long userId);

//    @Update
//    void update(User user, @Cond("userId") Long userId);
}
