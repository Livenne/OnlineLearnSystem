package com.livenne.repository;

import com.livenne.common.model.UserOrder;

import java.util.List;

public interface UserOrderRepository {
    UserOrder findById(Long id);
    void delete(Long id);
    void update(UserOrder userOrder);
    UserOrder save(UserOrder userOrder);
    List<UserOrder> findAll(Long userId);
}
