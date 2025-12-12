package com.livenne.repository;

import com.livenne.BaseMapper;
import com.livenne.annotation.*;
import com.livenne.common.model.dto.UserDTO;
import com.livenne.common.model.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends BaseMapper<User> {
    @Query
    User getById(@Cond("userId") Long userId);

    @Query
    User getByUsername(@Cond("username") String username);

    @Query
    List<User> getAll();

    @Insert
    Long save(UserDTO user);

    @Update
    void updateScore(@Cond("userId") Long userId, @Column("score") Long score);

}
