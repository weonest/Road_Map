package com.example.springdatajpa.repository;

import com.example.springdatajpa.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
