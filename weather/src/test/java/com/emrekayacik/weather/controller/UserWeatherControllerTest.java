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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {WeatherApplication.class})
public class UserWeatherControllerTest extends BaseTest {
    private static final String BASE_PATH = "/userWeathers";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void should_get_all_and_return_success() throws Exception {
        // Act
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get(BASE_PATH )
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Assert
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }
    @Test
    void should_get_by_id_and_return_success() throws Exception {
        // Arrange
        long id = 1003L;

        // Act
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get(BASE_PATH + "/" + id )
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Assert
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }
    @Test
    void should_delete_and_return_success() throws Exception {
        // Arrange
        long id = 1001L;

        // Act
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.delete(BASE_PATH + "/" + id)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Assert
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void should_get_by_username_and_return_success() throws Exception {
        // Arrange
        String username = "emrekayacik";

        // Act
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get(BASE_PATH + "/user?username=" +username)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Assert
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }
    @Test
    void should_get_by_cityName_and_return_success() throws Exception {
        // Arrange
        String cityName = "paris";

        // Act
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get(BASE_PATH + "/city?cityName=" +cityName)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Assert
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }
    @Test
    void should_save_userWeather_and_return_success() throws Exception {

        // Arrange
        String body = """
                {
                  "username": "emrekayacik",
                  "city": "beijing"
                }""";

        // Act
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post(BASE_PATH)
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Assert
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

}
