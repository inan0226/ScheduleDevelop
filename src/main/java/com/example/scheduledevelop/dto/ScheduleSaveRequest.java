package com.example.scheduledevelop.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleSaveRequest {

    private String title;
    private String content;
    private String authorName;
    private String password;
}
