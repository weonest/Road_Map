package com.example.god.contorller;

import com.example.god.domain.Board;
import com.example.god.repository.BoardRepository;
import com.example.god.service.BoardService;
import com.example.god.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 10) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String keyword) {
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(keyword, keyword, pageable);

        int startPage = 1;
        int endPage = boards.getTotalPages();

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable final Long id) {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        return "board/view";
    }

    @GetMapping("/write")
    public String write(Model model) {
            model.addAttribute("board", new Board()); //객체 전달 시 Null임을 막기 위해 new 로 생성
        return "board/write";
    }

    @PostMapping("/write")
    public String write(@Valid Board board, BindingResult bindingResult, Authentication authentication) {
        boardValidator.validate(board, bindingResult);

        if (bindingResult.hasErrors()) {
            return "board/write";
        }

        String username = authentication.getName();
        boardService.save(username, board);
        return "redirect:/board/list"; // 다시 조회를 일으키며 불러온다
    }

}
