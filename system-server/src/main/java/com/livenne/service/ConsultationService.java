package com.livenne.service;

import com.livenne.common.model.Consultation;

import java.util.List;

public interface ConsultationService {
    Consultation add(Consultation consultation);
    void delete(Long consultationId);
    void update(Consultation consultation);
    Consultation get(Long consultationId);
    List<Consultation> getList();
}
