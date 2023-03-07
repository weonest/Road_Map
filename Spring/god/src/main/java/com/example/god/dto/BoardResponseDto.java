package com.example.god.dto;

import com.example.god.domain.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {

    private Long id; // PK
    private String title; // 제목
    private String content; // 내용
    private int hits; // 조회 수
    private String password;
    private LocalDateTime createdDate; // 수정일

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.hits = entity.getHits();
        this.password = entity.getPassword();
        this.createdDate = entity.getCreatedDate();
    }
}