package com.livenne.repository.impl;

import com.livenne.common.model.Consultation;
import com.livenne.repository.ConsultationRepository;

import java.util.List;

public class ConsultationRepositoryImpl extends BaseRepository implements ConsultationRepository {
    public static ConsultationRepository instance = new ConsultationRepositoryImpl();

    @Override
    public Consultation findById(Long id) {
        try {
            return entityManager.find(Consultation.class, id);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Consultation findByName(String name) {
        try{
            return (Consultation) entityManager.createQuery(
                    "select c from Consultation c where c.name=:name"
            ).setParameter("name",name).getSingleResult();
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public List<Consultation> findAll() {
        try {
            return entityManager.createQuery("from Consultation", Consultation.class).getResultList();
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public Consultation save(Consultation consultation) {
        try {
            entityManager.getTransaction().begin();
            Consultation merge = entityManager.merge(consultation);
            entityManager.getTransaction().commit();
            return merge;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(findById(id));
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Consultation consultation) {
        save(consultation);
    }
}
