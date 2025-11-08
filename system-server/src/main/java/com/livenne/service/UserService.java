package com.livenne.service;

import com.livenne.common.model.User;

public interface UserService {
    User add(User user);
    void update(User user);
    void delete(Long userId);
    User get(Long userId);
    User getByUsername(String username);
    Boolean isExitsById(Long id);
    Boolean isExitsUsername(String username);
}
