package com.example.weatherapp.model;

import java.util.List;

public class WeatherResponse {

    public Main main;
    public List<Weather> weather;
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


