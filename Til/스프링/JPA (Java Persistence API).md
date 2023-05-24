# JPA (Java Persistence API)

------

## JPA란?

자바 진영의 ORM 표준 기술이다.

> ORM이란? Object - Relational Mapping 로, 객체랑 관계형 DB를 매핑해주는 프레임워크이다

**JPA는 라이브러리가 아닌 ORM을 사용하기 위한 인터페이스의 모음이다.**

이러한 JPA는 단순한 명세이기 때문에 구현이 없다. 자바 애플리케이션에서 관계형 데이터베이스를 어떻게 사용할지 정의하는 하나의 방법일 뿐이다.

따라서 이러한 JPA의 구현체가 있어야 사용할 수 있다.

### 왜 사용하는가?

- Insert SQL을 작성하고 JDBC API를 사용하는 등의 반복적인 일을 JPA가 대신 처리해준다.
- CREATE TABLE 같은 DDL 문을 자동 생성해준다.
- 데이터베이스 설계 중심의 패러다임을 객체 설계 중심으로 역전
- 패러다임 불일치 해결
- 데이터베이스 기술에 종속되지 않도록 한다. (만약 이러한 기술이 없이 DB의 API만을 이용하여 코드를 작성한다면 DB 변경시 이러한 API를 통해 작성된 코드도 모두 수정해야 한다)

### Hibernate

Hibernate는 JPA를 구현한 구현체이다.

JPA의 핵심들인 EntityManagerFactory, EntityManager, EntityTransaction 등을 상속받아 구현한다.

### Spring Data JPA

JPA를 한 단계 더 추상화시킨 Repository 인터페이스를 제공한다.

## 요약

JPA는 자바 진영의 ORM 기술에 대한 API 표준 명세이며

Hibernate는 JPA의 구현체이고, 내부적으로 JDBC를 이용한다.

Spring Data JPA는 JPA를 사용하기 쉽게 스프링에서 제공하는 모듈로 내부적으로 JPA 구현체를 이용한다.