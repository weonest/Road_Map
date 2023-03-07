package com.example.god.dto;


import com.example.god.domain.Board;
import com.example.god.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {

    private String title; // 제목
    private String content; // 내용
    private User user; // 작성자

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }

}