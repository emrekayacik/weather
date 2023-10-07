package com.emrekayacik.weatherlogservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {
    @Value("${weather.kafka.logger.address}")
    private String kafkaAddress;

    @Value("${weather.kafka.logger.group-id}")
    private String kafkaGroupId;
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RequestProps> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, RequestProps> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, RequestProps> consumerFactory() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaAddress);
        configMap.put(ConsumerConfig.GROUP_ID_CONFIG,kafkaGroupId);
        configMap.put(JsonDeserializer.VALUE_DEFAULT_TYPE,RequestProps.class);
        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(configMap);
    }

}
