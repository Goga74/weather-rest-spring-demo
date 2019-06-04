package weather.izam.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import weather.izam.demo.model.Location;
import weather.izam.demo.model.Weather;

import java.net.URI;
import java.util.Locale;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherConsumerTests {
    private static final Logger log = LoggerFactory.getLogger(WeatherConsumerTests.class);
    // key should to renew in free account of service https://api.darksky.net every 30 days
    private static final String weatherKey = "bea3233e802b6839cd4aedaeb2e4f778";
    private static final double lat = 0.0;
    private static final double lon = 0.0;

    @Autowired
    private WeatherConsumer weatherService;

    private RestTemplate restTemplate = new RestTemplateBuilder().build();

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testWeatherService() {
        Location loc = new Location(lat, lon);
        Weather w = new Weather();
        w.setLatitude(lat);
        w.setLongitude(lon);
        w.setCurrently(null);
        w.setTimezone("Etc/GMT");
        log.info(w.toString());

        String query = String.format("https://api.darksky.net/forecast/%s/%s,%s?units=si", weatherKey,
                String.format(Locale.ROOT, "%10.8f", lat).trim(),
                String.format(Locale.ROOT, "%11.8f", lon).trim());
        try {
            mockServer.expect(ExpectedCount.once(),
                    requestTo(new URI(query)))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withStatus(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(mapper.writeValueAsString(w))
                    );

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        Weather weather = weatherService.getWeather(weatherKey, loc);

        log.info(w.toString());
        log.info(weather.toString());

        Assert.assertEquals(new Location(w.getLatitude(), w.getLongitude()),
                new Location(weather.getLatitude(), weather.getLongitude()));
        //Assert.assertEquals(w.getLatitude(), weather.getLatitude(), 0);
        //Assert.assertEquals(w.getLongitude(), weather.getLongitude(), 0);
    }
}