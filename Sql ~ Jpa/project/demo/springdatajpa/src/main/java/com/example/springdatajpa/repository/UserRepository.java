package com.example.springdatajpa.repository;

import com.example.springdatajpa.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

// Spring Data JPA Repository 완성!
// 상속 후 제네릭을 받음 (받으려는 객체와, PK값의 자료형)
// 보통 인터페이스를 선언하면 인터페이스를 구현하는 클래스를 작성
// Spring Data JPA는 마법. 아래의 인터페이스를 구현하는 Bean을 자동으로 생성
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByName(String name);  // query method - spring data jpa

    Optional<User> findByNameAndEmail(String name, String email);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByUserIdBetween(int startUserId, int endUserId);

    List<User> findByUserIdLessThan(int userId);

    List<User> findByRegdateAfter(LocalDateTime day);

    List<User> findByRegdateBefore(LocalDateTime day);

    List<User> findByNameIsNull();

    List<User> findByNameIsNotNull();

    //select * from where name like "%건%";
    List<User> findByNameLike(String name);

    List<User> findByNameStartingWith(String name);

    List<User> findByNameEndingWith(String name);

    // where name like '%입력값%'
    List<User> findByNameContaining(String name);

    List<User> findByOrderByNameAsc();

    List<User> findByRegdateAfterOrderByNameDesc(LocalDateTime day);

    // where name <> ? / <>가 부정을 뜻함
    List<User> findByNameNot(String name);

    List<User> findByUserIdIn(Collection<Integer> userIds); // Collection = 자료 구조의 최상위 인터페이스

    List<User> findByUserIdNotIn(Collection<Integer> userIds);

    List<User> findByFlagTrue();
    
    List<User> findByFlagFalse();

    // select * from user3
    Long countBy();

    // select count(*) from user3 where name like = ?
    Long countByNameLike(String name);


    boolean existsByEmail(String name);

    // delete from user3 where name = ?
    // select 를 먼저 하고 delete를 진행
    int deleteByName(String name);

    // 중복 삭제
    // select distinct * from user3 where name = ?
    List<User> findDistinctByName(String name); // 그닥 의미는 없다? 모든 컬럼이 같아야 의미가 있음

    // select * from user3 limit 2
    List<User> findFirst2By();
    List<User> findTop2By();

//    페이징 처리
    Page<User> findBy(Pageable pageable);
    Page<User> findByName(String name, Pageable pageable);
    
    // Containing은 %를 붙여주지 않아도 like 구절을 사용
    // Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

}
