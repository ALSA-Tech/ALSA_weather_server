package com.example.ALSA.weather.server.locations;

import com.example.ALSA.weather.server.utils.MapUtils;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Business logic layer
 */

@Service
public class LocationService {

    // Called from ClientService
    public Location searchLocation(String location) throws LocationNotFoundException{
        String decodeLocation = decodeValue(location);

        //TODO get data from smhi
        Map<String, String> coords;
        coords = MapUtils.getInstance().getCoordinates(decodeLocation);
        System.out.println("latitude :" + coords.get("lat"));
        System.out.println("longitude:" + coords.get("lon"));

        //Test data
        ArrayList<LocationDataXY> dataSeriesXY = new ArrayList<>();
        dataSeriesXY.add(new LocationDataXY(LocalDate.now().toString(), 28));
        dataSeriesXY.add(new LocationDataXY(LocalDate.now().plusDays(1).toString(), 24));
        dataSeriesXY.add(new LocationDataXY(LocalDate.now().plusDays(2).toString(), 25));
        dataSeriesXY.add(new LocationDataXY(LocalDate.now().plusDays(3).toString(), 21));
        dataSeriesXY.add(new LocationDataXY(LocalDate.now().plusDays(4).toString(), 29));
        dataSeriesXY.add(new LocationDataXY(LocalDate.now().plusDays(5).toString(), 28));
        dataSeriesXY.add(new LocationDataXY(LocalDate.now().plusDays(6).toString(), 30));


        Location loc = new Location("Malmö",LocalDate.now().toString(), dataSeriesXY);
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
