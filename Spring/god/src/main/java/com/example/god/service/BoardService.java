package com.example.god.service;

import com.example.god.domain.Board;
import com.example.god.domain.User;
import com.example.god.repository.BoardRepository;
import com.example.god.repository.UserRepository;
import com.example.god.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardValidator boardValidator;

    public Board save(String username, Board board) {

        User user = userRepository.findByUsername(username);
        board.setUser(user);
        return boardRepository.save(board);
    }
}
