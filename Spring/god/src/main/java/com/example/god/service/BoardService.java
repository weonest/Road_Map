package com.example.god.service;

import com.example.god.domain.Board;
import com.example.god.domain.User;
import com.example.god.dto.BoardRequestDto;
import com.example.god.repository.BoardRepository;
import com.example.god.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public Long save(String username, BoardRequestDto board) {

        User user = userRepository.findByUsername(username);
        Board entity = boardRepository.save(board.toEntity());
        return entity.getId();
//        Board entity = boardRepository.save(board.toEntity());

//        board.setUser(user);
//        return boardRepository.save(board);
    }
}
