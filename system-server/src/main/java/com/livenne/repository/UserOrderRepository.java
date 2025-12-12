package com.livenne.repository;

import com.livenne.BaseMapper;
import com.livenne.annotation.Cond;
import com.livenne.annotation.Insert;
import com.livenne.annotation.Query;
import com.livenne.annotation.Repository;
import com.livenne.common.model.dto.UserOrderDTO;
import com.livenne.common.model.entity.UserOrder;

import java.util.List;

@Repository
public interface UserOrderRepository extends BaseMapper<UserOrder> {

    @Query
    List<UserOrder> getByUserId(@Cond("userId") Long userId);

    @Query
    List<UserOrder> getByCourseId(@Cond("courseId") Long courseId);

    @Insert
    Long save(UserOrderDTO userOrder);

}
