package weather.izam.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import weather.izam.demo.model.Location;
import weather.izam.demo.model.Weather;

import java.util.Locale;

@Component
@Service
@CacheConfig(cacheNames = {"weather"})
public class WeatherConsumer {
    private static final Logger log = LoggerFactory.getLogger(Location.class);

    private RestTemplate restTemplate = new RestTemplateBuilder().build();

    @Cacheable("weather")
    public Weather getWeather(final String key, Location loc) throws RestClientException {
        String query = String.format("https://api.darksky.net/forecast/%s/%s,%s?units=si",
                key,
                String.format(Locale.ROOT, "%10.8f", loc.getLatitude()).trim(),
                String.format(Locale.ROOT, "%11.8f", loc.getLongitude()).trim());

        log.info(query);

        Weather w = restTemplate.getForObject(query, Weather.class);
        log.info(String.format("Weather: %s", w.toString()));
        return w;
    }
}
