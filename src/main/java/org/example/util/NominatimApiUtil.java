package org.example.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.http.client.utils.URIBuilder;
import org.example.model.GeocodeResult;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class NominatimApiUtil {
    private static final String BASE_URL = "https://nominatim.md7.info";

    public static GeocodeResult geocode(String query) {
        try {
            URI uri = new URIBuilder(BASE_URL + "/search")
                    .addParameter("q", query)
                    .build();

            HttpGet httpGet = new HttpGet(uri);
            CloseableHttpClient httpClient = HttpClients.createDefault();

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                String responseBody = EntityUtils.toString(entity);

                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(responseBody);
                JsonNode firstResult = root.get(0);

                GeocodeResult result = new GeocodeResult();
                result.setLatitude(firstResult.get("lat").asDouble());
                result.setLongitude(firstResult.get("lon").asDouble());


                return result;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static GeocodeResult reverseGeocode(double latitude, double longitude) {
        try {
            URI uri = new URIBuilder(BASE_URL + "/reverse")
                    .addParameter("lat", String.valueOf(latitude))
                    .addParameter("lon", String.valueOf(longitude))
                    .build();

            HttpGet httpGet = new HttpGet(uri);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpGet);

            try {
                HttpEntity entity = response.getEntity();
                String responseBody = EntityUtils.toString(entity);

                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(responseBody);

                GeocodeResult result = new GeocodeResult();
                result.setLatitude(latitude);
                result.setLongitude(longitude);

                return result;
            } finally {
                response.close();
            }
        } catch (URISyntaxException | IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
