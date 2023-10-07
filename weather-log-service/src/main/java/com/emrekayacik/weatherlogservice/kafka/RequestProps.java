package com.emrekayacik.weatherlogservice.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestProps {
    private String requestURI;
    private String requestMethod;
    private String requestProtocol;
    private String requestUserIP;
    private Map<String,String[]> requestParameters;

}
