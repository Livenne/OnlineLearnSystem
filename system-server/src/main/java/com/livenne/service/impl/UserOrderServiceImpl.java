package com.livenne.service.impl;

import com.livenne.common.model.UserOrder;
import com.livenne.repository.UserOrderRepository;
import com.livenne.repository.impl.UserOrderRepositoryImpl;
import com.livenne.service.UserOrderService;

import java.util.List;

public class UserOrderServiceImpl implements UserOrderService {

    public static UserOrderService instance = new UserOrderServiceImpl();
    private final UserOrderRepository userOrderRepository = UserOrderRepositoryImpl.instance;

    @Override
    public UserOrder add(UserOrder userOrder) {
        return userOrderRepository.save(userOrder);
    }

    @Override
    public void delete(Long id) {
        userOrderRepository.delete(id);
    }

    @Override
    public void update(UserOrder userOrder) {
        userOrderRepository.update(userOrder);
    }

    @Override
    public UserOrder get(Long id) {
        return userOrderRepository.findById(id);
    }

    @Override
    public List<UserOrder> getListByUserId(Long userId) {
        return userOrderRepository.findAll(userId);
    }
}
