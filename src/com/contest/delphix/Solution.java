/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.delphix;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: Solution.java, v 0.1 2020-12-30 3:36 PM paras.chawla Exp $$
 */
public class Solution {


    public static void main(String[] args) throws IOException, ParamIllegalException, ParseException,
            org.json.simple.parser.ParseException {

        System.out.println(fireball("31.7937007N", "96.4039064E"));
    }

    private static Fireball fireball(String latitude, String longitude)
            throws ParamIllegalException, IOException, ParseException, org.json.simple.parser.ParseException {

        //TODO use Container
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("date-min", "2020-01-01");
        queryParams.put("limit","5");
        List<List<String>> nasaFireBallResponse = Utility.sendGETAndFetchFireballs(queryParams, Constant.GET_URL, Constant.DEFAULT_TIMEOUT);
        List<FireballData> fireballDataList = ConvertToGeoCoordinates.convertApiResponse(nasaFireBallResponse);
        GeoCoordinates epicentre = new GeoCoordinates(new Latitude(latitude), new Longitude(longitude));
        if (nasaFireBallResponse != null) {
            return Utility.findBrightestFireBall(fireballDataList, epicentre);
        }
        return null;
    }

    public static class ConvertToGeoCoordinates {
        public static List<FireballData> convertApiResponse(List<List<String>> apiResponse) throws ParamIllegalException {
            List<FireballData> fireballDataList = new ArrayList<>();
            for (List<String> response : apiResponse) {
                Latitude latitude = new Latitude(Double.parseDouble(response.get(3)), response.get(4));
                Longitude longitude = new Longitude(Double.parseDouble(response.get(5)), response.get(6));
                GeoCoordinates coordinates = new GeoCoordinates(latitude, longitude);
                FireballData fireballData = new FireballData(Double.parseDouble(response.get(1)), coordinates);
                fireballDataList.add(fireballData);
            }
            return fireballDataList;
        }
    }

    public static class Latitude {
        Double coordinate;
        String direction;
        Double signedCoord;

        public Latitude(Double coordinate, String direction) throws ParamIllegalException {
            if (isValidDirection(direction) && isValidCoordinate(coordinate)) {
                this.coordinate = coordinate;
                this.direction = direction;
                this.signedCoord = getSignedCoord(direction, coordinate);
            } else {
                throw new ParamIllegalException("Illegal Latitudinal Coordinates");
            }
        }

        //latitude =42.3578N
        public Latitude(String input) throws ParamIllegalException {
            String direction = input.substring(input.length() - 1).toUpperCase();
            Double coordinate = Double.parseDouble(input.substring(0, input.length() - 1));

            if (isValidDirection(direction) && isValidCoordinate(coordinate)) {
                this.coordinate = coordinate;
                this.direction = direction;
                this.signedCoord = getSignedCoord(direction, coordinate);
            } else {
                throw new ParamIllegalException("Illegal Latitudinal Coordinates");
            }
        }

        private Boolean isValidCoordinate(Double coordinate) {
            if (coordinate == null) {
                return false;
            } else if (coordinate >= 0 || coordinate <= 90) {
                return true;
            }

            return false;
        }

        private Boolean isValidDirection(String direction) {
            if (direction == null || "".equals(direction)) {
                return false;
            } else if (Direction.NORTH == Direction.getByDirection(direction) ||
                    Direction.SOUTH == Direction.getByDirection(direction)) {
                return true;
            }

            return false;
        }

        private Double getSignedCoord(String direction, Double coordinate) {
            if (direction.equals(Direction.NORTH.getDirection())) {
                return coordinate;
            } else if (direction.equals(Direction.SOUTH.getDirection())) {
                return -1 * coordinate;
            }

            return null;
        }

        @Override
        public String toString() {
            return "Latitude{" +
                    "coordinate=" + coordinate +
                    ", direction='" + direction + '\'' +
                    ", signedCoord=" + signedCoord +
                    '}';
        }
    }

    public static class Longitude {
        Double coordinate;
        String direction;
        Double signedCoord;

        public Longitude(Double coordinate, String direction) throws ParamIllegalException {
            if (isValidDirection(direction) && isValidCoordinate(coordinate)) {
                this.coordinate = coordinate;
                this.direction = direction;
                this.signedCoord = getSignedCoord(direction, coordinate);
            } else {
                throw new ParamIllegalException("Illegal Longitudinal Coordinates");
            }
        }

        //longitude =42.3578E
        public Longitude(String input) throws ParamIllegalException {
            String direction = input.substring(input.length() - 1).toUpperCase();
            Double coordinate = Double.parseDouble(input.substring(0, input.length() - 1));

            if (isValidDirection(direction) && isValidCoordinate(coordinate)) {
                this.coordinate = coordinate;
                this.direction = direction;
                this.signedCoord = getSignedCoord(direction, coordinate);
            } else {
                throw new ParamIllegalException("Illegal Longitudinal Coordinates");
            }
        }

        private Boolean isValidCoordinate(Double coordinate) {
            if (coordinate == null) {
                return false;
            } else {
                return coordinate >= 0 || coordinate <= 180;
            }
        }

        private Boolean isValidDirection(String direction) {
            if (direction == null || "".equals(direction)) {
                return false;
            } else {
                return (Direction.WEST == Direction.getByDirection(direction) ||
                        Direction.EAST == Direction.getByDirection(direction));
            }
        }

        private Double getSignedCoord(String direction, Double coordinate) {
            if (direction.equals(Direction.EAST.getDirection())) {
                return coordinate;
            } else if (direction.equals(Direction.WEST.getDirection())) {
                return -1 * coordinate;
            }

            return null;
        }

        @Override
        public String toString() {
            return "Longitude{" +
                    "coordinate=" + coordinate +
                    ", direction='" + direction + '\'' +
                    ", signedCoord=" + signedCoord +
                    '}';
        }
    }

    public static class Fireball {
        private double         brightness;
        private GeoCoordinates geoCoordinates;

        /**
         * Getter method for property <tt>brightness</tt>.
         *
         * @return property value of brightness
         */
        public double getBrightness() {
            return brightness;
        }

        /**
         * Setter method for property <tt>brightness</tt>.
         *
         * @param brightness value to be assigned to property brightness
         */
        public void setBrightness(double brightness) {
            this.brightness = brightness;
        }

        /**
         * Getter method for property <tt>geoCoordinates</tt>.
         *
         * @return property value of geoCoordinates
         */
        public GeoCoordinates getGeoCoordinates() {
            return geoCoordinates;
        }

        /**
         * Setter method for property <tt>geoCoordinates</tt>.
         *
         * @param geoCoordinates value to be assigned to property geoCoordinates
         */
        public void setGeoCoordinates(GeoCoordinates geoCoordinates) {
            this.geoCoordinates = geoCoordinates;
        }

        @Override
        public String toString() {
            return "Fireball{" +
                    "brightness=" + brightness +
                    ", geoCoordinates=" + geoCoordinates +
                    '}';
        }
    }

    public static class FireballData {
        private double         brightness;
        private GeoCoordinates geoCoordinates;

        public FireballData(double brightness, GeoCoordinates geoCoordinates) {
            this.brightness = brightness;
            this.geoCoordinates = geoCoordinates;
        }
    }

    public class FireBallResponse {
        private Signature          signature;
        private String             count;
        private List<String>       fields;
        private List<List<String>> data;

        /**
         * Getter method for property <tt>count</tt>.
         *
         * @return property value of count
         */
        public String getCount() {
            return count;
        }

        /**
         * Setter method for property <tt>count</tt>.
         *
         * @param count value to be assigned to property count
         */
        public void setCount(String count) {
            this.count = count;
        }

        /**
         * Getter method for property <tt>fields</tt>.
         *
         * @return property value of fields
         */
        public List<String> getFields() {
            return fields;
        }

        /**
         * Setter method for property <tt>fields</tt>.
         *
         * @param fields value to be assigned to property fields
         */
        public void setFields(List<String> fields) {
            this.fields = fields;
        }

        /**
         * Getter method for property <tt>signature</tt>.
         *
         * @return property value of signature
         */
        public Signature getSignature() {
            return signature;
        }

        /**
         * Setter method for property <tt>signature</tt>.
         *
         * @param signature value to be assigned to property signature
         */
        public void setSignature(Signature signature) {
            this.signature = signature;
        }

        /**
         * Getter method for property <tt>data</tt>.
         *
         * @return property value of data
         */
        public List<List<String>> getData() {
            return data;
        }

        /**
         * Setter method for property <tt>data</tt>.
         *
         * @param data value to be assigned to property data
         */
        public void setData(List<List<String>> data) {
            this.data = data;
        }
    }

    public class Signature {
        private String source;
        private String version;

        /**
         * Getter method for property <tt>source</tt>.
         *
         * @return property value of source
         */
        public String getSource() {
            return source;
        }

        /**
         * Setter method for property <tt>source</tt>.
         *
         * @param source value to be assigned to property source
         */
        public void setSource(String source) {
            this.source = source;
        }

        /**
         * Getter method for property <tt>version</tt>.
         *
         * @return property value of version
         */
        public String getVersion() {
            return version;
        }

        /**
         * Setter method for property <tt>version</tt>.
         *
         * @param version value to be assigned to property version
         */
        public void setVersion(String version) {
            this.version = version;
        }

    }

    public static class Utility {

        public static Fireball findBrightestFireBall(List<FireballData> fireballDataList, GeoCoordinates epicentre) {
            Fireball fireball = new Fireball();
            double maxBrightness = Double.MIN_VALUE;
            for (FireballData fireballData : fireballDataList) {
                if (fireballData.geoCoordinates.withinRange(epicentre)) {
                    if (maxBrightness < fireballData.brightness) {
                        maxBrightness = fireballData.brightness;
                        fireball.brightness = fireballData.brightness;
                        fireball.geoCoordinates = fireballData.geoCoordinates;
                    }
                }
            }

            return fireball;
        }

        static List<List<String>> sendGETAndFetchFireballs(Map<String, String> parameters, String url, int timeout)
                throws IOException, ParamIllegalException, ParseException, org.json.simple.parser.ParseException {
            if (url == null) {
                throw new ParamIllegalException("url is null");
            }
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", Constant.USER_AGENT);
            con.setConnectTimeout(timeout);
            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(getParamsString(parameters));
            out.flush();
            out.close();
            int responseCode = con.getResponseCode();
            List<List<String>> responseList = null;
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                JSONParser parser = new JSONParser();
                JSONObject jsonObj = (JSONObject) parser.parse(response.toString());
                JSONArray data = (JSONArray) jsonObj.get("data");
                responseList = new ArrayList<>();

                if (data != null) {
                    for (int i = 0; i < data.size(); i++) {
                        try {
                            JSONArray jsonArray = (JSONArray) data.get(i);
                            List<String> stringList = new ArrayList<>();
                            if (jsonArray != null) {
                                for (int j = 0; j < 7; j++) {
                                    try {
                                        String s = (String) jsonArray.get(j);
                                        if (s != null) {
                                            stringList.add(s);
                                        }
                                    } catch (Exception jsonException) {
                                        //donothing
                                    }
                                }
                                if (stringList.size() >= 6) {
                                    responseList.add(stringList);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                System.out.println("GET request not worked");
            }
            return responseList;
        }

        public static String getParamsString(Map<String, String> params)
                throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();

            for (Map.Entry<String, String> entry : params.entrySet()) {
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                result.append("&");
            }

            String resultString = result.toString();
            return resultString.length() > 0
                    ? resultString.substring(0, resultString.length() - 1)
                    : resultString;
        }

    }

    public static class GeoCoordinates {
        Latitude  latitude;
        Longitude longitude;

        public GeoCoordinates(Latitude latitude, Longitude longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        // 30.128319247, 90.12423423
        // case 1  45 degree -> 30 to 60
        // case 2 175 -> 180 - 10
        // case 3 0 degree
        public Boolean withinRange(GeoCoordinates epicentre, Double range) {
            Double fireballLongitude = this.longitude.signedCoord;
            Double fireballLatitude = this.latitude.signedCoord;

            Boolean withinLong = false;
            Boolean withinLat = false;

            if (epicentre.longitude.signedCoord >= 180 - range) {
                Double excessLong = (epicentre.longitude.signedCoord + range) - 180;
                Double overflow = -180 + excessLong;

                if (fireballLongitude >= (epicentre.longitude.signedCoord - range) ||
                        (fireballLongitude >= (180 - range) || fireballLongitude <= (overflow))) {
                    withinLong = true;
                }
            } else if (epicentre.longitude.signedCoord <= -180 + range) {
                Double excessLong = (epicentre.longitude.signedCoord - range) + 180;
                Double overflow = 180 + excessLong;

                if (fireballLongitude > (epicentre.longitude.signedCoord - range + 1) ||
                        (fireballLongitude <= (-180 + range) || fireballLongitude >= (overflow))) {
                    withinLong = true;
                }
            } else {
                if (fireballLongitude < (epicentre.longitude.signedCoord + range + 1) &&
                        fireballLongitude > (epicentre.longitude.signedCoord - range - 1)) {
                    withinLong = true;
                }
            }

            if (epicentre.latitude.signedCoord >= 90 - range) {
                Double excessLong = (epicentre.latitude.signedCoord + range) - 90;
                Double overflow = -90 + excessLong;

                if (fireballLongitude >= (epicentre.latitude.signedCoord - range) ||
                        (fireballLongitude >= (90 - range) || fireballLongitude <= (overflow))) {
                    withinLat = true;
                }
            } else if (epicentre.latitude.signedCoord <= -90 + range) {
                Double excessLong = (epicentre.latitude.signedCoord - range) + 90;
                Double overflow = 90 + excessLong;

                if (fireballLongitude >= (epicentre.latitude.signedCoord - range) ||
                        (fireballLongitude <= (-range) || fireballLongitude >= (overflow))) {
                    withinLat = true;
                }
            } else {
                if (fireballLatitude < (epicentre.latitude.signedCoord + range + 1) &&
                        fireballLatitude >= (epicentre.latitude.signedCoord - range - 1)) {
                    withinLat = true;
                }
            }

            if (withinLat && withinLong) {
                return true;
            }

            return false;
        }

        public Boolean withinRange(GeoCoordinates epicentre) {
            return withinRange(epicentre, 15d);
        }

        @Override
        public String toString() {
            return "GeoCoordinates{" +
                    "latitude=" + latitude +
                    ", longitude=" + longitude +
                    '}';
        }
    }

    /* -------------------------------------------EXCEPTION CLASSES------------------------------------------------------ */

    static class ParamIllegalException extends Exception {
        public ParamIllegalException(String s) {
            // Call constructor of parent Exception
            super(s);
        }
    }

    /* -------------------------------------------Constant CLASSES------------------------------------------------------ */

    static class Constant{
        private static final String USER_AGENT = "Mozilla/5.0";
        private static final String GET_URL = "https://ssd-api.jpl.nasa.gov/fireball.api";
        private static final int DEFAULT_TIMEOUT = 1000;
    }


    /* --------------------------------------------------ENUMS------------------------------------------------------------- */
    public enum Direction {

        NORTH("North"),
        SOUTH("South"),
        WEST("West"),
        EAST("East");

        private final String direction;

        Direction(String direction) {
            this.direction = direction;
        }

        public String getDirection() {
            return direction;
        }

        public static Direction getByDirection(String direction) {
            if (direction == null || direction.isEmpty()) {
                return null;
            }
            for (Direction dir : values()) {
                if (dir.getDirection().equals(direction)) {
                    return dir;
                }
            }
            return null;
        }

    }

    /* -------------------------------------------JUNIT TEST CASES------------------------------------------------------ */
    public class NasaAPITest {
        @Test
        public void test_API_Url_null() throws IOException {
            Map<String, String> parameters = new HashMap<>();
            List<List<String>> actualResult = null;
            parameters.put("date-min", "2017-01-01");
            try {
                actualResult = Solution.Utility.sendGETAndFetchFireballs(parameters, null, 1000);
            } catch (ParamIllegalException | ParseException | org.json.simple.parser.ParseException e) {

            }
            Assertions.assertNull(actualResult);
        }

        @Test
        public void test_API_Url_Timeout() throws IOException {
            Map<String, String> parameters = new HashMap<>();
            List<List<String>> actualResult = null;
            parameters.put("date-min", "2017-01-01");
            try {
                actualResult = Solution.Utility.sendGETAndFetchFireballs(parameters, null, 0);
            } catch (ParamIllegalException | ParseException | org.json.simple.parser.ParseException e) {

            }
            Assertions.assertNull(actualResult);
        }
    }
}