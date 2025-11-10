package com.livenne.repository.impl;

import com.livenne.common.model.CourseLike;
import com.livenne.common.model.QuestionLike;
import com.livenne.common.model.UserFavorite;
import com.livenne.repository.UserFavoriteRepository;

import java.util.List;
import java.util.Optional;

public class UserFavoriteRepositoryImpl extends BaseRepository implements UserFavoriteRepository {

    public static UserFavoriteRepository instance = new UserFavoriteRepositoryImpl();

    @Override
    public UserFavorite save(UserFavorite userFavorite) {
        try {
            entityManager.getTransaction().begin();
            UserFavorite merge = entityManager.merge(userFavorite);
            entityManager.getTransaction().commit();
            return merge;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public void delete(Long userId, Long courseId) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.createQuery("from UserFavorite where userId=:userId and courseId=:courseId", UserFavorite.class)
                .setParameter("userId", userId)
                .setParameter("courseId", courseId)
                .getSingleResult());
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(UserFavorite userFavorite) {
        save(userFavorite);
    }

    @Override
    public Boolean isFavorite(Long userId, Long courseId) {
        try{
            return Optional.ofNullable(entityManager.createQuery("from UserFavorite where userId=:userId and courseId=:courseId", UserFavorite.class)
                    .setParameter("userId", userId)
                    .setParameter("courseId", courseId)
                    .getSingleResult()).isPresent();
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public UserFavorite findById(Long favoriteId) {
        try{
            return entityManager.find(UserFavorite.class, favoriteId);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<UserFavorite> findAll(Long userId) {
        try {
            return entityManager.createQuery("from UserFavorite where userId=:userId", UserFavorite.class)
                    .setParameter("userId", userId).getResultList();
        }catch (Exception e) {
            return null;
        }
    }
}
