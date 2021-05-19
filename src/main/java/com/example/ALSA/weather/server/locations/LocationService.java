package com.example.ALSA.weather.server.locations;

import com.example.ALSA.weather.server.locations.gsonClasses.TimeSeries;
import com.example.ALSA.weather.server.utils.MapUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
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

    public ArrayList<Location> makeApiRequest(String latitude, String longitude, String cityName) {
        HttpURLConnection connection;
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        ArrayList<Location> locationList = new ArrayList<>();
        try {

            //URL url = new URL("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/13.763/lat/56.1577/data.json");
            URL url = new URL("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/" + longitude + "/lat/" + latitude + "/data.json");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();

            //Create JSON object
            JsonObject jsonObject = createJsonObject(responseContent.toString());
            //Create TimeSeries List
            TimeSeries[] timeSeries = createTimeSeriesList(jsonObject);

            Location location = new Location(cityName,LocalDate.now().toString(),tempCalculations(timeSeries));
            locationList.add(location);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return locationList;
    }

    // Called from ClientService (One location)
    public Location searchLocation(String location) throws LocationNotFoundException{
        String decodeLocation = decodeValue(location);

        Map<String, String> coords;
        coords = MapUtils.getInstance().getCoordinates(decodeLocation);

        String longitude = String.format("%.6f", Float.parseFloat(coords.get("lon"))).replace(",",".");
        String latitude = String.format("%.6f", Float.parseFloat(coords.get("lat"))).replace(",",".");
        System.out.println("LONG: " + longitude);
        System.out.println("LATI: " + latitude);

        Location loc = makeApiRequest(latitude,longitude,location).get(0); //We only expect one location back and therefore it will be in index 0.

        //Test data
        /*ArrayList<LocationDataXY> dataSeriesXY = new ArrayList<>();
        dataSeriesXY.add(new LocationDataXY(LocalDate.now().toString(), 28));
        dataSeriesXY.add(new LocationDataXY(LocalDate.now().plusDays(1).toString(), 24));
        dataSeriesXY.add(new LocationDataXY(LocalDate.now().plusDays(2).toString(), 25));
        dataSeriesXY.add(new LocationDataXY(LocalDate.now().plusDays(3).toString(), 21));
        dataSeriesXY.add(new LocationDataXY(LocalDate.now().plusDays(4).toString(), 29));
        dataSeriesXY.add(new LocationDataXY(LocalDate.now().plusDays(5).toString(), 28));
        dataSeriesXY.add(new LocationDataXY(LocalDate.now().plusDays(6).toString(), 30));
        */

        return loc;
    }

    // Called from ClientService (Subscriptions)
    public List<Location> getLocations(List<String> locations) {
        ArrayList<Location> locationList = new ArrayList<>();
        for (int i = 0; i < locations.size(); i++) {
            String location = locations.get(i);
            String decodeLocation = decodeValue(location);

            Map<String, String> coords;
            coords = MapUtils.getInstance().getCoordinates(decodeLocation);

            String longitude = coords.get("lon");
            String latitude = coords.get("lat");

            locationList = makeApiRequest(latitude, longitude, location);
        }
        return locationList;
    }

    private String decodeValue(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public JsonObject createJsonObject(String line) {
        JsonObject jsonObject = JsonParser.parseString(line).getAsJsonObject();
        return jsonObject;
    }

    public TimeSeries[] createTimeSeriesList(JsonObject jsonObject) {
        Gson gson = new Gson();
        JsonElement jsonElement = jsonObject.get("timeSeries");
        TimeSeries[] timeSeries = gson.fromJson(jsonElement, TimeSeries[].class);
        return timeSeries;
    }

    public ArrayList<LocationDataXY> tempCalculations(TimeSeries[] timeSeries){
        ArrayList<Double> temps = new ArrayList<>();
        int daysCounter = 0;
        ArrayList<LocationDataXY> dataSeriesXY = new ArrayList<>();

        String date = java.time.LocalDateTime.now().toString().split("T")[0];

        for (TimeSeries t : timeSeries){
            for (int i = 0; i < t.getParameters().size(); i++) {
                if (t.getValidTime().contains(date)) {
                    if (t.getParameters().get(i).getName().equalsIgnoreCase("t")) {
                        double temp = t.getParameters().get(i).getValues().get(0);
                        temps.add(temp);
                    }
                } else {
                    double highestTemp = calculateHighestTemp(temps);
                    dataSeriesXY.add(new LocationDataXY(LocalDate.now().plusDays(daysCounter).toString(),highestTemp));
                    daysCounter++;
                    temps.clear();
                    date = t.getValidTime().split("T")[0];
                }
            }
        }
        return dataSeriesXY;
    }

    //Calculates highest temp based on a list, this list contains alot of temps from 1 day.
    public double calculateHighestTemp(List<Double> list) {
        double highestTemp = -1000;
        for (double temp : list ){
            if (temp > highestTemp){
                highestTemp = temp;
            }
        }
        return highestTemp;
    }
}
