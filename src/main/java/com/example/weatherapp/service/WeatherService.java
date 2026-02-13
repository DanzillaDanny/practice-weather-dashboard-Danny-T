package com.example.weatherapp.service;

import com.example.weatherapp.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getWeatherDataForCity(String city) {

        String requestUrl = String.format(
                "https://api.openweathermap.org/data/2.5/weather?q=%s&units=imperial&appid=%s",
                city,
                apiKey
        );

        try {
            return restTemplate.getForObject(requestUrl, WeatherResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching weather: " + e.getMessage());
        }
    }
}




