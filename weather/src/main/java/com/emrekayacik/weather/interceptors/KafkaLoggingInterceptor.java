package com.emrekayacik.weather.interceptors;

import com.emrekayacik.weather.kafka.KafkaLogProducer;
import com.emrekayacik.weather.kafka.RequestProps;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class KafkaLoggingInterceptor implements HandlerInterceptor {

    private final KafkaLogProducer kafkaLogProducer;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        RequestProps requestProps = new RequestProps(
                request.getRequestURI(),
                request.getMethod(),
                request.getProtocol(),
                request.getLocalAddr(),
                request.getParameterMap()
        );
        kafkaLogProducer.sendMessage(requestProps);
        return true;
    }
}