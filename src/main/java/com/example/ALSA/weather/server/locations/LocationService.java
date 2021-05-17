package com.example.ALSA.weather.server.locations;

import com.example.ALSA.weather.server.utils.MapUtils;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * Business logic layer
 */

@Service
public class LocationService {

    // Called from ClientService
    public Location searchLocation(String location) throws LocationNotFoundException{
        System.out.println("Request happening here, DO SOMETHING");
        String decodeLocation = decodeValue(location);

        //TODO get data from smhi
        Map<String, String> coords;
        coords = MapUtils.getInstance().getCoordinates(decodeLocation);
        System.out.println("latitude :" + coords.get("lat"));
        System.out.println("longitude:" + coords.get("lon"));

        Location loc = new Location("Malmö","Monday " + decodeLocation);
        return loc;
    }

    private String decodeValue(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    // Called from ClientService
    public List<Location> getLocations(List<String> locationName) {
        return null;
    }
}
