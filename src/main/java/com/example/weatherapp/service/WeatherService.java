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

    @SuppressWarnings("unchecked") // <-- removes 4 warnings safely
    public WeatherResponse getWeatherDataForCity(String city) {

        String url = String.format(
                "https://api.openweathermap.org/data/2.5/weather?q=%s&units=imperial&appid=%s",
                city,
                apiKey
        );

        Map<String, Object> json = restTemplate.getForObject(url, Map.class);
        if (json == null) {
            throw new RuntimeException("API returned no data");
        }

        Map<String, Object> main = safeMap(json.get("main"));
        List<Map<String, Object>> weatherList = safeList(json.get("weather"));
        Map<String, Object> wind = safeMap(json.get("wind"));

        String description = weatherList.isEmpty()
                ? "N/A"
                : (String) weatherList.get(0).getOrDefault("description", "N/A");

        WeatherResponse result = new WeatherResponse();
        result.setCity(city);
        result.setTemperature(num(main.get("temp")));
        result.setHumidity(num(main.get("humidity")));
        result.setDescription(description);
        result.setWindSpeed(num(wind.get("speed")));

        return result;
    }

    private Map<String, Object> safeMap(Object obj) {
        return obj instanceof Map ? (Map<String, Object>) obj : Map.of();
    }
    private List<Map<String, Object>> safeList(Object obj) {
        return obj instanceof List ? (List<Map<String, Object>>) obj : List.of();
    }
    private double num(Object val) {
        return val instanceof Number ? ((Number) val).doubleValue() : 0.0;
    }
}
