# SQL 문제풀이

## 문제 1

![https://velog.velcdn.com/images/oeckikek/post/4308b2e6-45a2-4fa6-8f47-683f0e79d813/image.png](https://velog.velcdn.com/images/oeckikek/post/4308b2e6-45a2-4fa6-8f47-683f0e79d813/image.png)

```sql
SELECT JOB AS "직무", ROUND(AVG(SAL), 0) AS "급여 평균"
FROM emp
WHER제3
```

## 문제2

![https://velog.velcdn.com/images/oeckikek/post/750844e5-c5b8-4003-a42f-1f0b67b2565f/image.png](https://velog.velcdn.com/images/oeckikek/post/750844e5-c5b8-4003-a42f-1f0b67b2565f/image.png)

```sql
SELECT D.DNAME AS "부서명", COUNT(ENAME) AS "직원 수"
FROM emp E
	 INNER JOIN dept D
     ON E.DEPTNO = D.DEPTNO
GROUP BY E.DEPTNO
HAVING COUNT(ENAME) >= 4;
```

### 문제3

![https://velog.velcdn.com/images/oeckikek/post/d4640fd3-2b2e-47f9-84c4-58c1563618ef/image.png](https://velog.velcdn.com/images/oeckikek/post/d4640fd3-2b2e-47f9-84c4-58c1563618ef/image.png)

```sql
select case grouping(job) when 1 then 'total' else job end as "직무명", format(sum(sal), 0) as "급여의 합"
from emp group by job with rollup order by "직무명";
```

- `case` : 조건을 통과하고 첫 번째 조건이 충족되면 값을 반환한다. 조건에 따라 참이면 읽기를 중지, 결과를 반환하고, 조건이 거짓이면 `else`절의 값을 반환한다.
- **CASE문 사용 방법**
  - `when - then` 은 항상 같이 사용되어야 한다
  - `when - then` 은 여러개 사용이 가능하다.
  - `else`가 존재하면 모든 `when - then` 조건이 참이 아닌 경우 `else`의 결과값을 반환한다.
  - `else`가 없고, 조건이 참이 아니면 `null`을 반환한다.

```sql
# CASE문 사용 방법

CASE
	WHEN 조건1 THEN 결과값1
	WHEN 조건2 THEN 결과값2
	WHEN 조건N THEN 결과값N
	ELSE 결과값
END
# 예제
CREATE TABLE `test` (
  `id` int(1) unsigned NOT NULL,
  `number` int(1) unsigned NOT NULL,
  `kind` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

# INSERT 문
INSERT INTO `test` (`id`, `number`, `kind`) VALUES
  ('1', '1', 'Apple'),
  ('2', '1', 'Pear'),
  ('3', '2', 'carrot'),
  ('4', '3', 'cat'),
  ('5', '3', 'dog'),
  ('6', '4', 'human');

SELECT 
	id AS id,
	(
    	CASE
	  WHEN number = 1 THEN 'fruit'
	  WHEN number = 2 THEN 'vegetable'
	  WHEN number = 3 THEN 'animal'
	  ELSE 'not'
    	END
	) AS type,
	kind AS type_desc
FROM
	test;

[OUTPUT]
---------------------------------
id	type		type_desc
---------------------------------
1	fruit		Apple
2	fruit		Pear
3	vegetable	carrot
4	animal		cat
5	animal		dog
6	not		human
```

- **WITH ROLLUP, GROUPING**

  - `with rollup`은 그룹 항목 총합(총계)이나, 각 그룹 별 중간합계(소계)가 필요할 경우 사용 (문제에서는 total을 구하기 위해 사용)
  - grouping은 중간합계(소계) 값의 구분자라고 생각하면 됨 ( `1`일 경우가 구분자가 되며, 문제에서는 `grouping(job) when 1 then 'total'`로 구분자를 total로 생성하게 했음

- **FORMAT**

  ```sql
  [FORMAT 사용해 숫자 타입의 데이터를 세 자리마다 쉼표(,) 표시 실시]
  1. FORMAT() 함수는 숫자 타입의 데이터를 세 자리마다 쉼표(,)를 사용하는 '#,###,###.##' 형식으로 변환해 줍니다 
  2. FORMAT() 함수로 반환되는 데이터의 형식이 숫자 타입이 아닌 문자열 타입 
  3. FORMAT 문법 : FORMAT(컬럼 및 데이터, 소주점 이하 표시될 자리수)
  
  -- [select 문 수행 실시]  
  SELECT FORMAT(123456789.123456, 3) as "소수점 쉼표구분", -- [소주점 이하 3자리 표시]
         FORMAT(123456789, 4) as "정수 쉼표구분"; -- [소수점 이하 4자리 표시]
  ```

## 문제 4

![https://velog.velcdn.com/images/oeckikek/post/932aa477-06ba-4187-a695-e8a77292de16/image.png](https://velog.velcdn.com/images/oeckikek/post/932aa477-06ba-4187-a695-e8a77292de16/image.png)

```sql
select ename as "직원명", sal as "급여", grade as "급여 등급" 
from emp e, salgrade where e.sal = 
(select max(sal) from emp) and (select e.sal between losal and hisal);
```

## 문제 5

![https://velog.velcdn.com/images/oeckikek/post/5f8c9e16-446f-4beb-b549-5b7d9d000431/image.png](https://velog.velcdn.com/images/oeckikek/post/5f8c9e16-446f-4beb-b549-5b7d9d000431/image.png)

```sql

```

ㅇ