package com.livenne.repository.impl;

import com.livenne.common.model.CourseLike;
import com.livenne.repository.CourseLikeRepository;

import java.util.List;
import java.util.Optional;

public class CourseLikeRepositoryImpl extends BaseRepository implements CourseLikeRepository {

    public static CourseLikeRepository instance = new CourseLikeRepositoryImpl();

    @Override
    public CourseLike findById(Long id) {
        try{
            return entityManager.find(CourseLike.class, id);
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public List<CourseLike> findByCourseId(Long courseId) {
        try{
            return entityManager.createQuery("from CourseLike where courseId=:courseId", CourseLike.class)
                    .setParameter("courseId", courseId)
                    .getResultList();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Boolean isLike(Long userId, Long courseId) {
        try{
            return Optional.ofNullable(entityManager.createQuery("from CourseLike where userId=:userId and courseId=:courseId", CourseLike.class)
                    .setParameter("userId", userId)
                    .setParameter("courseId", courseId)
                    .getSingleResult()).isPresent();
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public CourseLike save(CourseLike courseLike) {
        try {
            entityManager.getTransaction().begin();
            CourseLike merge = entityManager.merge(courseLike);
            entityManager.getTransaction().commit();
            return merge;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void delete(Long userId, Long courseId) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.createQuery("from CourseLike where userId=:userId and courseId=:courseId", CourseLike.class)
                .setParameter("userId", userId)
                .setParameter("courseId", courseId)
                .getSingleResult());
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(CourseLike courseLike) {
        save(courseLike);
    }
}
