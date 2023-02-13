package com.example.springdatajpa.repository;

import com.example.springdatajpa.domain.Board;
import com.example.springdatajpa.dto.BoardIf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    
    // JPQL 사용할 수 있다.
    // SQL 과 모양이 비슷하지만, SQL이 아니다
    // JPQL은 객체지향 언어이다
    // JPQL 에서 from 뒤에 있는 것은 Entity 이름이다
    // Board 엔티티들을 조회하라. 엔티티는 결국 table과 관계

    @Query(value = "select b from Board b join fetch b.user")
    List<Board> getBoards();

    @Query(value = "select count(b) from Board b")
    Long getBoardCnt();

    // 관리자가 쓴 글의 목록만을 구하는 JPQL을 작성하시오. 가능? 불가능?
    // 쿼리 상에서는 select * from board b, user u, user_role ur, role r where b.user_id = u.user_id
    // and u.user_id = ur.user_id and ur.role_id = r.role_id and r.name = "role_admin"
    @Query("select b, u from Board b inner join fetch b.user u inner join u.roles r where r.name = :rolename")
//    @Query("select b from Board b inner join fetch b.user u inner join u.roles r where r.name = :rolename")
    List<Board> getAdminds(@Param("rolename") String rolename);
    
    // 네이티브 쿼리를 쓸 때는 getter 메소드만을 갖고 있는 인터페이스를 생성하여 사용해 준다
    @Query(value = "select b.board_id, b.title, b.content, b.user_id, u.name, b.regdate, b.view_cnt from board b, user u where b.user_id = u.user_id",
        nativeQuery = true
    )
    List<BoardIf> getBoardsWithNativeQuery();
}
