package com.example.god.contorller;


import com.example.god.domain.Board;
import com.example.god.dto.BoardRequestDto;
import com.example.god.repository.BoardRepository;
import com.example.god.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
public class  BoardApiController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;




    /**
     * 게시글 수정
     */
    @PatchMapping("/boards/{id}")
    public Long update (@RequestBody BoardRequestDto param, @PathVariable Long id, BindingResult bindingResult) {


        return boardService.update(id, param);
    }
//

    /**
     * 게시글 삭제
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @DeleteMapping("/boards/{id}")
    void deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
    }

}
