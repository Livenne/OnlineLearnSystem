package com.livenne.repository;

import io.github.livenne.BaseMapper;
import com.livenne.common.model.dto.UserDTO;
import com.livenne.common.model.entity.User;

import java.util.List;
import io.github.livenne.annotation.orm.Repository;
import io.github.livenne.annotation.orm.*;

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
