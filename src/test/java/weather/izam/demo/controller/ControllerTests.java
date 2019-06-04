package weather.izam.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void restControllerShouldReturn404() throws Exception {
        this.mockMvc.perform(get("/wrongPath"))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void restControllerWithWrongParam() throws Exception {
        this.mockMvc.perform(get("/weather").param("ip", "null"))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void restControllerShouldReturnTemperature() throws Exception {
        this.mockMvc.perform(get("/weather").param("ip", "127.0.0.1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("temperature")));
    }

    @Test
    public void restControllerWithWrongIP() throws Exception {
        this.mockMvc.perform(get("/weather").param("ip", "wrongIp"))
                .andDo(print()).andExpect(status().isBadRequest());
    }

}
