import org.jibble.pircbot.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Bot extends PircBot {
    boolean nextIsWeather = false;
    boolean nextIsTrend = false;

    private final static String WEATHER_API_KEY = "385a012ad9f8d5f4d5c91a3684c9b664";
    private final static String TRENDING_API_KEY = "cc279df4edbe8668c4742d8271c03ee4";


    public Bot() {
        this.setName("SamuelAnozie");
    }

    public void onMessage(String channel, String sender,
                          String login, String hostname, String message) {

        if (message.equalsIgnoreCase("time")) {
            String time = new java.util.Date().toString();
            sendMessage(channel, sender + ": The time is now " + time);
        }

        if (message.equalsIgnoreCase("start")) {
            sendMessage(channel, "Hey, it's time to start! Would you like to see some weather or trending movies?");
        }

        if (this.nextIsWeather) {
            Map<String, String> params = new HashMap<>();
            params.put("q", message);
            params.put("appid", WEATHER_API_KEY);
            try {
                WeatherAPI.WeatherRes weather = WeatherAPI.getWeatherData(params);
                sendMessage(channel, "here's your data: " + WeatherAPI.printDataAsString(weather));
            } catch (IOException e) {
                sendMessage(channel, "Oops, looks like there was a bug. I might not be able to get your data.");
                sendMessage(channel, e.getMessage());
            }

            this.nextIsWeather = false;
        }

        if (this.nextIsTrend) {
            try {
                TrendingAPI.TrendingRes trend = TrendingAPI.getTrendingData(message);
                sendMessage(channel, "here's your data: " + TrendingAPI.printDataAsString(trend));
            } catch (IOException e) {
                sendMessage(channel, "Oops, looks like there was a bug. I might still be able to get your data.");
                sendMessage(channel, e.getMessage());
            }

            this.nextIsTrend = false;
        }

        if (message.equalsIgnoreCase("weather")) {
            sendMessage(channel, "Nice! What city would you like to view?");
            this.nextIsWeather = true;
        }

        if (message.equalsIgnoreCase("trending")) {
            sendMessage(channel, "Nice! Would you like to see what's been trending in the past day, or in the past week?");
            this.nextIsTrend = true;
        }
    }
}

