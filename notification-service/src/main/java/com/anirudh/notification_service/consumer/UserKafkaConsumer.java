package com.anirudh.notification_service.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserKafkaConsumer {

    @KafkaListener(topics = "user-random-topic")
    public void handleUserRandomTopic1(String message) {
        log.info("MESSAGE RECEIVED :: {}", message);
    }

    @KafkaListener(topics = "user-random-topic")
    public void handleUserRandomTopic2(String message) {
        log.info("MESSAGE RECEIVED :: {}", message);
    }

    @KafkaListener(topics = "user-random-topic")
    public void handleUserRandomTopic3(String message) {
        log.info("MESSAGE RECEIVED :: {}", message);
    }
}
