# JDBC 프로그래밍

### JDBC 정의

- 자바를 이용한 데이터베이스 접속과 SQL 문장의 실행, 그리고 실행 결과로 얻어진 데이터의 핸들링을 제공하는 방법과 절차에 관한 규약
- 자바 프로그램내에서 SQL 문을 실행하기 위한 자바 API
- SQL과 프로그래밍 언어의 통합 접근 중 한 형태
- JAVA는 표준 인터페이스인 JDBC API를 제공
- 데이터베이스 벤더, 또는 기타 써드파티에서는 JDBC 인터페이스를 구현한 드라이버를 제공한다

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9d6377c7-990a-441d-b4f2-cf97873f079c/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1d862cb9-2081-45f0-8304-8c0359e9fb92/Untitled.png)

- Intellij 상에서 Open module setting 등을 통해 jdk 버전을 맞춰주자
- maven 프로젝트 생성시의 archetype은 어떤 프로젝트를 만들 것인지에 따라 선택 (여기선 quickstart사용)
- Modul.java.sql 패키지가 JDBC api 이다
  - 이 내부에 여러 개의 인터페이스가 존재한다
  - DOC (문헌) 에서 인터페이스를 누르면 구현 클래스가 나와야 하는데, 나오지 않는 이유는 JAVA에서는 인터페이스만을 제공해주고 있기 때문이다. 이 인터페이스들을 실제로 구현하고 있는 것은 JDBC Driver이다.
    - 좀 더 자세히 말하자면, JAVA의 `LIST<E>` 인터페이스는 AbstractList, ArrayList 등 어떤 클래스들을 사용하여 만들어진 인터페이스인지 나오지만, `Connection, ResultSet` 등은 JAVA 내부의 클래스들이 아닌 JDBC Driver에서 제공하는 클래스들로 이루어져있기 때문에 JAVA에서는 인터페이스만을 제공한다는 것
- JAVA JDBC Driver 에서 가장 중요한 것이 뭐냐 물으면 `Connection, Prepared Statement, ResultSet` 이 3개의 인터페이스이다

## JDBC 프로그래밍 방법

1. improt.java.sql.*;
2. 드라이버를 로드 한다
3. Connection 객체를 생성한다
4. PreparedStatement 객체를 생성한다
5. PreparedStatement에 값을 바인딩한다
6. SELECT 문일 경우 ResultSet을 이용하여 데이터를 읽어온다
7. Connection, PreparedStatement, ResultSet을 모두 close() 한다

- Statement, PreparedStatement, CallalbleStatement를 이용해 SQL을 실행할 수 있다. procedure는 CallableStatement를 이요해 실행한다. 그 외에는 PreparedStatement로 실행 하는 것이 좋다

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ce61e88d-76b3-4523-988b-844d80f6cf66/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/28786f4f-6966-4f09-ba99-fbbcc3210bd4/Untitled.png)

- 현재는 Classo.forName 구문을 적지 않아도 무방하다

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f52a77f6-a5a6-4123-8107-bb3b8faa103d/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/eb4fcd2e-2018-45f1-8440-5bc43578a0bf/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/fb02ed7b-520f-45eb-a8db-75876276bb9b/Untitled.png)

d