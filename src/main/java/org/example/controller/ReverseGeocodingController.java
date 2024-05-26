package org.example.controller;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.example.model.GeocodeResult;
import org.example.service.ReverseGeocodingService;
@Path("/reverse-geocode")
@Stateless
public class ReverseGeocodingController {
    @Inject
    private ReverseGeocodingService reverseGeocodingService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GeocodeResult reverseGeocode(
            @QueryParam("lat") double latitude,
            @QueryParam("lon") double longitude) {
        return reverseGeocodingService.reverseGeocode(latitude, longitude);
    }
}
