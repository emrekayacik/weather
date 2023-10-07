package com.emrekayacik.weatherlogservice.kafka;

import com.google.gson.Gson;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(topics = "logger-topic",
    groupId = "log-group")
    void listener(RequestProps data ){
        Gson gson = new Gson();
        String json = gson.toJson(data);
        System.out.println("Kafka Listener Received: " + json);
    }
}
