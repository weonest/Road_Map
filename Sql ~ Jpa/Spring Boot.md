# Spring Boot (v2.7.5)

## Spring Boot 를 알기 전에 Spring Framework부터

- Spring Boot는 프레임워크를 보다 쉽게 사용하기 위해 나온 기술

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/faaed73c-3da0-4953-af03-92b5128e204e/Untitled.png)

- Auto Configuration 기능 - 최소한의 노력

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/efda0b72-73d9-4562-a567-39e204a4f801/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1a5cb0dc-93ae-4d50-b97a-e0961c02e01f/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9d0ce20b-d6e1-4606-ae28-df691703c030/Untitled.png)

- classpath 추가 방식은 잘 사용하지 않음
- `[start.spring.io](<http://start.spring.io>)` 자체도 오픈소스이기 때문에 회사전용으로 만들 수 있다. intellij 커뮤니티 버전에서도 start.spring.io를 통해 스프링 부트 사용 가능하다

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b2192131-14d8-4582-a116-d5d0d7cd3bba/Untitled.png)

- plugin들이 라이브러리들의 버전을 관리해준다

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0dc57c06-4589-474e-8765-1008d3462724/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2e1100a1-a717-459f-aadd-8c29ad5d05bb/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/60bb50c8-4f40-4fc7-8e4c-a72d596b6902/Untitled.png)

- Spring 에서는 CommandLineRunner가 구현하고 있는 곳을 main으로 보고 메소드를 실행 시킬 수 있다
- `@AutoWired`를 통해 Bean 주입

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9874b76f-71f2-4094-8a9d-a9c1fb287e41/Untitled.png)

- 미리 연결을 맺어놓고, 사용자가 커넥션 풀에게 커넥션을 빌려온다
- 사용 후 커넥션을 될려준다
  - 동시에 여러 개의 요청을 받아서 커넥션 풀로부터 모든 커넥션을 받아오면 멈추게 된다
  - 그렇기에 빨리 요청을 처리하고 반환 해줘야 한다. `**close(); !**`

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f80c6cbf-0d0b-41e6-b43d-2e62f7875ff6/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c21c1150-3a7c-4e20-b822-3c60dbb19a69/Untitled.png)

- Spring Boot를 공부를 한다는 것은 Servie와 DAO를 만든다는 것
- 단일 책임의 원칙
  - 하나의 계층은 하나의 책임만 져야 한다
- DTO는 한 건의 정보를 담을 수 있는 객체
- Entity는 DB로부터 정보를 읽어오는 객체

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/184bae54-12db-45bd-b6b3-0a047a3e2872/Untitled.png)

- RowMapper와 같이 하나의 구현 메소드만 갖고 있는 인퍼테이스를 함수형 인터페이스라고 한다.
  - 함수형 인터페이스들이 람다식으로 바꿀 수 있는 인터페이스들이다

> **RowMapper**
>
> [[Jdbc-Template\] select문 / query() , queryForObject() / RowMapper / 익명클래스, 람다식 , 내부클래스 활용](https://u-it.tistory.com/entry/Spring-JDBC-Jdbc-Template의-select-RowMapperType-과-익명클래스-람다식문법-활용)
>
> - RowMapper는 전통방식의 JDBC 코드에서 while문의 범위를 말한다
> - 이 클래스의 목적은 ResultSet 사용을 편하게 하기 위한 클래스이다
>
> +! NamedParameterJdbcTemplate 공부!