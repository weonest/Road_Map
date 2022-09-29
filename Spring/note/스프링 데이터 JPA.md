# 스프링 데이터 JPA

- 스프링 부트와 JPA만 사용해도 개발 생산성이 정말 많이 증가하고, 개발해야 할 코드도 확연히 줄어든다. 여기에 스프링 데이터 JPA 를 사용하면, 기존의 한계를 넘어 마치 마법처럼, 레포지토리에 구현 클래스 없이 인터페이스 만으로 개발을 완료할 수 있다. 그리고 반복 개발해온 기본 CRUD 기능도 스프링 데이터 JPA가 모두 제공한다.
- 스프링 부트와 JPA라는 기반 위에, 스프링 데이터 JPA라는 환상적인 프레임워크를 더하면 개발이 정말 즐거워진다. 지금까지 조금이라도 단순하고 반복이라 생각했던 개발 코드들이 확연하게 줄어든다.

> 주의 : 스프링 데이터 JPA는 JPA를 편리하게 사용하도록 도와주는 기술이다. 따라서 JPA를 먼저 학습한 후에 스프링 데이터 JPA를 학습해야 한다.

```java
package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,
Long>, MemberRepository {
 Optional<Member> findByName(String name);
}
package hello.hellospring;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SpringConfig {
 private final MemberRepository memberRepository;
 public SpringConfig(MemberRepository memberRepository) {
 this.memberRepository = memberRepository;
 }
 @Bean
 public MemberService memberService() {
 return new MemberService(memberRepository);
 }
}
```

- 스프링 데이터 JPA가 기본적으로 공통화 된 작업들을 갖고 있으므로 그냥 가져다 쓰기만 하면 된다.
- findByName 같은 경우는 사용처마다 달라 공통화 할 수 없으므로 따로 생성해준다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9c642bff-acc5-472b-892c-3ae1cfc97e13/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/aeb74820-b47b-4140-95e6-0eae2be249da/Untitled.png)

- 자세한 내용은 다음 강의를 참고하자: 인프런 - 실전! 스프링 데이터 JPA