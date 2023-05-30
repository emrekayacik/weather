package com.emrekayacik.weather.base;

import com.emrekayacik.weather.base.response.RestResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

public class BaseTest {

    protected ObjectMapper objectMapper;

    protected boolean isSuccess(MvcResult mvcResult) throws JsonProcessingException, UnsupportedEncodingException {
        var restResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestResponse.class);

        boolean success = restResponse.isSuccess();
        return success;
    }
}