package com.example.ALSA.weather.server.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class MapUtils {

    private static MapUtils instance = null;

    public MapUtils() {

    }

    public static MapUtils getInstance() {
        if (instance == null) {
            instance = new MapUtils();
        }
        return instance;
    }

    public String sendGet(String uri){
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            HttpRequest request = buildGet(uri);
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return response.body();
    }

    private HttpRequest buildGet(String uri){
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uri))
                .timeout(Duration.ofSeconds(30))
                .setHeader("User-Agent", "ALSA-Tech")
                .build();
    }

    public Map<String, String> getCoordinates(String address) {
        Map<String, String> res = new HashMap<>();
        StringBuilder query = new StringBuilder();
        String[] split = address.split(" ");
        String queryResult = null;

        query.append("https://nominatim.openstreetmap.org/search?q=");

        if (split.length == 0) {
            return null;
        }

        for (int i = 0; i < split.length; i++) {
            query.append(split[i]);
            if (i < (split.length - 1)) {
                query.append("+");
            }
        }
        query.append("&format=json&addressdetails=1");
        try {
            queryResult = sendGet(query.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (queryResult == null) {
            return null;
        }

        Object obj = JSONValue.parse(queryResult);
        if (obj instanceof JSONArray) {
            JSONArray array = (JSONArray) obj;
            if (array.size() > 0) {
                JSONObject jsonObject = (JSONObject) array.get(0);
                String lon = (String) jsonObject.get("lon");
                String lat = (String) jsonObject.get("lat");
                res.put("lon", lon);
                res.put("lat", lat);

            }
        }
        return res;
    }

}
