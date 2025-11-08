package com.livenne.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class BaseRepository {
    protected EntityManager entityManager = Persistence.createEntityManagerFactory("JpaUnit").createEntityManager();

}
