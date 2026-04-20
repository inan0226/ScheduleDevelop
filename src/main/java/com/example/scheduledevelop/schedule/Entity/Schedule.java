package com.example.scheduledevelop.schedule.Entity;

import com.example.scheduledevelop.Common.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "Schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String authorName;
    private String password;

    public Schedule(String title, String content, String authorName, String password) {
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.password = password;
    }


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}