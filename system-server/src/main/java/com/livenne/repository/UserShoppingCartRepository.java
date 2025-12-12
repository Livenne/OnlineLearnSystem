package com.livenne.repository;

import com.livenne.BaseMapper;
import com.livenne.annotation.*;
import com.livenne.common.model.dto.UserShoppingCartDTO;
import com.livenne.common.model.entity.UserShoppingCart;

import java.util.List;

@Repository
public interface UserShoppingCartRepository extends BaseMapper<UserShoppingCart> {

    @Query
    List<UserShoppingCart> getByUserId(@Cond("userId") Long userId);

    @Query
    UserShoppingCart isInCart(@Cond("userId") Long userId, @Cond("courseId") Long courseId);

    @Insert
    Long save(UserShoppingCartDTO userShoppingCart);

    @Delete
    void delete(@Cond("userId") Long userId,@Cond("courseId") Long courseId);

}
