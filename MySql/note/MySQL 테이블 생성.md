# MySQL 테이블 생성

```java
CREATE TABLE topic(
    ->  id INT(11) NOT NULL AUTO_INCREMENT,
    ->  title VARCHAR(100) NOT NULL,
    ->  created DATETIME NOT NULL,
    ->  author VARCHAR(15) NULL,
    ->  profile VARCHAR(200) NULL, PRIMARY KEY(id));
```

### PRIMARY KEY

PRIMARY KEY 제약 조건을 설정하면, 해당 필드는 NOT NULL과 UNIQUE 제약 조건의 특징을 모두 가집니다.

따라서 이 제약 조건이 설정된 필드는 NULL 값을 가질 수 없으며, 또한 중복된 값을 가져서도 안 됩니다.

이러한 PRIMARY KEY 제약 조건을 기본 키라고 합니다.

UNIQUE는 한 테이블의 여러 필드에 설정할 수 있지만, PRIMARY KEY는 테이블당 오직 하나의 필드에만 설정할 수 있습니다.

이러한 PRIMARY KEY 제약 조건은 테이블의 데이터를 쉽고 빠르게 찾도록 도와주는 역할을 합니다.

- 즉, 내가 만드려는 테이블에서 ID 값은 중복되면 안되기 때문에 프라이머리 키를 사용해준다
- 테이블에 자료 넣기

```java
INSERT INTO topic (title,description,created,author,profile) VALUES('MongoDB', 'MongoDB is...', now(), 'egoing', 'developer');
```

- 테이블 확인하기

```java
DESC 테이블명;
```

- 테이블에 컬럼 추가하기

```java
alter table 테이블명 add 컬럼명 자료형(자료형길이) 제약조건;
```

- 테이블 확인하기

```java
SELCET * FROM 테이블명; (범위 지정 안 하면 전부 가져옴)
```

- 셀렉트

```java
select id, title, created, author from topic where author='egoing' order by id desc;
SELECT id, title, created, author FROM topic WHERE author='egoing' order by id DESC LIMIT 2;
```

- 업데이트

```java
UPDATE topic SET description='ORACLE is...', title = 'ORACLE' WHERE id = 2;
```

- 딜리트

```java
DELETE FROM topic WHERE id = 5;
```

- 컬럼 순서 변경

```java
ALTER TABLE topic MODIFY COLUMN description TEXT AFTER author;
ALTER TABLE 테이블명 MODIFY COLUMN 컬럼명 자료형 AFTER 다른컬럼;
```

