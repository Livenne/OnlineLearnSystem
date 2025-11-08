package com.livenne.repository;


import com.livenne.common.model.User;

public interface UserRepository {
    User findById(Long id);
    User findByUsername(String username);
    User save(User user);
    void update(User user);
    void delete(Long id);
}
