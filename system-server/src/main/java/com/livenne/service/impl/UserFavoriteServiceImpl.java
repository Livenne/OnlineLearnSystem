package com.livenne.service.impl;

import com.livenne.common.model.UserFavorite;
import com.livenne.repository.UserFavoriteRepository;
import com.livenne.repository.impl.UserFavoriteRepositoryImpl;
import com.livenne.service.UserFavoriteService;

import java.util.List;

public class UserFavoriteServiceImpl implements UserFavoriteService {

    public static UserFavoriteService instance = new  UserFavoriteServiceImpl();
    private final UserFavoriteRepository userFavoriteRepository = UserFavoriteRepositoryImpl.instance;

    @Override
    public UserFavorite get(Long favoriteId) {
        return userFavoriteRepository.findById(favoriteId);
    }

    @Override
    public UserFavorite add(UserFavorite userFavorite) {
        return userFavoriteRepository.save(userFavorite);
    }

    @Override
    public void delete(Long favoriteId) {
        userFavoriteRepository.delete(favoriteId);
    }

    @Override
    public void update(UserFavorite userFavorite) {
        userFavoriteRepository.update(userFavorite);
    }

    @Override
    public List<UserFavorite> getListByUserId(Long userId) {
        return userFavoriteRepository.findAll(userId);
    }
}
