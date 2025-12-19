package com.livenne.repository;

import io.github.livenne.BaseMapper;
import com.livenne.common.model.dto.UserShoppingCartDTO;
import com.livenne.common.model.entity.UserShoppingCart;

import java.util.List;
import io.github.livenne.annotation.orm.Repository;
import io.github.livenne.annotation.orm.Cond;
import io.github.livenne.annotation.orm.Delete;
import io.github.livenne.annotation.orm.Insert;
import io.github.livenne.annotation.orm.Query;

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
