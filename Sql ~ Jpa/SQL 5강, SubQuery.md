# SQL 5강, SubQuery

```sql
#실습
# SCOTT의 급여보다 높은 급여를 받는 사람의 이름을 출력하시오

# SCOTT의 급여를 알아야 한다

use bubu;

select * from emp;

select sal from emp where ename="SCOTT";

# 3000보다 높은 급여를 받는 사람

select ename from emp where sal > 3000; # KING 

select ename from emp where sal > (select sal from emp where ename="SCOTT"); #Suq Qeury
# 위의 커리는 동명이인이 있는 경우 오류가 발생한다

# emp 테이블에서 이름으로 정렬했을 때 첫 번째로 나오는 사원의 이름, 급여, 부서번호를 출력

select * from emp order by ename;

select min(ename) from emp; # 문자열에도 최대, 최소값을 사용할 수 있다

select ename, sal, deptno from emp where ename = (select min(ename) from emp);

# 사원의 평균 급여보다 작은 급여를 받는 사원의 이름과 급여를 출력

select ename, sal from emp where sal < (select avg(sal) from emp);

# 부서이름이 SALES인 부서의 사원의 이름과 부서번호를 출력

select * from emp where deptno = (select deptno from dept where dname = "SALES");
select * from emp join dept on (emp.deptno = dept.deptno and dname = "sales");

select ename, emp.deptno from emp, dept where emp.deptno = dept.deptno and dept.dname = "sales";
select ename, deptno from emp join dept on (emp.deptno = dept.deptno and dept.dname ="sales");
select ename, deptno from emp where deptno = (select deptno from dept where dname = "SALES");

# 데이터의 구조에 따라 서브쿼리와 심플조인의 실행 속도가 상이하다

# 중요! 아래의 식이 오류가 나는 이유
# Single Row 서브쿼리는 하나의 row에 해당하는데 group by 함수는 여러 값에 해당하기 때문
select ename = (select min(ename) from emp group by deptno;

# 부서별로 첫 번째로 등장하는 사원의 이름, 급여, 부서 번호를 출력

select ename, sal, deptno from emp where ename in ("ADAMS", "ALLEN", "CLARK"); # 동명이인인 경우가 있기에 X

select ename, sal, deptno from emp where (ename, deptno) in (select min(ename), deptno from emp group by deptno); # 서브 쿼리에 그룹바이

# any
select ename, sal, deptno from emp where ename = any (select min(ename) from emp group by deptno);
select * from emp where sal > any (select sal from emp where deptno = 30);

# all
select * from emp where sal < all (select e.sal from emp e where e.deptno in (30, 10));

# correlated query
# 사원의 이름, 급여, 부서 번호를 출력. 단 사원의 급여가 그 사람이 속한 부서의 평균 급여보다 큰 경우만 / in, out 관계
select e.ename, e.sal, e.deptno from emp e where e.sal > (select avg(i.sal) from emp i where i.deptno = e.deptno);

select max(sal) from emp group by deptno;

select ename from emp where(sal, deptno) in (select max(sal), deptno from emp group by deptno); # where 조건이 한 개이상이면 괄호 필수

# 테이블과 select 결과는 집합이다
# 결과 자체를 테이블처럼 사용할 수 있다

select * from(select max(sal), deptno from emp group by deptno) m; # 이 결과를 m (alias) 라는 집합으로 사용

select e.ename, e.deptno from emp e, (select max(sal) as msal, deptno from emp group by deptno) m where e.deptno = m.deptno and e.sal = msal; 

# intersect (교집합) : MySQL에서는 지원하지 않지만, 다음과 같이 구할 수 있다
select A.name from A, B where A.name = B.name;

# minus(차집합) : 역시 지원하지 않지만, 다음과 같이 가능
select A.name from A where A.name not in (select B.name from B);

# rank
select sal, ename, rank() over(order by sal desc) as ranking from emp;
```

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/afea11ee-6ab5-485d-8fe1-e6def2461e35/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/79c25b76-fdef-4454-a809-ec814a25cf61/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/cfd3c448-ea34-448f-8e98-5424b41b327e/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d4f4e344-5c53-4b9e-9645-b0a9438a0915/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/52002559-86dd-4feb-8c13-9b24d21e03b3/Untitled.png)

- `Any`는 서브쿼리와 함께 쓸때만 유용하다. 서브쿼리는 한 줄만 뱉기 때문. 같이 사용하면 여러 값 받음

  - 위 예제는 MySQL에서는 불가. 다음과 같이 사용

  ```sql
  select * from emp where sal > any (select sal from emp where deptno = 30);
  ```

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/09c03256-265f-44d5-89b9-d20002ecaa2a/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8cc4f0e2-9a51-4091-90c3-1352617b7ed7/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/65a79a96-f89b-430e-bd38-f3eaa4cb6483/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a81ca90a-2d8f-41cd-bd73-fe82b50d5dda/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/be0fd0c4-a953-4d17-8c05-fd8cfd02277c/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/dbf9ec47-32eb-49e7-a58d-b743784a3ad6/Untitled.png)

- Union

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ff756567-17e8-493e-b541-d3f247769293/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/bbebffff-3d33-4e08-92d4-8bc7308477fc/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/52be2d3c-11f3-4f9e-9e12-080185e0c9a6/Untitled.png)

- `alias`가 필요함