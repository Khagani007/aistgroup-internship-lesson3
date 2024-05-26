package org.example.service;

import org.example.model.GeocodeResult;

public interface ReverseGeocodingService {
    GeocodeResult reverseGeocode(double latitude, double longitude);
}
