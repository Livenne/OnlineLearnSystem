package com.livenne.repository.impl;

import com.livenne.common.model.QuestionLike;
import com.livenne.repository.QuestionLikeRepository;

import java.util.List;
import java.util.Optional;

public class QuestionLikeRepositoryImpl extends BaseRepository implements QuestionLikeRepository {

    public static QuestionLikeRepository instance = new QuestionLikeRepositoryImpl();

    @Override
    public QuestionLike findById(Long id) {
        try {
            return entityManager.find(QuestionLike.class, id);
        }catch(Exception e) {
            return null;
        }
    }

    @Override
    public List<QuestionLike> findByQuestionId(Long questionId) {
        try{
            return entityManager.createQuery("from QuestionLike where questionId=:questionId", QuestionLike.class)
                    .setParameter("questionId", questionId)
                    .getResultList();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public QuestionLike save(QuestionLike questionLike) {
        try {
            entityManager.getTransaction().begin();
            QuestionLike merge = entityManager.merge(questionLike);
            entityManager.getTransaction().commit();
            return merge;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean isLike(Long userId, Long questionId) {
        try{
            return Optional.ofNullable(entityManager.createQuery("from QuestionLike where userId=:userId and questionId=:questionId", QuestionLike.class)
                    .setParameter("userId", userId)
                    .setParameter("questionId", questionId)
                    .getSingleResult()).isPresent();
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void delete(Long userId, Long questionId) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.createQuery("from QuestionLike where userId=:userId and questionId=:questionId", QuestionLike.class)
                .setParameter("userId", userId)
                .setParameter("questionId", questionId)
                .getSingleResult());
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(QuestionLike questionLike) {
        save(questionLike);
    }
}
