package org.example.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.model.AuditLog;

import java.util.List;

@Stateless
public class AuditLogDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(AuditLog auditLog) {
        entityManager.persist(auditLog);
    }

    public List<AuditLog> getAllLogs() {
        return entityManager.createQuery("SELECT a FROM AuditLog a", AuditLog.class).getResultList();
    }
}
