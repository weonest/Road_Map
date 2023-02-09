package com.example.springdatajpa;

import com.example.springdatajpa.domain.User;
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

@SpringBootApplication
public class SpringdatajpaApplication implements CommandLineRunner {



    public static void main(String[] args) {
        SpringApplication.run(SpringdatajpaApplication.class, args);
    }

    @Autowired
    UserRepository userRepository; // 자동으로 Bean을 주입


    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        Optional<User> user = userRepository.findByNameAndEmail("겅퀴", "geonhee34@naver.com");
        // Optional은 .get() 을 해서 가져와 준다
//        System.out.println(user.get);

//        List<User> users = userRepository.findByNameOrEmail("겅퀴", "test@naver.com");
//        for (User user : users) {
//            System.out.println(user);
//        }
//        List<User> byUserIdBetween = userRepository.findByUserIdBetween(2, 5);
//        for (User user : byUserIdBetween) {
//            System.out.println(user);
//        }
//        List<User> users = userRepository.findByUserIdLessThan(5);
//        for( User user : users)
//            System.out.println(user);
//        List<User> users = userRepository.findByRegdateBefore(LocalDateTime.now().minusDays(1L));
//        for (User user : users) {
//            System.out.println(user);
//        }

//        List<User> users = userRepository.findByNameLike("백승_");
//        for (User user : users) {
//            System.out.println(user);
//        }

//        List<User> users = userRepository.findByNameStartingWith("겅");
//        for (User user : users) {
//            System.out.println(user);
//        }

//        List<User> users = userRepository.findByNameEndingWith("퀴");
//        for (User user : users) {
//            System.out.println(user);
//        }

//        List<User> users = userRepository.findByOrderByNameAsc();
//        for (User user : users) {
//            System.out.println(user);
//        }

//        List<User> users = userRepository.findByRegdateAfterOrderByNameDesc(LocalDateTime.now().minusDays(1L));
//        for (User user : users) {
//            System.out.println(user);
//        }

//        List<User> users = userRepository.findByNameNot("겅퀴");
//        for (User user : users) {
//            System.out.println(user);
//        }
        
        // List는 Collection의 자식이기 때문에 파라미터로 들어갈 수 있다. 상속, 인터페이스 관계
        // List.of 는 파라미터 내부의 값을 List형태로 반환
//        List<User> users = userRepository.findByUserIdIn(List.of(2, 3));
//        for (User user : users) {
//            System.out.println(user);
//        }
        
        /**
        * 여기 왜 계속 오류가 나냐..
         **/
        Page<User> users = userRepository.findBy(PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "redgdate")));
        for (User user : users.getContent()) {
            System.out.println(user);
        }


    }
}
