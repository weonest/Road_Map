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

@SpringBootApplication
public class SpringdatajpaApplication implements CommandLineRunner {



    public static void main(String[] args) {
        SpringApplication.run(SpringdatajpaApplication.class, args);
    }

    @Autowired
    UserRepository userRepository; // 자동으로 Bean을 주입


    @Override
    public void run(String... args) throws Exception {
        User user = userRepository.findById(2).orElseThrow();
        System.out.println(user);

    }
}
