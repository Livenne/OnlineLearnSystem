package com.livenne.service.impl;

import com.livenne.common.model.entity.Info;
import com.livenne.repository.InfoRepository;
import com.livenne.service.InfoService;
import io.github.livenne.annotation.container.Autowired;
import io.github.livenne.annotation.container.Service;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private InfoRepository infoRepository;

    @Override
    public Info getById(Long id) {
        return infoRepository.getById(id);
    }

    @Override
    public List<Info> getAll() {
        return infoRepository.getAll();
    }

    @Override
    public Boolean isExistById(Long id) {
        Info info = getById(id);
        return info != null && info.getInfoId() != null;
    }

    @Override
    public List<Info> queryByName(String name) {
        return infoRepository.queryName(name);
    }
}
