package com.livenne.repository;

import com.livenne.common.model.UserShoppingCart;

import java.util.List;

public interface UserShoppingCartRepository {
    UserShoppingCart save(UserShoppingCart userShoppingCart);
    void delete(Long id);
    void update(UserShoppingCart userShoppingCart);
    UserShoppingCart findById(Long id);
    List<UserShoppingCart> findAll(Long userId);
}
