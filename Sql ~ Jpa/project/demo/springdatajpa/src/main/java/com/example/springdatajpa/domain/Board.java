package com.example.springdatajpa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // Database 테이블과 맵핑하는 객체. 별다른 설정이 없다면 클래스명과 맵핑
@Table(name = "board") // Database 테이블 이름 user3 와 User라는 객체가 맵핑
@NoArgsConstructor // 기본 생성자가 필요하다. -> 자동 생성?
@Data
//@ToString // 엔티티 관계를 표현할때는 되도록 사용하지 않는게 좋다. 나중에 재귀적인 문제가 생길 수 있따.
public class Board {
    @Id // 이 필드가 Table의 PK
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // userId를 자동으로 생성 및 증가
    private Integer boardId;

    @Column(length = 100)
    private String title;

    @Lob // 대용량 텍스트 애노테이션
    private String content;

    private int viewCnt;

    @CreationTimestamp
    private LocalDateTime regdate;

    @ManyToOne(fetch = FetchType.LAZY)// 게시물 N --- 사용자 1
    // fetch의 기본값은 EAGER = 무조건 데이터를 가져와라
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Board{" +
                "boardId=" + boardId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", viewCnt=" + viewCnt +
                ", regdate=" + regdate +
                '}';
    }

    /*

'board_id', 'int', 'NO', '', '0', ''
'title', 'varchar(100)', 'NO', '', NULL, ''
'content', 'text', 'YES', '', NULL, ''
'user_id', 'int', 'NO', '', NULL, ''
'regdate', 'timestamp', 'YES', '', 'CURRENT_TIMESTAMP', 'DEFAULT_GENERATED'
'view_cnt', 'int', 'YES', '', '0', ''

     */
}
