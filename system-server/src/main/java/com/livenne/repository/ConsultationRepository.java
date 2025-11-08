package com.livenne.repository;

import com.livenne.common.model.Consultation;

import java.util.List;

public interface ConsultationRepository {
    Consultation findById(Long id);
    Consultation findByName(String name);
    List<Consultation> findAll();
    Consultation save(Consultation consultation);
    void delete(Long id);
    void update(Consultation consultation);
}
