package com.example.springdatajpa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity // Database 테이블과 맵핑하는 객체. 별다른 설정이 없다면 클래스명과 맵핑
@Table(name = "user") // Database 테이블 이름 user3 와 User라는 객체가 맵핑
@NoArgsConstructor // 기본 생성자가 필요하다. -> 자동 생성?
@Data

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
    
    @ManyToMany // 다대다 관계
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =@JoinColumn(name = "role_id")
    )
    Set<Role> roles = new HashSet<>();
    // User 에서는 Role 가지는데, Role에서는 User를 갖지 못하도록 단방향 다대다 관계 맵핑

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", regdate=" + regdate +
                '}';
    }
}
