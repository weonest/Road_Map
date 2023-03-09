package com.example.god.contorller;


import com.example.god.dto.BoardRequestDto;
import com.example.god.repository.BoardRepository;
import com.example.god.service.BoardService;
import com.example.god.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class  BoardApiController {

    @Autowired
    private BoardService boardService;


    /**
     * 게시글 수정
     */
    @PatchMapping("/boards/{id}")
    public Long update (@PathVariable Long id, @RequestBody BoardRequestDto param) {

        return boardService.update(id, param);
    }
//

    /**
     * 게시글 삭제
     */
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @DeleteMapping("/boards/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.delete(id);
    }

}
