package org.example.service.impl;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.example.dao.AuditLogDao;
import org.example.model.AuditLog;
import org.example.model.GeocodeResult;
import org.example.service.ReverseGeocodingService;
import org.example.util.NominatimApiUtil;

@Stateless
public class ReverseGeocodingServiceImpl implements ReverseGeocodingService {
    @Inject
    private AuditLogDao auditLogDao;

    @Override
    public GeocodeResult reverseGeocode(double latitude, double longitude) {
        GeocodeResult result = NominatimApiUtil.reverseGeocode(latitude, longitude);
        assert result != null;
        logAudit("latitude=" + latitude + ", longitude=" + longitude, result);
        return result;
    }

    private void logAudit(String parameters, GeocodeResult result) {
        AuditLog auditLog = new AuditLog();
        auditLog.setEndpoint("reverse-geocode");
        auditLog.setParameters(parameters);
        auditLog.setResult(result.toString());
        auditLogDao.save(auditLog);
    }

}
