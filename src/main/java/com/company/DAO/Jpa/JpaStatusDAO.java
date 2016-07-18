package com.company.DAO.Jpa;

import com.company.DAO.StatusDAO;
import com.company.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JpaStatusDAO implements StatusDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Status getStatus(int id) {
        Query query = entityManager.createQuery("SELECT s FROM Status s WHERE s.id = :id", Status.class);
        query.setParameter("id", id);

        return (Status)query.getSingleResult();
    }

    @Override
    public Status getStatus(String status) {
        Query query = entityManager.createQuery("SELECT s FROM Status s WHERE s.status = :status", Status.class);
        query.setParameter("status", status);

        return (Status)query.getSingleResult();
    }
}
