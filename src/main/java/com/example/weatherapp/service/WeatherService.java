package com.example.weatherapp.service;

import com.example.weatherapp.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

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
            Map<String, Object> response = restTemplate.getForObject(requestUrl, Map.class);

            Map<String, Object> main = (Map<String, Object>) response.get("main");
            List<Map<String, Object>> weatherList = (List<Map<String, Object>>) response.get("weather");
            Map<String, Object> wind = (Map<String, Object>) response.get("wind");

            double temp = ((Number) main.get("temp")).doubleValue();
            double humidity = ((Number) main.get("humidity")).doubleValue();
            String description = (String) weatherList.get(0).get("description");
            double windSpeed = wind != null ? ((Number) wind.get("speed")).doubleValue() : 0;

            return new WeatherResponse(
                    city,
                    temp,
                    humidity,
                    description,
                    windSpeed
            );

        } catch (Exception e) {
            throw new RuntimeException("Error fetching weather: " + e.getMessage());
        }
    }
}
