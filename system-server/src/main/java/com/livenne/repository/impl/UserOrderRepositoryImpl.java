package com.livenne.repository.impl;

import com.livenne.common.model.UserOrder;
import com.livenne.repository.UserOrderRepository;

import java.util.List;

public class UserOrderRepositoryImpl extends BaseRepository implements UserOrderRepository {

    public static UserOrderRepository instance = new UserOrderRepositoryImpl();

    @Override
    public UserOrder findById(Long id) {
        try{
            return entityManager.find(UserOrder.class,id);
        }catch(Exception e){
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
    public void update(UserOrder userOrder) {
        save(userOrder);
    }

    @Override
    public UserOrder save(UserOrder userOrder) {
        try{
            entityManager.getTransaction().begin();
            UserOrder merge = entityManager.merge(userOrder);
            entityManager.getTransaction().commit();
            return merge;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public List<UserOrder> findAll(Long userId) {
        try {
            return entityManager.createQuery("from UserOrder where userId=:userId", UserOrder.class)
                    .setParameter("userId", userId).getResultList();
        }catch (Exception e) {
            return null;
        }
    }
}
