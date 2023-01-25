# SQL 6강

## DML, DDL, DCL, TCL

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/309f61ca-b998-4c6d-84af-417b56dbecdc/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b75a28d8-59b9-4559-8908-158d1a6f53c1/Untitled.png)

- Spatial : 위도 경도
- JSON : 하나의 컬럼에 데이터가 다 들어갈 수 있음
- 등등 공식 홈페이지에 자세하게 설명

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/94b973f8-b1f9-40f2-a514-1bb0667cf775/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/12a04ebc-84c3-4ece-b2aa-4208d4cdb2e7/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/48012201-b94d-463e-9ce7-a5f1905c86c4/Untitled.png)

- 요구조건 분석 단계 : 회원가입 (이메일, 암호, 이름 / 게시판에서는 제목과 내용, 로그인 여부 등)
- 개념석 설계 단계 : 보통 한글로 명사들을 뽑음 (회원, 게시판, 암호, 이메일) 각 명사(엔티티)들의 관계를 추출
- 논리적 설계 단계 (정규화) : 데이터들을 중복되지 않도록 (유일하게) 하는 과정
- 물리적 설계 단계 (반정규화)
- 구현 단계

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f10b5d72-e3b3-40e1-9293-cac18c5eb9fe/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/baf7ad8c-4d2e-45e4-b4ec-45a3ad8cca74/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/931e394a-ac33-4a8d-a1f3-29776799ff71/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/681ecc67-2e32-4db2-9fb1-04b165001da8/Untitled.png)

- 정리

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7ca1a618-a5b5-4375-80b1-cdfa1a5b888c/Untitled.png)

- 회원과 권한의 관계는 다대다 관계이다

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/556c8ae7-1d54-49ed-a707-a81f16894a6f/Untitled.png)

- 정규화 작업 = 중복을 제거 하는 것
  - 같은 이메일과 같은 이름의 데이터가 있다 (일반 권한, 관리자 권한)
  - 권한 컬럼이 별도의 테이블로 빠질 수 있다

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/876ea294-28c8-407d-9a72-917f07179e2b/Untitled.png)

- user_id (INT) = 식별자 (유일성 보장)

  - 이메일도 유일성을 갖지만 PK를 주지 않는 이유는 성능을 위해서 AI(자동증가)를 통해 user_id를 식별자로 삼는다. 또한, 이메일은 후에 변경이 될 가능성도 있기 때문

- 테이블은 다대다 관계를 맺기 위해서는 `관계 테이블`이라는 것이 필수적이다 (user_role 테이블)

  - user_role의 두 가지 값은 모두 PK다

  ```sql
  drop table if exists role;
  
  create table role (
  	role_id int primary key,
  	name varchar(20)
  );
  
  insert into role(role_id, name) values(1, 'role_user');
  insert into role(role_id, name) values(2, 'role_admin');
  ```

- 회원 테이블 만들기

```sql
drop table if exists user;

create table user (
	user_id int primary key auto_increment,
	email varchar(255) not null,
	name varchar(50) not null,
	password varchar(500) not null,
	reddate timestamp default now()
);
```

- 회원_권한 테이블 만들기

```sql
drop table if exists user_role;
create table user_role (
	user_id int,
	role_id int,
	primary key(user_id, role_id),
	foreign key (user_id) references user(user_id),
	foreign key (role_id) references role(role_id)
);
	
```

- 게시물 정보를 저장하는 테이블 만들기

```sql
create table board (
	board_int int primary key auto_increment,
	title varchar(100) not null,
	content text null,
	user_id int not null,
	regdate timestamp default now(),
	view_cnt int default 0,
	foreign key (user_id) references user(user_id)
);
```

d