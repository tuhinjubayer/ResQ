package com.resq.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class LocationUtil {

    public static String getLocation() {
        try {
            URL url = new URL("http://ip-api.com/json/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());
            String city = json.getString("city");
            double lat = json.getDouble("lat");
            double lon = json.getDouble("lon");

            return city + " (Lat: " + lat + ", Lon: " + lon + ")";
        } catch (Exception e) {
            e.printStackTrace();
            return "Location not available";
        }
    }
}
