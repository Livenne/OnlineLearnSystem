package com.livenne.repository;

import com.livenne.common.model.UserFavorite;

import java.util.List;

public interface UserFavoriteRepository {
    UserFavorite save(UserFavorite userFavorite);
    void delete(Long userId, Long courseId);
    void update(UserFavorite userFavorite);
    Boolean isFavorite(Long userId, Long courseId);
    UserFavorite findById(Long favoriteId);
    List<UserFavorite> findAll(Long userId);
}
