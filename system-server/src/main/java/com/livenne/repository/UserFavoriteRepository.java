package com.livenne.repository;

import com.livenne.common.model.UserFavorite;

import java.util.List;

public interface UserFavoriteRepository {
    UserFavorite save(UserFavorite userFavorite);
    void delete(Long favoriteId);
    void update(UserFavorite userFavorite);
    UserFavorite findById(Long favoriteId);
    List<UserFavorite> findAll(Long userId);
}
