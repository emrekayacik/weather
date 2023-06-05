package com.emrekayacik.weather.controller;

import com.emrekayacik.weather.WeatherApplication;
import com.emrekayacik.weather.base.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {WeatherApplication.class})
public class ClientControllerTest extends BaseTest {
    private static final String BASE_PATH = "/weathers";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }


    @Test
    void should_get_city_forecasts_by_cityName_and_return_success() throws Exception {
        // Assert
        String city = "istanbul";

        // Act
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get(BASE_PATH + "/forecasts/city?city="+ city )
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Arrange
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void should_get_city_forecasts_by_lat_lon_and_return_success() throws Exception {
        // Assert
        double lat = 34.5;
        double lon = 26.5;

        // Act
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get(BASE_PATH + "/forecasts/coordinates?lat="+ lat + "&lon=" +lon )
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Assert
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void should_get_city_current_weather_by_cityName_and_return_success() throws Exception {
        // Arrange
        String city = "istanbul";

        // Act
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get(BASE_PATH + "/current/city?city="+ city )
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Assert
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void should_get_city_current_weather_by_lat_lon_and_return_success() throws Exception {
        // Arrange
        double lat = 34.5;
        double lon = 26.5;

        // Act
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get(BASE_PATH + "/current/coordinates?lat="+ lat + "&lon=" +lon )
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


        // Assert
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

}
