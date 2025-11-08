package com.livenne.repository.impl;

import com.livenne.common.model.UserShoppingCart;
import com.livenne.repository.UserShoppingCartRepository;

import java.util.List;

public class UserShoppingCartRepositoryImpl extends BaseRepository implements UserShoppingCartRepository {

    public static UserShoppingCartRepository instance =  new UserShoppingCartRepositoryImpl();

    @Override
    public UserShoppingCart save(UserShoppingCart userShoppingCart) {
        try {
            entityManager.getTransaction().begin();
            UserShoppingCart merge = entityManager.merge(userShoppingCart);
            entityManager.getTransaction().commit();
            return merge;
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
    public void update(UserShoppingCart userShoppingCart) {
        save(userShoppingCart);
    }

    @Override
    public UserShoppingCart findById(Long id) {
        try{
            return entityManager.find(UserShoppingCart.class,id);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<UserShoppingCart> findAll(Long userId) {
        try {
            return entityManager.createQuery("from UserShoppingCart where userId=:userId", UserShoppingCart.class)
                    .setParameter("userId", userId).getResultList();
        }catch (Exception e) {
            return null;
        }
    }
}
