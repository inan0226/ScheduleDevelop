package com.example.scheduledevelop.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleSaveRequest {

    private String title;
    private String content;
    private String authorName;
    private String password;
}
