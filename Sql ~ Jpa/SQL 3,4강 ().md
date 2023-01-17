# SQL 3,4강 ()

## JOIN (테이블 병합)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/15acf61b-33dd-4fe5-8df1-393f4ee83a13/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0322ddeb-7b9c-4c08-9e2c-39a158ccfb76/Untitled.png)

- 두 테이블을 곱한만큼의 필드가 출력됨
  - 두 테이블의 모든 컬럼을 참조하는 필드가 출력되어 **잉여 필드**들이 무수히 생성됨
- 즉, 부서의 이름과 그 부서의 매니저 이름을 출력하기 위해서는 다음과 같이 해야함

```sql
select * from departments, employees where departments.manager_id = employee_id;

select department_name, concat(first_name, ' ', last_name) as name from departments, employees where departments.manager_id = employee_id;
```

- 다음과 같이 `where` 절이 `Join`이 되는 것

```sql
# 실습
select * from employees where employee_id = "201";

select department_name from departments;

select concat(first_name, ' ', last_name) as name from employees;

# 부서의 이름과 사원의 이름을 출력

select * from departments, employees;

select count(*) from departments, employees; # 두 테이블을 곱한 개수만큼 필드 출력

select * from departments, employees where departments.manager_id = employee_id;

select department_name, concat(first_name, ' ', last_name) as name from departments, employees where departments.manager_id = employee_id;

select department_name, concat(first_name, ' ', last_name) as name from employees, departments where employees.department_id = departments.department_id;

select e.first_name, d.department_name from employees e, departments d where e.department_id = d.department_id;

select * from locations;

# 부서의 이름과 그 부서가 속한 도시의 이름 
# 부서의 location_id = 지역의 location_id

select d.department_name, l.city from departments d, locations l where d.location_id = l.location_id;

# 사원의 이름과 그 사원이 속한 부서가 속한 도시의 이름

select e.first_name, l.city from employees e, departments d, locations l where e.department_id = d.department_id and d.location_id = l.location_id; 

select e.email, r.region_id from employees e, departments d, locations l, countries c, regions r where e.department_id = d.department_id and d.location_id = l.location_id and l.country_id = c.country_id and c.region_id = r.region_id;

# 사원의 상사 아이디와, 그 사원이 속한 부서의 매니저 아이디를 출력하시오
select e.employee_id, e.manager_id, d.manager_id from employees e, departments d where e.department_id = d.department_id;

# 사원의 이름과 부서명을 출력 ( 단, location_id 가 1800인 경우만 )
select e.first_name, d.department_name from employees e, departments d where e.department_id = d.department_id and d.location_id = 1800;
```

### Workbench의 Reverse Engineer = 관계도

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7360e684-d58f-4f20-bd0f-fd6117c61354/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5979a032-9a6e-4501-9238-69d40808b8a5/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ee9b2e0c-c5af-4da2-9fc3-e51b1f186c2d/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/69750617-86c7-4963-a86f-f9b1a16d73b6/Untitled.png)

# 4강

```sql
#실습 
show tables;

select * from emp;

select * from salgrade;

# 사원의 이름과 사원의 급여 등급을 출력하시오

select e.ename, s.grade from emp e, salgrade s where e.sal >= s.losal and e.sal <= s.hisal order by s.grade ASC;

select * from emp e, salgrade s where e.sal >= s.losal and e.sal <= s.hisal;

select e.ename, s.grade from emp e, salgrade s where e.sal between s.losal and s.hisal order by s.grade ASC;

select * from departments; # 27건

select * from locations; # 23건

select * from departments natural join locations; # 27건. 즉, 모든 부서는 location 값이 있다는 것

select * from departments, locations where departments.location_id = locations.location_id; # 위와 같은 값

# natural join 사용시 주의사항
select * from employees e, departments d where e.department_id = d.department_id; 
select * from employees natural join departments; # 같은 컬럼명이 있다면 이상한 결과를 도출할 수도 있다.
select * from employees join departments using(department_id);
select * from employees e join departments d on(e.department_id = d.department_id);
select * from employees e join departments d where(e.department_id = d.department_id); # where 도 가능

select * from employees where department_id is null;

# 부서가 없을 경우도 사원의 이름과 부서를 출력
select * from employees left outer join departments using(department_id); #outer는 생략 가능

select employee_id, first_name, manager_id from employees;

# 사원의 이름과 사원의 상사의 이름을 출력
select employee_id as "상사의 id", first_name as "상사의 이름" from employees where employee_id = 100;

select * from employees e, employees m where e.manager_id = m.employee_id;

select e.first_name as "직원 이름", m.first_name as "상사 이름" from employees e, employees m where e.manager_id = m.employee_id;

select e.first_name as "직원 이름", m.first_name as "상사 이름" from employees e join employees m on(e.manager_id = m.employee_id);

select e.first_name as "직원 이름", m.first_name as "상사 이름" from employees e left join employees m on(e.manager_id = m.employee_id);
```

- `natural join` 은 같은 컬럼명이 있는 경우 옳치 않은 결과가 도출될 수도 있기 때문에 지양하자

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/85e5c34f-4e5b-4df2-8b79-11d97b6156a4/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e506894f-8d64-4199-9028-a599a57c9347/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/97a84389-e08f-4dbf-9431-19cd62ca1f6f/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f7a2a8cc-f10c-4d24-b686-3a084b4d1154/Untitled.png)

- 실습 DB에서는 같은 컬럼명이 없기에 주의

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/459ea4ff-a6de-4f21-aa26-432c887b68a3/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5565d7a1-b6aa-4124-9bac-ba70dbac78e9/Untitled.png)

- 셀프 조인은 반드시 `alias` 사용해야함