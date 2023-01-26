# SQL 7강

- Auto Commit Mode = 입력, 수정, 삭제를 했을 때 데이터베이스에 바로 반영을 시키는 것
  - 커밋을 하지 않으면 작업 중인 세션에서는 데이터가 반영이 되지만, 다른 세션에서는 반영이 되지 않으며, 다른 세션에서 작업을 진행하려고 하면 `Lock`이 걸린다
- 트랜젝션 - 논리적인 하나의 작업 단위
  - `begin;`명령어를 통해 트랜잭션 시작. `commit;` 앞에서 시작된 트랜잭션을 DBMS에 반영. `rollback`; 함수는 롤백
  - DDL (데이터 정의어), DCL (데이터 제어어)를 사용하게 되면 트랜잭션이 처리가 되어버림(롤백 불가. 앞에서 진행한 내용을 모두 반영하므로 사용 주의)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/63abaf35-ee6b-4de3-b049-40701a52f0a0/Untitled.png)

- 트랜잭션 중 `rollback`을 하게되면 `Auto Increment`가 적용된 값에는 영향을 미치지 않아 그대로 값이 증가하게 된다

- PK를 지정한 값이 복수의 값을 갖게하려면 연속해서 지정하면 된다.

  - 아래의 예시와 같이 1,1,2 가 오는 것은 가능. 1,2,1은 불가

  ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/003d2715-45b1-404f-8655-12fda084c6f0/Untitled.png)

  ```sql
  # 홍길동 사용자의 이름과 홍길동 사용자의 권한명을 출력하시오
  select u.name, r.name from user u, user_role ur, role r where
  u.user_id = ur.user_id and ur.role_id = r.role_id and u.user_id = 2;
  
  # 김갑순을 회원으로 등록하고 ROLE_USER 권한을 부여하시오
  begin;
  
  insert into user values('kim@naver.com', '김갑순', '1234');
  
  insert into user_role (user_id, role_id) values ( last_isnert_id(), 1);
  ```

- `last_isnert_id()` = 같은 트랜지션 내부에서만 사용 가능. 마지막으로 `AI`한 id값을 반환

### last_insert_id() 사용시 주의!!

```sql
DROP TABLE IF EXISTS `last_insert_id_table`;

CREATE TABLE `last_insert_id_table` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `col` VARCHAR(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO last_insert_id_table(col)
VALUES('1row'),('2row'),('3row');

SELECT last_insert_id();
/*
last_insert_id()  
------------------
                 ? <----------------- last_insert_id() 값은?
*/
```

아마 대부분이 마지막으로 AI가 된 id 값인 ‘3’을 반환할 것이라고 예상할 것이다. 하지만 1을 반환한다. 다음의 쿼리문을 보자

```sql
DROP TABLE IF EXISTS `last_insert_id_table`;

CREATE TABLE `last_insert_id_table` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `col` VARCHAR(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO last_insert_id_table(col)
VALUES('1row');

INSERT INTO last_insert_id_table(col)
VALUES('2row');

INSERT INTO last_insert_id_table(col)
VALUES('3row');

SELECT last_insert_id();
/*
last_insert_id()  
------------------
                 ? <----------------- last_insert_id() 값은?
*/
```

이렇게 진행하면 예상했던 ‘3’이 반환된다.

이런 결과가 나오는 이유는 `last_insert_id()` 함수는 1개의 insert 쿼리에 대해서 성공시 마지막 `auto_increament` 값을 반환한다. 즉, `insert into last_insert_id_table(col) valeus('1row'),('2row'),('3row');` 쿼리는 1개이고 이에 대해서 AI가 적용된 것은 1인 것이다.

대량의 데이터를 배치로 insert 하는 경우가 종종 발생하는데, 이 경우 위처럼 `last_isnert_id()`를 잘못 사용하는 오류가 없는지 확인하자.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3471f3d1-13dc-4491-8656-177d809b52e5/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/00a032c8-f0a0-471f-a9ac-74c6951bc49d/Untitled.png)

- 위의 두 작업이 하나의 트랜잭션으로 묶여야 한다