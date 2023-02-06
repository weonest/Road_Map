package com.example.springdatajpa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // Database 테이블과 맵핑하는 객체. 별다른 설정이 없다면 클래스명과 맵핑
@Table(name = "user3") // Database 테이블 이름 user3 와 User라는 객체가 맵핑
@NoArgsConstructor // 기본 생성자가 필요하다. -> 자동 생성?
@Data
@ToString
public class User {
    @Id // 이 필드가 Table의 PK
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // userId를 자동으로 생성 및 증가
    private Integer userId;

    @Column(length = 255)
    private String email;

    @Column(length = 50)
    private String name;

    @Column(length = 500)
    private String password;

    @CreationTimestamp
    private LocalDateTime regdate;
}
