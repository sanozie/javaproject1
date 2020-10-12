import com.google.gson.*;
import com.google.gson.annotations.*;

import java.io.*;
import java.util.*;

public class WeatherAPI {
    private final static String WEATHER_API_KEY = "385a012ad9f8d5f4d5c91a3684c9b664";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Please enter a city that you'd like to see weather data for: ");
        String city = scan.nextLine();
        Map<String, String> params = new HashMap<>();
        params.put("q", city);
        params.put("appid", WEATHER_API_KEY);
        WeatherRes weather = null;

        try {
            weather = getWeatherData(params);
        } catch (IOException e) {
            e.printStackTrace();
        }

        printData(weather);

    }

    // Methods

    /**
     * Returns data from API cal as WeatherRes class.
     * @param params city in which data is called for
     * @return WeatherRes
     * @throws IOException
     */
    public static WeatherRes getWeatherData(Map<String, String> params) throws IOException {
        // Generate link
        String queryString = "https://api.openweathermap.org/data/2.5/weather?"
                + APIWrapper.getParamsString(params);

        StringBuffer content = APIWrapper.callAPI(queryString);

        return new GsonBuilder().create().fromJson(content.toString(), WeatherRes.class);
    }

    public static void printData(WeatherRes data) {
        System.out.println("Longitude: " + data.coord.lon);
        System.out.println("Latitude: " + data.coord.lat);
        System.out.println("Temperature (Right Now): " + getFahrenheit(data.main.temp) + " Degrees");
        System.out.println("Feels like: " + getFahrenheit(data.main.feelsLike) + " Degrees");
        System.out.println("Max: " + getFahrenheit(data.main.tempMax) + " Degrees");
        System.out.println("Min: " + getFahrenheit(data.main.tempMin) + " Degrees");
        System.out.println("Looks like: " + getFahrenheit(data.main.feelsLike));
    }

    public static String printDataAsString(WeatherRes data) {
        return "Longitude: " + data.coord.lon + ", " +
                "Latitude: " + data.coord.lat + ", " +
                "Temperature (Right Now): " + getFahrenheit(data.main.temp) + " Degrees, " +
                "Feels like: " + getFahrenheit(data.main.feelsLike) + " Degrees";
    }

    public static double getFahrenheit(double kelvin) {
        return Math.round((((kelvin - 273) * 9d/5) + 32) * 100.0) / 100.0;
    }

    // Wrappers
    public static class ParameterStringBuilder {

    }

    // Weather API Bindings
    public class Clouds {

        /**
         *
         * (Required)
         *
         */
        @SerializedName("all")
        @Expose
        public int all;

    }

    public class Coord {

        /**
         *
         * (Required)
         *
         */
        @SerializedName("lon")
        @Expose
        public double lon;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("lat")
        @Expose
        public double lat;

    }

    public static class Main {

        /**
         *
         * (Required)
         *
         */
        @SerializedName("temp")
        @Expose
        public double temp;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("feels_like")
        @Expose
        public double feelsLike;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("temp_min")
        @Expose
        public double tempMin;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("temp_max")
        @Expose
        public double tempMax;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("pressure")
        @Expose
        public int pressure;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("humidity")
        @Expose
        public int humidity;

    }

    public static class Sys {

        /**
         *
         * (Required)
         *
         */
        @SerializedName("type")
        @Expose
        public int type;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("id")
        @Expose
        public int id;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("country")
        @Expose
        public String country;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("sunrise")
        @Expose
        public int sunrise;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("sunset")
        @Expose
        public int sunset;

    }


    public static class WeatherRes {

        /**
         *
         * (Required)
         *
         */
        @SerializedName("coord")
        @Expose
        public Coord coord;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("weather")
        @Expose
        public List<Object> weather = null;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("base")
        @Expose
        public String base;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("main")
        @Expose
        public Main main;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("visibility")
        @Expose
        public int visibility;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("wind")
        @Expose
        public Wind wind;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("clouds")
        @Expose
        public Clouds clouds;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("dt")
        @Expose
        public int dt;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("sys")
        @Expose
        public Sys sys;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("timezone")
        @Expose
        public int timezone;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("id")
        @Expose
        public int id;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("name")
        @Expose
        public String name;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("cod")
        @Expose
        public int cod;

    }

    public static class Wind {

        /**
         *
         * (Required)
         *
         */
        @SerializedName("speed")
        @Expose
        public double speed;
        /**
         *
         * (Required)
         *
         */
        @SerializedName("deg")
        @Expose
        public int deg;

    }

}
