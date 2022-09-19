# 라이브러리 살펴보기

- 스프링부트는 여러 라이브러리들의 버전을 자동으로 관리해준다.
- 라이브러리들이 내장(embed) 소스들을 갖고 있다. (상호의존관계)
- 현직에서는 log를 남겨 모아두는 것이 중요하다. (출력문으로 일일이 다 확인하는 건 비효율) == slf4j, logback 라이브러리

**“스프링 부트 라이브러리”**

- spring-boot-start-web

  - spring-boot-starter-tomcat: 톰캣(웹서버)
  - spring-webmvc: 스프링 웹 MVC

- spring-boot-starter-thymeleaf: 타임리프 템플릿 엔진(View)

- spring-boot-starter(공통): 스프링 부트 + 스프링 코어 + 로깅

  - spring-boot
    - spring-core
  - spring-boot-starter-logging
    - logback, slf4j

  **”테스트 라이브러리”**

- spring-boot-starter-test

  - junit: 테스트 프레임워크
  - mockito: 목 라이브러리
  - assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
  - spring-test: 스프링 통합 테스트 지원