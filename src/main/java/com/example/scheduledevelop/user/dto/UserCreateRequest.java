package com.example.scheduledevelop.user.dto;

import lombok.Getter;

@Getter
public class UserCreateRequest {
    private String userName;
    private String email;
    private String password;
}
