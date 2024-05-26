package org.example.service.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.example.dao.AuditLogDao;
import org.example.model.AuditLog;
import org.example.model.GeocodeResult;
import org.example.service.GeocodingService;
import org.example.util.NominatimApiUtil;

@Stateless
public class GeocodingServiceImpl implements GeocodingService {
    @Inject
    private AuditLogDao auditLogDao;

    @Override
    public GeocodeResult geocode(String query) {
        GeocodeResult result = NominatimApiUtil.geocode(query);
        assert result != null;
        logAudit("query=" + query, result);
        return result;
    }

    private void logAudit(String parameters, GeocodeResult result) {
        AuditLog auditLog = new AuditLog();
        auditLog.setEndpoint("geocode");
        auditLog.setParameters(parameters);
        auditLog.setResult(result.toString());
        auditLogDao.save(auditLog);
    }
}
