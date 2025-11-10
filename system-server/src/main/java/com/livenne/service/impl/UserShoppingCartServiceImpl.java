package com.livenne.service.impl;

import com.livenne.common.model.UserShoppingCart;
import com.livenne.repository.UserShoppingCartRepository;
import com.livenne.repository.impl.UserShoppingCartRepositoryImpl;
import com.livenne.service.UserShoppingCartService;

import java.util.List;

public class UserShoppingCartServiceImpl implements UserShoppingCartService {

    public static UserShoppingCartService instance = new UserShoppingCartServiceImpl();
    private final UserShoppingCartRepository userShoppingCartRepository = UserShoppingCartRepositoryImpl.instance;

    @Override
    public UserShoppingCart add(UserShoppingCart userShoppingCart) {
        return userShoppingCartRepository.save(userShoppingCart);
    }

    @Override
    public void delete(Long userId,Long courseId) {
        userShoppingCartRepository.delete(userId,courseId);
    }

    @Override
    public void update(UserShoppingCart userShoppingCart) {
        userShoppingCartRepository.update(userShoppingCart);
    }

    @Override
    public UserShoppingCart get(Long id) {
        return userShoppingCartRepository.findById(id);
    }

    @Override
    public List<UserShoppingCart> getListByUserId(Long userId) {
        return userShoppingCartRepository.findAll(userId);
    }
}
