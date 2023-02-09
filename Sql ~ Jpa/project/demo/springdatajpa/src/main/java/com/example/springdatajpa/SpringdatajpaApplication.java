package com.example.springdatajpa;

import com.example.springdatajpa.domain.Role;
import com.example.springdatajpa.domain.User;
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

@SpringBootApplication
public class SpringdatajpaApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(SpringdatajpaApplication.class, args);
    }

    @Autowired // 하나의 애노테이션에 하나의 코드만 작성인 것 같음
    UserRepository userRepository; // 자동으로 Bean을 주입

    @Autowired
    RoleRepository roleRepository;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<User> all = userRepository.findAll(); // select * from user;
        for (User user : all) {
            System.out.println(user);
//            for (Role role : user.getRoles()) { // select from user_role, role where user_id = ?
//                System.out.println(role);
//            }
//            System.out.println("-------------------------");
        }
    }
}
