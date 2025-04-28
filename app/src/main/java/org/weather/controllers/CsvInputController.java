package org.weather.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.weather.services.WeatherService;

@Controller
public class CsvInputController {
    private final WeatherService weatherService;

    @Autowired
    public CsvInputController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping("/generate-csv")
    public String handleCityInput(@RequestParam("cities") String cities, Model model) {
        // Pass the cities to the next step (processing, CSV generation, etc.)
        model.addAttribute("cities", cities);
        // Optionally, you can trigger weather data fetching here or redirect to a
        // processing page
        return "home";
    }
}