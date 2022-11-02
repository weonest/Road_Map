package com.example.todoapp.repository;

import com.example.todoapp.domain.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// domain Todo에서 갖고 있는 id의 타입을 두 번째 파라미터로 넣으면 된다
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
