package com.example.god.service;

import com.example.god.domain.Board;
import com.example.god.domain.User;
import com.example.god.dto.BoardRequestDto;
import com.example.god.repository.BoardRepository;
import com.example.god.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    /**
     * 게시글 생성
     */
    @Transactional
    public Long save(BoardRequestDto param) {
        Board entity = boardRepository.save(param.toEntity());
        return entity.getId();
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public Long update(Long id, BoardRequestDto param) {
        Board entity = boardRepository.findById(id).orElse(null);
        entity.update(param.getTitle(), param.getContent());
        return id;
    }
}
