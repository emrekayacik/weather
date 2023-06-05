package com.emrekayacik.weather.controller;

import com.emrekayacik.weather.WeatherApplication;
import com.emrekayacik.weather.base.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {WeatherApplication.class})
public class AuthenticationControllerTest extends BaseTest {

    private static final String BASE_PATH = "/auth";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void should_register_and_return_success() throws Exception {
        // Arrange
        String body = """
                {
                  "username": "mockuser",
                  "password": "mockpassword",
                  "name": "mocky",
                  "surname": "mock",
                  "email": "mockymock@gmail.com",
                  "phoneNumber": "+461422145574"
                }""";

        // Act
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post(BASE_PATH + "/register" )
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Assert
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }
    @Test
    void should_register_and_return_jwt() throws Exception {
        // Arrange
        String body = """
                {
                  "username": "mockuserjwt",
                  "password": "mockpassword",
                  "name": "mocky",
                  "surname": "mock",
                  "email": "mockymockjwt@gmail.com",
                  "phoneNumber": "+146422145574"
                }""";

        // Act

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post(BASE_PATH + "/register" )
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Assert
        String jwt = getJwt(mvcResult);
        assertJwtValid(jwt);
    }
    @Test
    void should_authenticate_and_return_success() throws Exception {
        // Arrange
        String body = """
                {
                  "username": "mockuserjwt",
                  "password": "mockpassword"
                }""";

        // Act
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post(BASE_PATH + "/authenticate" )
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Assert
        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void should_authenticate_and_return_jwt() throws Exception {
        // Arrange
        String body = """
                {
                  "username": "mockuser",
                  "password": "mockpassword"
                }""";

        // Act
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post(BASE_PATH + "/authenticate" )
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Assert
        String jwt = getJwt(mvcResult);
        assertJwtValid(jwt);
    }

    private String getJwt(MvcResult mvcResult) throws JsonProcessingException, UnsupportedEncodingException {
        return getResponseData(mvcResult).toString().substring(7, getResponseData(mvcResult).toString().length() - 1);
    }
    private static void assertJwtValid(String jwt) {
        assertNotEquals("", jwt);
        assertEquals("ey", jwt.substring(0,2));
        assertNotEquals(0, jwt.length());
    }

}
