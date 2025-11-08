package com.livenne.service.impl;

import com.livenne.common.model.User;
import com.livenne.repository.UserRepository;
import com.livenne.repository.impl.UserRepositoryImpl;
import com.livenne.service.UserService;

public class UserServiceImpl implements UserService {

    public static UserServiceImpl instance = new UserServiceImpl();

    private final UserRepository userRepository = UserRepositoryImpl.instance;

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User get(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean isExitsById(Long id) {
        return get(id) != null;
    }

    @Override
    public Boolean isExitsUsername(String username) {
        return getByUsername(username) != null;
    }
}
