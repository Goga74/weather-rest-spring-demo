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

import java.net.URI;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationConsumerTests {
    private static final Logger log = LoggerFactory.getLogger(WeatherConsumerTests.class);

    private static final double lat = 37.751;
    private static final double lon = -97.822;
    private static final String IP = "8.8.8.8";

    @Autowired
    private LocationConsumer locationService;

    private RestTemplate restTemplate = new RestTemplateBuilder().build();

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testLocationService() {
        Location l = new Location();
        l.setIp(IP);
        l.setLatitude(lat);
        l.setLongitude(lon);
        l.setCountry("United States");

        log.info(l.toString());

        String query = String.format(String.format("https://www.iplocate.io/api/lookup/%s", IP));
        try {
            mockServer.expect(ExpectedCount.once(),
                    requestTo(new URI(query)))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withStatus(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(mapper.writeValueAsString(l))
                    );

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        Location loc = locationService.getLocation(IP);

        log.info(loc.toString());

        Assert.assertEquals(l, loc);
    }
}