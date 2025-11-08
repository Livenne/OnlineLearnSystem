package com.livenne.service.impl;

import com.livenne.common.model.Consultation;
import com.livenne.repository.ConsultationRepository;
import com.livenne.repository.impl.ConsultationRepositoryImpl;
import com.livenne.service.ConsultationService;

import java.util.List;

public class ConsultationServiceImpl implements ConsultationService {

    public static ConsultationService instance  = new ConsultationServiceImpl();

    private final ConsultationRepository consultationRepository = ConsultationRepositoryImpl.instance;

    @Override
    public Consultation add(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public void delete(Long consultationId) {
        consultationRepository.delete(consultationId);
    }

    @Override
    public void update(Consultation consultation) {
        consultationRepository.update(consultation);
    }

    @Override
    public Consultation get(Long consultationId) {
        return consultationRepository.findById(consultationId);
    }

    @Override
    public List<Consultation> getList() {
        return consultationRepository.findAll();
    }
}
