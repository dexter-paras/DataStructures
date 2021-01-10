/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.delphix.fireball;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author paras.chawla
 * @version $Id: FireBallRunner.java, v 0.1 2020-12-31 12:33 PM paras.chawla Exp $$
 */

/*
Points to remember :

1. The simple framework below can help to decode the location pairs.
+ + : North and East
+ - : North and West
- - : South and West
- + : South and East

Client should enter latitude and longitude within below range without direction
-90.0 to +90.0 Latitude
Explanation:
-12.123 states 12.123 South where as +12.123 states 12.123 North

-180.0 to +180.0 Longitude
Explanation:
-123.123 states 123.123 West where as +123.123 states 123.123 East

NOTE - Direction will be interpt by System automatically from +ve and -ve sign , internally everything works +ve cordinate with direction
2. Two Commands are supported as of now and can be extended to support other commands

*/

public class FireBallRunner {

    private final static OutputPrinter          outputPrinter          = new OutputPrinter();
    private final static FireBallService        fireBallService        = new FireBallService();
    private final static CommandExecutorFactory commandExecutorFactory =
            new CommandExecutorFactory(fireBallService);

    // lat/long - top 5 locations - top5Locations lat long
    // hit_fireball lat long
    // exit lat long
    // Mode - INteractiveMode , FileMode

    // 1. Main method , Execution starts from here or from Test cases
    public static void main(String[] args) throws Exception {

        // 2. User need to enter from console as per commands shown
        outputPrinter.welcome();

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            final String input = reader.readLine();
            final Command command = new Command(input);
            processCommand(command);

            // 3. exit command to exit from execution
            if (command.getCommandName().equals(ExitCommandExecutor.COMMAND_NAME)) {
                break;
            }
            outputPrinter.welcomeAgain();
        }
    }

    // 4. Validate and Execute Command. As of now, System support 2 commands and can be extented to have multiple commands
    private static void processCommand(final Command command) throws Exception {
        final CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
        if (commandExecutor.validate(command)) {
            commandExecutor.execute(command);
        } else {
            throw new InvalidCommandException("Verify Command again or Check documentation above");
        }
    }

    /* ----------------------------------------Service classes----------------------------------- */

    static class FireBallService {

        static FireBallClient fireBallClient = new FireBallClient();

        static FireBallResponse fireBall(Double latitude, Double longitude, Double epicenterRange, Map<String, String> queryMap)
                throws Exception {

            // 1. total data coming from downstream API
            FireBallMetaData fireBallData = fireBallClient.fetchFireBallData(queryMap);

            // 2. Associating DirectionEnum based on +ve/-ve cordinates
            Cordinate latCordinate = new Cordinate(latitude, CordinateType.LATITUDE);
            Cordinate longCordinate = new Cordinate(longitude, CordinateType.LONGITUDE);

            // 3. Converting Lat/Long with directionEnum to epicenter(point where we need to find shooting Star within range *)
            Epicenter epicenter = new Epicenter(latCordinate, longCordinate, epicenterRange);

            // 4. Actual Code where all magic happens !!
            FireBallResponse fireBallResponse = findBrightestFireBall(epicenter, fireBallData);

            // 5. Output to console
            if (StringUtils.isEmpty(fireBallResponse.getBrightness())) {
                outputPrinter.printWithNewLine(
                        "Duh !! Not enough historical data to get best position. Please increase Range and try again");
            } else {
                outputPrinter.printWithNewLine("Latitude " + fireBallResponse.getLatitude(), "Longitude " + fireBallResponse.getLongitude(),
                        "Brightness " + fireBallResponse.getBrightness());
            }
            return fireBallResponse;
        }

        // 1. Filter Brightest FireBall based on epicenter location given by client
        private static FireBallResponse findBrightestFireBall(Epicenter epicenter, FireBallMetaData fireBallData) {

            FireBallResponse fireBallResponse = new FireBallResponse();
            Double maxBrightness = Double.MIN_VALUE;

            for (FireBallCoreInfo fireBallCoreInfo : fireBallData.getData()) {
                if (withinRange(fireBallCoreInfo, epicenter)
                        && maxBrightness < Double.parseDouble(fireBallCoreInfo.getEnergy())) {
                    maxBrightness = Double.parseDouble(fireBallCoreInfo.getEnergy());
                    fireBallResponse.setBrightness(fireBallCoreInfo.getEnergy());
                    fireBallResponse.setLatitude(fireBallCoreInfo.getLatitude() + " " + fireBallCoreInfo.getLatDirection());
                    fireBallResponse.setLongitude(fireBallCoreInfo.getLongitude() + " " + fireBallCoreInfo.getLongDirection());
                }
            }
            return fireBallResponse;
        }

        /* Intuition
            Consider Latitude as a Circle starting from 0 to 90 Left and 0 to 90 Right
            Consider Longitude as a Circle starting from 0 to 180 Left and 0 to 180 Right
            Below code snippet handle all complex cases
        */
        private static Boolean withinRange(FireBallCoreInfo fireBallCoreInfo, Epicenter epicenter) {

            // 1. Find Epicenter ranges
            Double latitudeFloorRange = epicenter.getLatitude().getCordinate() - epicenter.getRange();// 40 N
            Double latitudeCeilRange = epicenter.getLatitude().getCordinate() + epicenter.getRange(); // 120 N

            Boolean withinLongRange = false;
            Boolean withinLatRange = false;

            Double fireBallLatitude = Double.parseDouble(fireBallCoreInfo.getLatitude());
            Double fireBallLongitude = Double.parseDouble(fireBallCoreInfo.getLongitude());

            /* Case 1 -> Happy Case - Latitude within Range , No direction change , Let Range=20
                Eg. Epicenter Latitude 60 N, Longitude 50 E ; Lat Range becomes (40 N, 80 N) && Long Range becomes (30 E, 70 E)
                Lets say -> FireBallCoreInfo Lat 50 N && Long 70 E is within Lat and Long Range
            */

            if (isSameLatDirection(fireBallCoreInfo, epicenter) &&
                    fireBallLatitude >= latitudeFloorRange && fireBallLatitude <= latitudeCeilRange) {
                withinLatRange = true;
            }

            /* Case 2 -> Latitude Not within Range , direction change will happen and should taken into account , Let Range = 40
                Eg. Epicenter Latitude 80 N, Longitude 50 E ; Lat Range becomes (40 N, 120)
                Note -> 120 N is 60S to 90S + 80N to 90N
                && Long Range becomes (30 E, 70 E)
                Lets say -> FireBallCoreInfo Lat 80 N && Long 40 E is within Lat and Long Range
                     latitudeFloorRange=80-40=40 N (40 N to 80 N)
                Here latitudeCeilRange=80+40=120 N ~(60S to 90S) +(80N to 90N)
            */
            if (latitudeCeilRange >= 90) {
                Double ceilDelta = latitudeCeilRange - 90;
                if (isOppositeLatDirection(fireBallCoreInfo, epicenter)
                        && fireBallLatitude <= 90 && fireBallLatitude >= 90 - ceilDelta) {
                    withinLatRange = true;
                }

                if (isSameLatDirection(fireBallCoreInfo, epicenter) &&
                        fireBallLatitude >= latitudeFloorRange && fireBallLatitude <= 90) {
                    withinLatRange = true;
                }
            }

            /* Case 3 -> Latitude Not within Range , direction change will happen and should taken into account , Let Range = 40
                Eg. Epicenter Latitude 10 N, Longitude 50 E ; Lat Range becomes (50 N, -30)
                Note -> -30 N is 0S to 30S + 0N to 10N
                     latitudeFloorRange=10-40=-30 N (0 N to 10 N)+ (30 S to 0S)
                Here latitudeCeilRange=10+40=50 N
            */
            if (latitudeFloorRange <= 0) {
                Double delta = latitudeFloorRange * -1;
                // Checking 0S to 30 S
                if (isOppositeLatDirection(fireBallCoreInfo, epicenter) &&
                        fireBallLatitude >= 0 && fireBallLatitude <= delta) {
                    withinLatRange = true;
                }
                // Checking 0N to 10N + 10N to 50N
                if (isSameLatDirection(fireBallCoreInfo, epicenter) && fireBallLatitude >= 0 && fireBallLatitude <= latitudeCeilRange) {
                    withinLatRange = true;
                }
            }

            Double longitudeFloorRange = epicenter.getLongitude().getCordinate() - epicenter.getRange();
            Double longitudeCeilRange = epicenter.getLongitude().getCordinate() + epicenter.getRange();

            /* Case 4 -> Happy Case - Longitude within Range , No direction change , Let Range=20
                Eg. Epicenter Latitude 50 N, Longitude 50 E ; Lat Range becomes (30 N, 70 N) && Long Range becomes (30 E, 70 E)
                Lets say -> FireBallCoreInfo Lat 60 N && Long 60 E is within Lat and Long Range
            */
            if (isSameLongDirection(fireBallCoreInfo, epicenter) &&
                    fireBallLongitude >= longitudeFloorRange && fireBallLongitude <= longitudeCeilRange) {
                withinLongRange = true;
            }

            /* Case 5 -> Longitude Not within Range , direction change will happen and should taken into account , Let Range = 30
                Eg. Epicenter Latitude 40 N ; Lat Range becomes (10 N, 70N)
                Epicenter Longitude 170 E; Long Range becomes (140 E to 170E) to (170 E to 180 E) + (160 W to 180 W)
                longitudeCeilRange = 200
            */
            if (longitudeCeilRange >= 180) {
                Double delta = longitudeCeilRange - 180; //20

                // Checking from (160 W to 180 W)
                if (isOppositeLongDirection(fireBallCoreInfo, epicenter)
                        && fireBallLongitude <= 180 && fireBallLongitude >= 180 - delta) {
                    withinLongRange = true;
                }

                // Checking from (140 E to 180 E)
                if (isSameLongDirection(fireBallCoreInfo, epicenter) &&
                        fireBallLongitude >= longitudeFloorRange && fireBallLongitude <= 180) {
                    withinLongRange = true;
                }
            }

            /* Case 6 -> Longitude Not within Range , direction change will happen and should taken into account , Let Range = 30
                Eg. Epicenter Latitude 50 N ; Lat Range becomes (20 N, 80N)
                Epicenter Longitude 10 E; Long Range becomes (10 E to 40E) to (20 W to 0 E/W) + (0 E/W to 10 E)
                longitudeFloorRange = 10-30 = -20
            */
            if (longitudeFloorRange <= 0) {
                Double delta = longitudeFloorRange * -1;

                // Checking (20 W to 0 E/W)
                if (isOppositeLongDirection(fireBallCoreInfo, epicenter) &&
                        fireBallLongitude >= 0 && fireBallLongitude
                        <= delta) {
                    withinLongRange = true;
                }
                // Checking (0 E/W to 10 E) to (10 E to 40E)
                if (isSameLongDirection(fireBallCoreInfo, epicenter) &&
                        fireBallLongitude >= 0 && fireBallLongitude
                        <= longitudeCeilRange) {
                    withinLongRange = true;
                }
            }

            return withinLatRange && withinLongRange;
        }

        // 4. filter records based on same latitude direction
        static Boolean isSameLatDirection(FireBallCoreInfo fireBallCoreInfo, Epicenter epicenter) {
            return fireBallCoreInfo.getLatDirection().equals(epicenter.getLatitude().getDirectionEnum().getDirection());
        }

        // 5. filter records based on same latitude direction
        static Boolean isOppositeLatDirection(FireBallCoreInfo fireBallCoreInfo, Epicenter epicenter) {
            return !isSameLatDirection(fireBallCoreInfo, epicenter);
        }

        // 6. filter records based on same Longitude direction
        static Boolean isSameLongDirection(FireBallCoreInfo fireBallCoreInfo, Epicenter epicenter) {
            return fireBallCoreInfo.getLongDirection().equals(epicenter.getLongitude().getDirectionEnum().getDirection());
        }

        // 7. filter records based on same Longitude direction
        static Boolean isOppositeLongDirection(FireBallCoreInfo fireBallCoreInfo, Epicenter epicenter) {
            return !isSameLongDirection(fireBallCoreInfo, epicenter);
        }
    }

    /* ------------------------------Command Design Pattern - Taking command from client-------------------------- */

    static class Command {

        private static final String              SPACE         = " ";
        private static final String              COLON         = ":";
        private              String              commandName;
        private              Double              range;
        private              Double[]            cordinates;
        private              Map<String, String> queryParamMap = new HashMap<>();

        /**
         * Constructor. It takes the input line and parses the command name and param out of it.
         * If the command or its given params are not
         * valid, then {@link InvalidCommandException} is thrown.
         * Eg. {0}<- command name , {1}<- latitude , {2} <- longitude, {3}<-epicenter range {4} <-limit:5, {5} <- date-min:2014-01-01 ...
         * Use Below as an Example:
         * hit_fireball 29.123123 34.24132 15.0d hit_fireball 29.123123 34.2413 10.0d limit:5 date-min:2017-01-01 req-alt:true
         * hit_fireball -29.123123 -34.24132 15.0d
         *
         * @param inputLine Given input command line.
         */
        public Command(final String inputLine) {

            final List<String> tokensList = Arrays.stream(inputLine.trim().split(SPACE))
                    .map(String::trim)
                    .filter(token -> (token.length() > 0)).collect(Collectors.toList());

            // commandName
            commandName = tokensList.get(0).toLowerCase();

            if (commandName.isEmpty()) {
                throw new IllegalArgumentException("Command Name can't be empty");
            }
            //Special case, if commandName is exit,return right away
            else if (commandName.equals("exit")) {
                if (tokensList.size() > 1) {
                    throw new IllegalArgumentException("Exit command shouldn't have any other parameters");
                }
                return;
            }
            // if commandName is hit_fireball, it must have latitude and longitude
            else if (tokensList.size() == 1) {
                throw new IllegalArgumentException("Latitude and Longitude are mandatory");
            }

            // latitude and longitude
            this.cordinates = new Double[2];
            this.cordinates[0] = Double.parseDouble(tokensList.get(1));
            this.cordinates[1] = Double.parseDouble(tokensList.get(2));

            // epicenter range - priority given to range coming from client else Default
            this.range = tokensList.size() == 4 ? Double.parseDouble(tokensList.get(3)) : Constant.DEFAULT_RANGE; // range from client

            // query-params
            for (int j = 4; j < tokensList.size(); j++) {
                String[] keyValue = tokensList.get(j).split(COLON);
                this.queryParamMap.put(keyValue[0], keyValue[1]);
            }

        }

        public String getCommandName() {
            return commandName;
        }

        public Double getRange() {
            return range;
        }

        public Double[] getCordinates() {
            return cordinates;
        }

        public Map<String, String> getQueryParamMap() {
            return queryParamMap;
        }

    }

    /* Acting as a Template for every command */
    static abstract class CommandExecutor {
        protected FireBallService fireballService;
        protected OutputPrinter   outputPrinter;

        public CommandExecutor(final FireBallService fireballService,
                               final OutputPrinter outputPrinter) {
            this.fireballService = fireballService;
            this.outputPrinter = outputPrinter;
        }

        /**
         * Validates that whether a command is valid to be executed or not.
         *
         * @param command Command to be validated.
         * @return Boolean indicating whether command is valid or not.
         */
        public abstract boolean validate(Command command);

        /**
         * Executes the command.
         *
         * @param command Command to be executed.
         */
        public abstract void execute(Command command) throws Exception;
    }

    /* As of now, System support 2 Commands, It can have multiple other commands like Top 5 places near Lat/Long*/
    //
    static class CommandExecutorFactory {
        private Map<String, CommandExecutor> commands = new HashMap<>(); // commandName - CommandExecutor

        public CommandExecutorFactory(final FireBallService fireBallService) {
            final OutputPrinter outputPrinter = new OutputPrinter();
            commands.put(
                    HitFireBallNasaApiCommandExecutor.COMMAND_NAME,
                    new HitFireBallNasaApiCommandExecutor(fireBallService, outputPrinter));
            commands.put(
                    ExitCommandExecutor.COMMAND_NAME,
                    new ExitCommandExecutor(fireBallService, outputPrinter));
        }

        /**
         * Gets {@link CommandExecutor} for a particular command. It basically uses name of command to fetch its corresponding executor.
         *
         * @param command Command for which executor has to be fetched.
         * @return Command executor.
         */
        public CommandExecutor getCommandExecutor(final Command command) {
            final CommandExecutor commandExecutor = commands.get(command.getCommandName());
            if (commandExecutor == null) {
                throw new InvalidCommandException("Verify CommandName again or Check documentation above");
            }
            return commandExecutor;
        }
    }

    /* Actual Executor which is hitting downstream API and getting the desired response */
    static class HitFireBallNasaApiCommandExecutor extends CommandExecutor {
        private static final String COMMAND_NAME = "hit_fireball";

        public HitFireBallNasaApiCommandExecutor(
                final FireBallService fireBallService, final OutputPrinter outputPrinter) {
            super(fireBallService, outputPrinter);
        }

        @Override
        public boolean validate(final Command command) {
            if (command.cordinates.length < 2) {
                return false;
            }
            return validateLatitude(command) && validateLongitude(command);
        }

        /* Valid range of latitude in degrees is -90 and +90 for the southern and northern hemisphere respectively */
        private boolean validateLatitude(Command command) {
            Double latitude = command.cordinates[0];
            return latitude >= -90 && latitude <= 90;
        }

        /* Valid range of longitude in degrees is -180 and +180 for the Western and Eastern hemisphere respectively */
        private boolean validateLongitude(Command command) {
            Double longitude = command.cordinates[1];
            return longitude >= -180 && longitude <= 180;
        }

        @Override
        public void execute(final Command command) throws Exception {
            Double latitude = command.getCordinates()[0];
            Double longitude = command.getCordinates()[1];
            Double epicenterRange = command.getRange();
            Map<String, String> queryParamMap = command.getQueryParamMap();

            // Hitting downstream system to execute the request further
            fireBallService.fireBall(latitude, longitude, epicenterRange, queryParamMap);
        }
    }

    /* Exit command Executor which is needed to break the code-execution */
    static class ExitCommandExecutor extends CommandExecutor {
        public static String COMMAND_NAME = "exit";

        public ExitCommandExecutor(
                final FireBallService fireBallService, final OutputPrinter outputPrinter) {
            super(fireBallService, outputPrinter);
        }

        @Override
        public boolean validate(final Command command) {
            return command.getCordinates() == null && command.getQueryParamMap().isEmpty() && command.getRange() == null;
        }

        @Override
        public void execute(final Command command) {
            outputPrinter.end();
        }
    }

    /* ---------------------------------------------------Model classes----------------------------------------------- */

    /* Data retrieving from Nasa API*/
    static class FireBallMetaData {

        private Signature              signature;
        private String                 count;
        private List<String>           fields;
        private List<FireBallCoreInfo> data;

        public Signature getSignature() {
            return signature;
        }

        public void setSignature(Signature signature) {
            this.signature = signature;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<String> getFields() {
            return fields;
        }

        public void setFields(List<String> fields) {
            this.fields = fields;
        }

        public List<FireBallCoreInfo> getData() {
            return data;
        }

        public void setData(List<FireBallCoreInfo> data) {
            this.data = data;
        }
    }

    /* Creating FireBallCoreInfo Using Builder Design Pattern - makes complex object Immutable */
    static class FireBallCoreInfo {

        // Required properties
        private String energy;
        private String latitude;
        private String longitude;
        private String latDirection;
        private String longDirection;

        // Optional properties as per current scenario, can be used later on
        private String date;
        private String impact;
        private String altitude;
        private String velocity;

        public String getEnergy() {
            return energy;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getLatDirection() {
            return latDirection;
        }

        public String getLongDirection() {
            return longDirection;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {

            // Required properties
            private String energy;
            private String latitude;
            private String longitude;
            private String latDirection;
            private String longDirection;

            // Optional properties as per current scenario, can be used later on
            private String date;
            private String impact;
            private String altitude;
            private String velocity;

            public Builder withEnergy(final String energy) {
                this.energy = energy;
                return this;
            }

            public Builder withLatitude(final String latitude) {
                this.latitude = latitude;
                return this;
            }

            public Builder withLongitude(final String longitude) {
                this.longitude = longitude;
                return this;
            }

            public Builder withLatDirection(final String latDirection) {
                this.latDirection = latDirection;
                return this;
            }

            public Builder withLongDirection(final String longDirection) {
                this.longDirection = longDirection;
                return this;
            }

            public FireBallCoreInfo build() {

                if (this.latitude == null || this.longitude == null || this.energy == null) {
                    throw new RuntimeException("All required properties are not present.");
                }

                FireBallCoreInfo fireBallCoreInfo = new FireBallCoreInfo();
                fireBallCoreInfo.latitude = latitude;
                fireBallCoreInfo.longitude = longitude;
                fireBallCoreInfo.latDirection = latDirection;
                fireBallCoreInfo.longDirection = longDirection;
                fireBallCoreInfo.energy = energy;

                return fireBallCoreInfo;
            }

        }
    }

    static class Signature {
        private String source;
        private String version;

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    // Final response given by service class to outside client
    static class FireBallResponse {

        // 21.4123 NORTH
        private String latitude;

        // -123.4123 WEST
        private String longitude;

        private String brightness;

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public void setBrightness(String brightness) {
            this.brightness = brightness;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getBrightness() {
            return brightness;
        }
    }

    /* Cordinate represent Latitude and Longitude with Direction */
    static class Cordinate {
        private double        cordinate;
        private DirectionEnum directionEnum;

        public DirectionEnum getDirectionEnum() {
            return directionEnum;
        }

        public double getCordinate() {
            return cordinate;
        }

        public Cordinate(double coordinate, CordinateType cordinateType) {

            switch (cordinateType) {
                case LATITUDE:
                    this.directionEnum = coordinate >= 0 ? DirectionEnum.NORTH : DirectionEnum.SOUTH;
                    break;
                case LONGITUDE:
                    this.directionEnum = coordinate >= 0 ? DirectionEnum.EAST : DirectionEnum.WEST;
                    break;
            }
            // When direction is associated with coordinate, then we can multiply by -1 to make it positive
            this.cordinate = coordinate < 0 ? coordinate * -1 : coordinate;
        }
    }

    /* Client location where we would like to find the Brightest shooting star location within the range */
    static class Epicenter {
        Cordinate latitude;
        Cordinate longitude;
        Double    range;

        public Cordinate getLatitude() {
            return latitude;
        }

        public Cordinate getLongitude() {
            return longitude;
        }

        public Double getRange() {
            return range;
        }

        public Epicenter(Cordinate latitude, Cordinate longitude, Double range) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.range = range;
        }

    }

    /* --------------------------------------------Exception Classes------------------------------------------------------ */

    /**
     * Exception given when the command given is invalid or the command params are invalid.
     */
    static class InvalidCommandException extends RuntimeException {
        static final long serialVersionUID = -7034897190745766939L;
        public InvalidCommandException(String s) {
            super(s);
        }
    }

    /* --------------------------------------------------ENUMS------------------------------------------------------------- */
    public enum DirectionEnum {

        NORTH("N"),
        SOUTH("S"),
        WEST("W"),
        EAST("E");

        private final String direction;

        DirectionEnum(String direction) {
            this.direction = direction;
        }

        public String getDirection() {
            return direction;
        }

        public static DirectionEnum getByDirection(String direction) {
            if (direction == null || direction.isEmpty()) {
                return null;
            }
            for (DirectionEnum dir : values()) {
                if (dir.getDirection().equals(direction)) {
                    return dir;
                }
            }
            return null;
        }
    }

    public enum CordinateType {
        LATITUDE,
        LONGITUDE
    }

    /* --------------------------------------------Utlitiy Classes------------------------------------------------------ */

    /* Underlying OutputPrinter can print in console or use logger jar to print everything on logs */
    static class OutputPrinter {

        public void welcome() {
            printWithNewLine(" ******** Welcome to NASA Fireball API ******** \n");
            welcomeCommon();
        }

        public void welcomeAgain() {
            printWithNewLine("\n ******** Glad you're liking this !! Lets play with another test case ******** \n");
            welcomeCommon();
        }

        public void welcomeCommon() {
            printWithNewLine("Enter command like           -> command_name latitude longitude range key:value");
            printWithNewLine("For Positive cordinates Type -> hit_fireball 23.12131 43.231123 15 limit:5");
            printWithNewLine("For Negative cordinates Type -> hit_fireball -23.12131 -43.231123 15 limit:5");
            printWithNewLine("To Exit                 Type -> exit to exit the process");
        }

        public void end() {
            printWithNewLine("Thanks for using NASA Fireball API. Please feedback in case of any improvement");
        }

        public void printWithNewLine(final String msg) {
            System.out.println(msg);
        }

        public void printWithNewLine(final String msg1, final String msg2, final String msg3) {
            System.out.println("\nMaximum probablity to see Shooting STAR as per historical data is");
            System.out.println(msg1);
            System.out.println(msg2);
            System.out.println(msg3);
        }

    }

    // Note - Jackson or Gson isn't supported, and json-simple not supporting directly mapping json to Java POJO
    // This might be boilerplate code and can be easily catered using Jackson Library efficiently
    static class ConversionUtil {

       static HashMap<String,Integer> indexMap = new HashMap<>();

        static {
            indexMap.put("energy", 1);
            indexMap.put("latitude", 3);
            indexMap.put("latDir", 4);
            indexMap.put("longitude", 5);
            indexMap.put("longDir", 6);
        }

        public static FireBallMetaData convertJsonToObject(String jsonString) throws ParseException {
            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject) parser.parse(jsonString);

            FireBallMetaData fireBallMetaData = new FireBallMetaData();
            fireBallMetaData.setCount((String) jsonObj.get(Constant.COUNT));
            fireBallMetaData.setSignature(composeSignature((JSONObject) jsonObj.get(Constant.SIGNATURE)));
            fireBallMetaData.setFields(composeFields((JSONArray) jsonObj.get(Constant.FIELDS)));
            fireBallMetaData.setData(composeData((JSONArray) jsonObj.get(Constant.DATA)));
            return fireBallMetaData;
        }

        private static Signature composeSignature(JSONObject signature) {
            Signature obj = new Signature();
            obj.setSource((String) signature.get(Constant.SOURCE));
            obj.setVersion((String) signature.get(Constant.VERSION));
            return obj;
        }

        private static List<String> composeFields(JSONArray fields) {
            List<String> fieldsObj = new ArrayList<>();
            for (int i = 0; i < fields.size(); i++) {
                fieldsObj.add((String) fields.get(i));
            }
            return fieldsObj;
        }

        /*
         * Using Builders
         * 1. Object remains immutable once it is build
         * */
        private static List<FireBallCoreInfo> composeData(JSONArray data) {
            List<FireBallCoreInfo> dataObj = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {

                String energy = (String) ((JSONArray) data.get(i)).get(indexMap.get("energy"));
                String latitude = (String) ((JSONArray) data.get(i)).get(indexMap.get("latitude"));
                String latDir = (String) ((JSONArray) data.get(i)).get(indexMap.get("latDir"));
                String longitude = (String) ((JSONArray) data.get(i)).get(indexMap.get("longitude"));
                String longDir = (String) ((JSONArray) data.get(i)).get(indexMap.get("longDir"));

                // Note -Filter data having missing mandatory fields , Skip this record
                if (isNullOrEmpty(energy, latitude, latDir, longitude, longDir)) {
                    continue;
                }

                FireBallCoreInfo fireBallCoreInfoBuilder = FireBallCoreInfo.builder().withEnergy(energy).withLatitude(latitude)
                        .withLatDirection(latDir)
                        .withLongitude(longitude).withLongDirection(longDir).build();

                dataObj.add(fireBallCoreInfoBuilder);
            }
            return dataObj;
        }

        private static boolean isNullOrEmpty(String energy, String latitude, String latDir, String longitude, String longDir) {
            return StringUtils.isEmpty(energy) || StringUtils.isEmpty(latitude) ||
                    StringUtils.isEmpty(latDir) || StringUtils.isEmpty(longitude) ||
                    StringUtils.isEmpty(longDir);
        }

    }

    /* --------------------------------------------FireBall Client------------------------------------------------------------- */
    static class FireBallClient {

        private FireBallMetaData fetchFireBallData(Map<String, String> queryMap) throws Exception {
            // 1. Create a URL Object
            URL url = !queryMap.isEmpty() ? new URL(appendQueryMap(Constant.GET_URL, queryMap)) : new URL(Constant.GET_URL);

            // 2. Open HTTP Connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 3. Set Connection properties
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", Constant.USER_AGENT);
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(Constant.DEFAULT_TIMEOUT);

            /* 4. Ensure the Connection Will Be Used to Send Content
            To send request content, let's enable the URLConnection object's doOutput property to true.
            Otherwise, we'll not be able to write content to the connection output stream
            */
            connection.setDoOutput(true);
            InputStream inStream = connection.getInputStream();
            String jsonData = streamToString(inStream); // input stream to string

            /* 5. Disconnect the HttpURLConnection and inStream */
            connection.disconnect();
            inStream.close();
            return ConversionUtil.convertJsonToObject(jsonData);
        }

        private static String streamToString(InputStream inputStream) {
            String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
            return text;
        }

        private String appendQueryMap(String url, Map<String, String> queryMap) throws UnsupportedEncodingException {

            StringBuilder result = new StringBuilder(url + "?");

            // K-> date-min, V->2020-10-10
            for (Map.Entry<String, String> entry : queryMap.entrySet()) {
                result.append(URLEncoder.encode(entry.getKey(), Constant.CHARSET));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), Constant.CHARSET));
                result.append("&");
            }

            String resultString = result.toString();
            return resultString.substring(0, resultString.length() - 1);
        }

    }

    /* -------------------------------------------Constant CLASSES------------------------------------------------------ */

    /* Its important to put urls and password stored securely in HashiCorp Vault */
    private static class Constant {
        public static final String USER_AGENT      = "Mozilla/5.0";
        public static final String GET_URL         = "https://ssd-api.jpl.nasa.gov/fireball.api";
        public static final int    DEFAULT_TIMEOUT = 1000;
        public static final String CHARSET         = "UTF-8";
        public static final Double DEFAULT_RANGE   = 15.0d;
        public static final String COUNT           = "count";
        public static final String SIGNATURE       = "signature";
        public static final String FIELDS          = "fields";
        public static final String DATA            = "data";
        public static final String SOURCE          = "source";
        public static final String VERSION         = "version";

    }

    /* -------------------------------------------Junit CLASSES------------------------------------------------------ */

    public class FireBallRunnerTest {

        private FireBallService                   fireBallService;
        private OutputPrinter                     outputPrinter;
        private HitFireBallNasaApiCommandExecutor hitFireBallNasaApiCommandExecutor;
        private ExitCommandExecutor               exitCommandExecutor;

        @Before
        public void setUp() {

            // prefer creating using mockito or jmock [Note - jars not working in coderpad]
            fireBallService = new FireBallService();
            outputPrinter = new OutputPrinter();
            hitFireBallNasaApiCommandExecutor = new HitFireBallNasaApiCommandExecutor(fireBallService, outputPrinter);
            exitCommandExecutor = new ExitCommandExecutor(fireBallService, outputPrinter);
        }

        @Test
        public void testValidCommand() {
            Assert.assertTrue(hitFireBallNasaApiCommandExecutor.validate(new Command("hit_fireball 12.1231 32.1231")));
            Assert.assertTrue(exitCommandExecutor.validate(new Command("exit")));
        }

        @Test
        public void testInValidCommand() {

            //1. wrong command Name, it must be hit_fireball or exit
            Assert.assertFalse(hitFireBallNasaApiCommandExecutor.validate(new Command("wrong_commandName 121.1231 222.1231")));

            //2. Overflow Latitude and Longitude passed by client
            Assert.assertFalse(hitFireBallNasaApiCommandExecutor.validate(new Command("hit_fireball 121.1231 222.1231")));

            //3. Wrong Latitude and Longitude passed by client
            Assert.assertFalse(hitFireBallNasaApiCommandExecutor.validate(new Command("hit_fireball -121.1231 -222.1231")));

            //4. query-map passed by client must be as per documentation, here date+min should be date-min
            Assert.assertFalse(
                    hitFireBallNasaApiCommandExecutor.validate(new Command("hit_fireball -21.123 -150.1231 15 date+min:2020-10-20")));

            // 5. exit command shouldn't have any other parameters except commandType
            Assert.assertFalse(exitCommandExecutor.validate(new Command("exit 12.123")));
        }

        @Test
        public void testCommandExecution() throws Exception {

            // 1. Get Brightest start based on lat/long and range=10 and limit=10
            hitFireBallNasaApiCommandExecutor.execute(new Command("hit_fireball 12.1231 32.1231 10 limit:10"));

            // 2. Gracefully shutdown
            exitCommandExecutor.execute(new Command("exit"));
        }

        @Test
        public void testEpicenterLatRangeBeyondNinety() throws Exception{

            // 1. Get Brightest start based on lat/long and range=20 , Direction changing
            hitFireBallNasaApiCommandExecutor.execute(new Command("hit_fireball 80.1231 32.1231 20"));

            // 2. Get Brightest start based on lat/long and range=30 , Direction changing
            hitFireBallNasaApiCommandExecutor.execute(new Command("hit_fireball 80.1231 32.1231 20"));
        }

        @Test
        public void testEpicenterLongRangeBeyondOneEighty() throws Exception{

            // 1. Get Brightest start based on lat/long and range=30 , Direction changing
            hitFireBallNasaApiCommandExecutor.execute(new Command("hit_fireball 80.1231 170.1231 30"));

            // 2. Get Brightest start based on lat/long and range=30 , Direction changing
            hitFireBallNasaApiCommandExecutor.execute(new Command("hit_fireball 80.1231 10.1231 30"));
        }
    }
}