package com.livenne.repository.impl;

import com.livenne.common.model.QuestionComment;
import com.livenne.repository.QuestionCommentRepository;

import java.util.List;

public class QuestionCommentRepositoryImpl extends BaseRepository implements QuestionCommentRepository {

    public static QuestionCommentRepository instance = new  QuestionCommentRepositoryImpl();

    @Override
    public QuestionComment findById(Long commentId) {
        try {
            return entityManager.find(QuestionComment.class, commentId);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public QuestionComment save(QuestionComment questionComment) {
        try {
            entityManager.getTransaction().begin();
            QuestionComment merge = entityManager.merge(questionComment);
            entityManager.getTransaction().commit();
            return merge;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public void delete(Long commentId) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(commentId));
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(QuestionComment questionComment) {
        save(questionComment);
    }

    @Override
    public List<QuestionComment> findAll(Long questionId) {
        try {
            return entityManager.createQuery("from QuestionComment where questionId=:questionId", QuestionComment.class)
                    .setParameter("questionId", questionId).getResultList();
        }catch (Exception e) {
            return null;
        }
    }
}
