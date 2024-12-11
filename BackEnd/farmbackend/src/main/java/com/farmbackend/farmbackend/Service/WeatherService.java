package com.farmbackend.farmbackend.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmbackend.farmbackend.Entities.Weather;
import com.farmbackend.farmbackend.Repository.WeatherRepository;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    public Weather createWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    public List<Weather> getAllWeather() {
        return weatherRepository.findAll();
    }

    // public List<Weather> getWeatherByFarmerId(int farmerId) {
    //     return weatherRepository.findByFarmer_FarmerId(farmerId);
    // }

    // public List<Weather> getWeatherByDate(LocalDate date) {
    //     return weatherRepository.findByDate(date);
    // }

    // public List<Weather> getWeatherByDateRange(LocalDate startDate, LocalDate endDate) {
    //     return weatherRepository.findByDateBetween(startDate, endDate);
    // }

    public Weather updateWeather(int id, Weather weather) {
        Weather existingWeather = weatherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Weather not found"));
        // existingWeather.setDate(weather.getDate());
        existingWeather.setTemperature(weather.getTemperature());
        existingWeather.setHumidity(weather.getHumidity());
        // existingWeather.rmer(weather.getFarmer());
        return weatherRepository.save(existingWeather);
    }

    public void deleteWeather(int id) {
        weatherRepository.deleteById(id);
    }
}
