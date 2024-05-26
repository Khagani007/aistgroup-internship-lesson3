package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeocodeResult {
    private Map<String, Object> address;
    private double latitude;
    private double longitude;

}
