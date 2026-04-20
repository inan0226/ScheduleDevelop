package com.example.scheduledevelop.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserCreateResponse {
    private final Long id;
    private final String userName;
    private final String email;
    private final LocalDateTime createAt;
    private final LocalDateTime modifyAt;

    public UserCreateResponse(Long id, String userName, String email, LocalDateTime createAt, LocalDateTime modifyAt) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.createAt = createAt;
        this.modifyAt = modifyAt;
    }
}
