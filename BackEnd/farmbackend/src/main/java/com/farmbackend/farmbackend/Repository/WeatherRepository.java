package com.farmbackend.farmbackend.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmbackend.farmbackend.Entities.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    // Find by Farmer ID
    // List<Weather> findByFarmer_FarmerId(int farmerId);

    // // Find by specific date
    // List<Weather> findByDate(LocalDate date);

    // // Find records within a date range
    // List<Weather> findByDateBetween(LocalDate startDate, LocalDate endDate);
}