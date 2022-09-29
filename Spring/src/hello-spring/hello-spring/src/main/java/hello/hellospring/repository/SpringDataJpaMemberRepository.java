package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 스프링 데이터 JPA가 이걸 보고 알아서 구현체를 만들어서 등록해준다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL select m from Member m where m.name = ?
    // 인터페이스 변수명에 맞춰서 자동으로 만들어 준다.
    @Override
    Optional<Member> findByName(String name);
}
