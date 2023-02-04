package com.example.springdatajpa;

import com.example.springdatajpa.domain.User;
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
    EntityManagerFactory entityManagerFactory;

    @Override
    public void run(String... args) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

//            User user = new User();
//            user.setName("원건희");
//            user.setEmail("geonhee33@naver.com");
//            user.setPassword("1234");
//
//            entityManager.persist(user); // 영속성을 갖게 해달라 = 저장

            User user = entityManager.find(User.class, 1);
            System.out.println(user);
            
            
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        /**
         * 위의 코드가 가장 기본적인 JPA 코딩 방식
         */
    }
}
