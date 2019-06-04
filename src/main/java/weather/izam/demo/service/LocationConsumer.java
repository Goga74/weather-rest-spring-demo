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

@Component
@Service
@CacheConfig(cacheNames={"location"})
public class LocationConsumer {
    private static final Logger log = LoggerFactory.getLogger(Location.class);
    private RestTemplate restTemplate = new RestTemplateBuilder().build();

    @Cacheable("location")
    public Location getLocation(String ip) throws RestClientException {
        Location loc = restTemplate.getForObject(String.format("https://www.iplocate.io/api/lookup/%s", ip),
                Location.class);
        log.info(loc.toString());
        return loc;
    }
}
