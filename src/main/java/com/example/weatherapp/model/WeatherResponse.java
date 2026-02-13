package com.example.weatherapp.model;

public class WeatherResponse {

    private String city;
    private double temperature;
    private double humidity;
    private String description;
    private double windSpeed;

    public WeatherResponse() {}

    public WeatherResponse(String city, double temperature, double humidity, String description, double windSpeed) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.description = description;
        this.windSpeed = windSpeed;
    }

    public String getCity() { return city; }
    public double getTemperature() { return temperature; }
    public double getHumidity() { return humidity; }
    public String getDescription() { return description; }
    public double getWindSpeed() { return windSpeed; }

    public void setCity(String city) { this.city = city; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    public void setHumidity(double humidity) { this.humidity = humidity; }
    public void setDescription(String description) { this.description = description; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }
}




