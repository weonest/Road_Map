package com.example.god.controller;

import com.example.god.domain.User;
import com.example.god.dto.BoardRequestDto;
import com.example.god.dto.BoardResponseDto;
import com.example.god.repository.UserRepository;
import com.example.god.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/board")
public class BoardController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardService boardService;


    /**
     * 게시글 리스트
     */
    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 10) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String keyword) {
        Page<BoardResponseDto> boards = boardService.findAll(keyword, keyword, pageable);

        int startPage = 1;
        int endPage = boards.getTotalPages();

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "board/list";
    }

    /**
     * 상세 보기
     */
    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable final Long id) {
        BoardResponseDto board = boardService.findById(id);
        model.addAttribute("board", board);
        return "board/view";
    }

    /**
     * 글 작성 불러오기
     */
    @GetMapping("/write")
    public String write(Model model, BoardRequestDto param, Authentication authentication) {

        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("board", param);
        return "board/write";
    }

    /**
     * 글 작성
     */
    @PostMapping("/write")
    public String write(Model model, @ModelAttribute("board") @Valid BoardRequestDto board,
                        BindingResult bindingResult, Authentication authentication) {

        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);

        boardService.validate(board, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/board/write";
        }

        boardService.save(board);
        return "redirect:/board/list"; // 다시 조회를 일으키며 불러온다
    }

}
