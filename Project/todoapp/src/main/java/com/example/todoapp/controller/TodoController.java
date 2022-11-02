package com.example.todoapp.controller;

import com.example.todoapp.domain.ToDo;
import com.example.todoapp.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor // 레포지를 자동으로 넣어줌
public class TodoController {
    private final ToDoRepository toDoRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<ToDo> todos = toDoRepository.findAll();
        model.addAttribute("todos", todos);
        return "todos";
    }

    @RequestMapping(value="/addTodo", method = {RequestMethod.POST, RequestMethod.GET})
    public String addTodo(@RequestParam("todo") String todo) {
        ToDo toDo = new ToDo();
        toDo.setTodo(todo);
        toDoRepository.save(toDo);
        return "redirect:/";
    }
}
