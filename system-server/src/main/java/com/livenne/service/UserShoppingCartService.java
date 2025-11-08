package com.livenne.service;

import com.livenne.common.model.UserShoppingCart;

import java.util.List;

public interface UserShoppingCartService {
    UserShoppingCart add(UserShoppingCart userShoppingCart);
    void delete(Long id);
    void update(UserShoppingCart userShoppingCart);
    UserShoppingCart get(Long id);
    List<UserShoppingCart> getListByUserId(Long userId);

}
