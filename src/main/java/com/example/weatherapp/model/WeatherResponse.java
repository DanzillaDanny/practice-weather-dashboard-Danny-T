package com.example.weatherapp.model;

public class WeatherResponse {

    public Main main;
    public Weather[] weather;
    public Wind wind;

    public static class Main {
        public double temp;
        public double humidity;
    }

    public static class Weather {
        public String description;
    }

    public static class Wind {
        public double speed;
    }
}
