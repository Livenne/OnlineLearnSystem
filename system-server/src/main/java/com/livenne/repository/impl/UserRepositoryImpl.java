package com.livenne.repository.impl;

import com.livenne.common.model.User;
import com.livenne.repository.UserRepository;

public class UserRepositoryImpl extends BaseRepository implements UserRepository{

    public static UserRepository instance = new UserRepositoryImpl();

    @Override
    public User findById(Long id) {
        try {
            return entityManager.find(User.class, id);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public User findByUsername(String username) {
        try{
            return (User) entityManager.createQuery(
                    "select u from User u where u.username=:username"
            ).setParameter("username",username).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public User save(User user) {
        try {
            entityManager.getTransaction().begin();
            User merge = entityManager.merge(user);
            entityManager.getTransaction().commit();
            return merge;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(User user) {
        save(user);
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }
}
