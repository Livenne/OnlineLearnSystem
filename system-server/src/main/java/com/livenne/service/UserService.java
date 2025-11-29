package com.livenne.service;

import com.livenne.common.model.User;

import java.util.List;

public interface UserService {
    User getById(Long id);
    User getByName(String name);
    List<User> getAll();
    List<User> getByIdList(List<Long> idList);
    List<User> getByNameList(List<String> nameList);

    Boolean isExistByName(String name);

    User save(User user);
}
