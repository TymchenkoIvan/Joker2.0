package com.company.DAO.Jpa;

import com.company.DAO.RoleDAO;
import com.company.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JpaRoleDAO implements RoleDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public Role getRole(int id) {
        Query query = entityManager.createQuery("SELECT r FROM Role r WHERE r.id = :id", Role.class);
        query.setParameter("id", id);

        return (Role)query.getSingleResult();
    }

    @Override
    public Role getRole(String role) {
        Query query = entityManager.createQuery("SELECT r FROM Role r WHERE r.role = :role", Role.class);
        query.setParameter("role", role);

        return (Role)query.getSingleResult();
    }
}
