package com.anirudh.user_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Value("${kafka.topic.user-random-topic}")
    private String KAFKA_RANDOM_USER_TOPIC;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping
    public ResponseEntity<String> getHealth(){
        String message = "OK";
        return ResponseEntity.ok(message);
    }

    @PostMapping("/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message){
        kafkaTemplate.send(KAFKA_RANDOM_USER_TOPIC, message);
        return ResponseEntity.ok("Message Queued :" + message);
    }
}
