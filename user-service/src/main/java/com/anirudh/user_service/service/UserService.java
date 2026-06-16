package com.anirudh.user_service.service;

import com.anirudh.user_service.dto.CreateUserRequestDTO;
import com.anirudh.user_service.entity.User;
import com.anirudh.user_service.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(CreateUserRequestDTO createUserRequestDTO){
        User user = User.builder()
                .name(createUserRequestDTO.getName())
                .email(createUserRequestDTO.getEmail())
                .build();

        userRepository.save(user);
    }

}
