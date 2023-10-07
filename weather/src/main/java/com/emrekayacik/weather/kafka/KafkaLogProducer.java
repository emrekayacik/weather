package com.emrekayacik.weather.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaLogProducer {
    private final KafkaTemplate<String, RequestProps> kafkaTemplate;
    @Value("${weather.kafka.logger.topic}")
    private String TOPIC_NAME;

    public KafkaLogProducer(KafkaTemplate<String, RequestProps> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(RequestProps logData) {
        kafkaTemplate.send(TOPIC_NAME, logData);
    }
}
