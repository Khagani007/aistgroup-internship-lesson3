package org.example.controller;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.example.model.GeocodeResult;
import org.example.service.GeocodingService;

@Path("/geocode")
@Stateless
public class GeocodingController {
    @Inject
    private GeocodingService geocodingService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public GeocodeResult geocode(@QueryParam("q") String query) {
        return geocodingService.geocode(query);
    }
}
