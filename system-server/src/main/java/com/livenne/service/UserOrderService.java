package com.livenne.service;

import com.livenne.common.model.UserOrder;

import java.util.List;

public interface UserOrderService {
    UserOrder add(UserOrder userOrder);
    void delete(Long id);
    void update(UserOrder userOrder);
    UserOrder get(Long id);
    List<UserOrder> getListByUserId(Long userId);
}
