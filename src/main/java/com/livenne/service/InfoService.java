package com.livenne.service;

import com.livenne.common.model.entity.Info;

import java.util.List;

public interface InfoService {
    Info getById(Long id);
    List<Info> getAll();
    Boolean isExistById(Long id);
    List<Info> queryByName(String name);
}
