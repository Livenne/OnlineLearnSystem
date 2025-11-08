package com.livenne.repository.impl;

import com.livenne.common.model.Question;
import com.livenne.repository.QuestionRepository;

import java.util.List;

public class QuestionRepositoryImpl extends BaseRepository implements QuestionRepository {
    public static QuestionRepository instance = new QuestionRepositoryImpl();

    @Override
    public Question save(Question question) {
        try {
            entityManager.getTransaction().begin();
            Question merge = entityManager.merge(question);
            entityManager.getTransaction().commit();
            return merge;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Question findById(Long id) {
        try {
            return entityManager.find(Question.class, id);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Question> findAll() {
        try {
            return entityManager.createQuery("from Question", Question.class).getResultList();
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Question question) {
        entityManager.getTransaction().begin();
        save(question);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Question> findAllByUserId(Long userId) {
        try {
            return entityManager.createQuery("from Question where userId=:userId", Question.class)
                    .setParameter("userId", userId).getResultList();
        }catch (Exception e) {
            return null;
        }
    }
}
