package com.example.springdatajpa;

import com.example.springdatajpa.domain.User;
import com.example.springdatajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory; // JPA api
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

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
//        User user = userRepository.findById(2).orElseThrow();

//        User user = new User();
//        user.setName("test2");
//        user.setPassword("Test2");
//        user.setEmail("test@naver.com");
//        userRepository.save(user);

//        저장과 동시에 user의 id값이 궁금한 경우
//        User saveUser = userRepository.save(userRepository);
//        System.out.println(saveUser);

//        userRepository.deleteById(1);
//        or
//        User user = userRepository.findById(2).orElseThrow();
//        userRepository.delete(user);

        User user = userRepository.findById(4).orElseThrow();
        System.out.println(user);

    }
}
