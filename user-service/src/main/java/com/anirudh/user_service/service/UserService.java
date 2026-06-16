package com.anirudh.user_service.service;

import com.anirudh.event.UserCreatedEvent;
import com.anirudh.user_service.dto.CreateUserRequestDTO;
import com.anirudh.user_service.entity.User;
import com.anirudh.user_service.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final KafkaTemplate<Long, UserCreatedEvent> kafkaTemplate;

    @Value("${kafka.topic.user-created-topic}")
    private String KAFKA_USER_CREATED_TOPIC;

    public void createUser(CreateUserRequestDTO createUserRequestDTO){
        User user = User.builder()
                .name(createUserRequestDTO.getName())
                .email(createUserRequestDTO.getEmail())
                .build();

        User savedUser = userRepository.save(user);
        UserCreatedEvent userCreatedEvent = modelMapper.map(savedUser, UserCreatedEvent.class);

        kafkaTemplate.send(KAFKA_USER_CREATED_TOPIC, userCreatedEvent.getId(), userCreatedEvent);
    }

}
