package com.example.scheduledevelop.schedule.Entity;

import com.example.scheduledevelop.Common.Entity.BaseEntity;
import com.example.scheduledevelop.user.entity.User;
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
    @ManyToOne //  단방향 설정
    @JoinColumn(name = "user_id") // FK 설정: students 테이블에 coures_id 라는 컬럼을 만들고 이 컬럼을 coures 테이블의 id 와 연결한다.
    private User user; // 단방향 관계

    public Schedule(String title, String content, String authorName, String password,User user) {
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.password = password;
        this.user = user;
    }


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}