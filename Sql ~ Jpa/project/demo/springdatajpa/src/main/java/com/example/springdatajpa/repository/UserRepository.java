package com.example.springdatajpa.repository;

import com.example.springdatajpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Spring Data JPA Repository 완성!
// 상속 후 제네릭을 받음 (받으려는 객체와, PK값의 자료형)
// 보통 인터페이스를 선언하면 인터페이스를 구현하는 클래스를 작성
// Spring Data JPA는 마법. 아래의 인터페이스를 구현하는 Bean을 자동으로 생성
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByName(String name);  // query method - spring data jpa
}
