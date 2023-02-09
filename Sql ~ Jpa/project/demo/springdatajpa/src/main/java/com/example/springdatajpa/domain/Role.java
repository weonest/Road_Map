package com.example.springdatajpa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // Database 테이블과 맵핑하는 객체. 별다른 설정이 없다면 클래스명과 맵핑
@Table(name = "role") // Database 테이블 이름 user3 와 User라는 객체가 맵핑
@NoArgsConstructor // 기본 생성자가 필요하다. -> 자동 생성?
@Data
//@ToString // 엔티티 관계를 표현할때는 되도록 사용하지 않는게 좋다. 나중에 재귀적인 문제가 생길 수 있따.
public class Role {
    @Id // 이 필드가 Table의 PK
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // userId를 자동으로 생성 및 증가
    private Integer roleId;

    @Column(length = 20)
    private String name;

    @Override                    // 되도록이면 애노테이션 ToString이 아닌 직접 생성을 추천
    public String toString() { 
        return "Role{" +
                "roleId=" + roleId +
                ", name='" + name + '\'' +
                '}';
    }
}
