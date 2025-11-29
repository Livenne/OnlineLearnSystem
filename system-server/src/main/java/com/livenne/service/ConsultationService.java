package com.livenne.service;

import com.livenne.common.model.Consultation;

import java.util.List;

public interface ConsultationService {
    Consultation getById(Long id);
    Consultation getByName(String name);
    List<Consultation> getAll();
    List<Consultation> getByIdList(List<Long> idList);
    List<Consultation> getByNameList(List<String> nameList);
}
