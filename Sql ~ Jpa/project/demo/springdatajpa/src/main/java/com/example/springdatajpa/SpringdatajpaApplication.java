package com.example.springdatajpa;

import com.example.springdatajpa.domain.Board;
import com.example.springdatajpa.domain.Role;
import com.example.springdatajpa.domain.User;
import com.example.springdatajpa.repository.BoardRepository;
import com.example.springdatajpa.repository.RoleRepository;
import com.example.springdatajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory; // JPA api
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class SpringdatajpaApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(SpringdatajpaApplication.class, args);
    }

    @Autowired // 하나의 애노테이션에 하나의 코드만 작성인 것 같음
    UserRepository userRepository; // 자동으로 Bean을 주입

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BoardRepository boardRepository;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        // 1 + N 문제
          // 여러 건을 조회할 때는 join이 일어나지 않는다?

//        List<Board> all = boardRepository.getBoards(); // select * from board
//        for (Board board : all) {
//            System.out.println(board);
////            System.out.println(board.getUser());
//        }
//         fetch 타입이 EAGER면 board와 user를 join하여 한 번에 가져옴
//         LAZY면 필요시에 새로운 sql을 실행하여 가져옴

//        Board board = boardRepository.findById(3).get(); // Optional이기에 .get()
//        System.out.println(board);
//        System.out.println(board.getUser());

//        Long boardCnt = boardRepository.getBoardCnt();
//        System.out.println(boardCnt);
// -----------------------------------------------------------
//        Role role = roleRepository.findById(2).get();
//        System.out.println(role);
//
//        User user = new User();
//        user.setName("관리자");
//        user.setPassword("1234");
//        user.setEmail("admin@naver.com");
//        // 권한 설정은 위의 roleRepository를 통해 읽어드린 값을 넣어줘야 한다
//        user.setRoles(Set.of(role));
//
//        userRepository.save(user);
        // --------------------------------

        User user = userRepository.findById(9).get();
        Board board = new Board();
        board.setUser(user);
        board.setTitle("관리자님의 글");
        board.setContent("관리자의 테스트");
        boardRepository.save(board);
    }
}
