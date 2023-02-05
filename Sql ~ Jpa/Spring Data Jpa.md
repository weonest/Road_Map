# Spring Data Jpa

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/dde863bd-e9dd-4be6-9536-3ea220802cd5/Untitled.png)

- Spring JDBC 프로그래밍
  1. DBMS 접속
  2. SQL 작성
  3. SQL 실행, DBMS 안에서 실행.
  4. ResultSet으로 한 건씩 읽어와서 처리

**Hibernate (SQL을 몰라도 쓸 수 있게끔?)**

- EJB에도 Entity Bean이라는 게 있었는데 너무 복잡하고 힘들어서 Hibernate가 등장 (ORM 기술) → JPA(ORM 표준 API, 인터페이스) 2006 → JPA2.1(2013) → Spriong Data JPA
- JPA를 구현하고 있는 것이 Hibernate같은 기술
- Spring은 JPA와 관련된 객체를 손쉽게 Bean으로 생성.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2b7c435b-14a0-4b5d-abff-ebaf690447ec/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a4dbb681-9086-4ff0-9358-416aa8da307b/Untitled.png)

- JPA에서 가장 중요한 객체?
  - `EntityManager ← EntityManagerFactory` 이 매니저 팩토리를 Bean으로 만들어 줌

### 요약

- Spring Data JPA는 JPA를 사용하고, JPA는 EntityManager를 사용한다

### EntityManger의 작동 원리

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d79ab33e-ed90-4b3c-82e0-c9c02d4dc65b/Untitled.png)

- user와 user2의 객체가 같은 객체이면 select문이 한 번만 실행되었다

  - EntityManager 내부에서 `PersitenceContext`

  ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/98273af9-b6fe-4743-ab4e-6c4d5e6f2d77/Untitled.png)

  - EntityManager → Persistence Context → DBMS
    - `Persistence Context`는 EntityManger가 보낸 요청을 DBMS로부터 받아 자기가 지니고 있는다. 이러한 이유로 user와 user2가 같은 객체라는 것
  - `find`를 제외한 `CUD`는 반드시 Transaction 안에서 수행되어야 한다

- 위 상황에서 `user.setPassword`를 통해 비밀번호를 변경시키면 다음과 같이 진행된다 (같은 트랜잭션 내)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d1815892-1d3f-48ad-89d6-a110557ade7c/Untitled.png)

- `Persistence Context` 가 자신의 내부의 user객체가 변경되었음을 DBMS에 알리고, 자동으로 user 객체의 update가 진행된다
- 즉, 트랜잭션이 끝났을 때 (커밋이 종료되었을 때) 변경사항이 있으면 자동으로 업데이트가 일어난다는 것