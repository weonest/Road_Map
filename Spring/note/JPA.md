# JPA

- JPA는 기존의 반복 코드는 물론이고, 기본적인 SQL도 JPA가 직접 만들어서 실행해준다.
- JPA를 사용하면, SQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임을 전환을 할 수 있다.
- JPA를 사용하면 개발 생산성을 크게 높일 수 있다.
- JPA를 쓰려면 @`Transactional`이 항상 필요

```java
spring.jpa.hibernate.ddl-auto=none
// JPA가 객체를 보고 자동으로 테이블을 만들어 주는 기능을 끄는 것
// 우리는 이미 테이블이 있기 때문
@Entity //JPA를 쓰기위해 엔티티 설정
public class Member {

    // ID와 아이덴티티 전략으로 밸류 생성
    // 아이덴티티 : DB가 알아서 자동으로 생성해주는 것
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository
{
    //JPA는 엔티티매니저로 모든 것이 동작함
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.of(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
				List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();

		// List<Member> result = em.createQuery("select m from Member m", Member.class)
    //           .getResultList();
		//.   return result;
		//.   여기서 result 변수에 Inline Variable 로 위처럼 만들기 (단축키 커맨트 + 옵션 + N)
    }
}
@Transactional // JPA를 쓰기 때문 : 모든 DATA 변경에 필요하다
public class MemberService {
			...
}
```

ㅇ