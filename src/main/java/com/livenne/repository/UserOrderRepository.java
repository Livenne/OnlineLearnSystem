package com.livenne.repository;

import io.github.livenne.BaseMapper;
import com.livenne.common.model.dto.UserOrderDTO;
import com.livenne.common.model.entity.UserOrder;

import java.util.List;
import io.github.livenne.annotation.orm.Repository;
import io.github.livenne.annotation.orm.Cond;
import io.github.livenne.annotation.orm.Insert;
import io.github.livenne.annotation.orm.Query;

@Repository
public interface UserOrderRepository extends BaseMapper<UserOrder> {

    @Query
    List<UserOrder> getByUserId(@Cond("userId") Long userId);

    @Query
    List<UserOrder> getByCourseId(@Cond("courseId") Long courseId);

    @Insert
    Long save(UserOrderDTO userOrder);

}
