package com.livenne.repository.impl;

import com.livenne.common.model.CourseComment;
import com.livenne.repository.CourseCommentRepository;

import java.util.List;

public class CourseCommentRepositoryImpl extends BaseRepository implements CourseCommentRepository {

    public static CourseCommentRepository instance = new CourseCommentRepositoryImpl();

    @Override
    public CourseComment findById(Long commentId) {
        try {
            return entityManager.find(CourseComment.class, commentId);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CourseComment> findAll(Long courseId) {
        try{
            return entityManager.createQuery("from CourseComment where courseId=:courseId", CourseComment.class)
                    .setParameter("courseId", courseId)
                    .getResultList();
        }catch (Exception e){
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
    public void update(CourseComment courseComment) {
        save(courseComment);
    }

    @Override
    public CourseComment save(CourseComment courseComment) {
        try {
            entityManager.getTransaction().begin();
            CourseComment merge = entityManager.merge(courseComment);
            entityManager.getTransaction().commit();
            return merge;
        }catch (Exception e) {
            return null;
        }
    }
}
