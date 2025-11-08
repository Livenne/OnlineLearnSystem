package com.livenne.service;

import com.livenne.common.model.UserFavorite;

import java.util.List;

public interface UserFavoriteService {
    UserFavorite get(Long favoriteId);
    UserFavorite add(UserFavorite userFavorite);
    void delete(Long favoriteId);
    void update(UserFavorite userFavorite);
    List<UserFavorite> getListByUserId(Long userId);
}
