# SQL 3강 ()

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

d