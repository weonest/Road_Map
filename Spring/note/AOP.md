# AOP

## AOP가 필요한 상황

- 모든 메소드의 호출 시간을 측정하고 싶다면?
- 공통 관심 사항 (cross-cutting concern) vs 핵심 관심 사항 (core concern)
- 회원 가입 시간, 회원 조회 시간을 측정하고 싶다면?

```java
public Long join(Member member) {

        long start = System.currentTimeMillis();
        // 같은 이름이 있는 중복 회원X
        try {
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        } finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }

    }

public List<Member> findMembers() {
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }

    }
```

- 이런 방법으로도 찍을 수 있지만, 만약 1,000개의 메소드에 출력을 원한다면..?

```java
package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // 모든 곳에 적용하겠다
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try{
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("ENd: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
```

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0669f1f8-7ccb-4c3d-bb58-f7e5c762efec/Untitled.png)

ㅇ