package org.example.service;

import org.example.model.GeocodeResult;

public interface GeocodingService {
    GeocodeResult geocode(String query);
}
