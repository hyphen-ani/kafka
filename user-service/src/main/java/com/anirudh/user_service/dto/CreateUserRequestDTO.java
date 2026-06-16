package com.anirudh.user_service.dto;

import lombok.Data;

@Data
public class CreateUserRequestDTO {
    private String name;
    private String email;
}
