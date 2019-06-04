package weather.izam.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import weather.izam.demo.model.Currently;
import weather.izam.demo.model.Location;
import weather.izam.demo.model.Weather;
import weather.izam.demo.persistence.WeatherEntity;
import weather.izam.demo.persistence.WeatherRepository;
import weather.izam.demo.service.LocationConsumer;
import weather.izam.demo.service.WeatherConsumer;

import javax.validation.Valid;

@RestController
public class Controller {
    private static final Logger log = LoggerFactory.getLogger(Controller.class);
    private static final String weatherKey = "bea3233e802b6839cd4aedaeb2e4f778";

    @Autowired
    private WeatherRepository repository;
    @Autowired
    private LocationConsumer locationService;

    @Autowired
    private WeatherConsumer weatherService;

    @GetMapping(path = "/weather")
    public @ResponseBody
    ResponseEntity<Currently> addNewIp(@Valid @RequestParam String ip) {
        WeatherEntity weatherEntity = new WeatherEntity();
        Weather weatherData;
        try {
            weatherEntity.setIp(ip);

            Location loc = locationService.getLocation(ip);
            weatherData = weatherService.getWeather(weatherKey, loc);

            weatherEntity.setLocation(loc);
            weatherEntity.setTimezone(weatherData.getTimezone());
            weatherEntity.setCountry(loc.getCountry());
            weatherEntity.setCurrently(weatherData.getCurrently());

            repository.save(weatherEntity);
            return ResponseEntity.ok(weatherData.getCurrently());
        } catch (RestClientException ex) {
            String message = String.format("Error: %s", ex.getMessage());
            log.error(message);
            return ResponseEntity.badRequest().build();
        }
    }
}
