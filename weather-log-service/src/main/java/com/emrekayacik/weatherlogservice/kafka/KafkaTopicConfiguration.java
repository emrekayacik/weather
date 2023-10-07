package com.emrekayacik.weatherlogservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {
    @Value("${weather.kafka.logger.topic}")
    private String topic;
    @Bean
    public NewTopic logTopic(){
        return TopicBuilder
                .name(topic)
                .build();
    }
}
