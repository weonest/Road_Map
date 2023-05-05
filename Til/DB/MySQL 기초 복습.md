# MySQL 기초 복습

------

SQL 공부는 평소에 코딩테스를 푸는 것 만큼 열심히 하지 않았기 때문에 배웠던 것들도 많이 잊어버린 상황이었다. 그런김에 다시 한 번 정리를 하는 시간을 가지면서 SQL 주요 문법들을 익혀보도록 하자!

## Distinct와 Count

중복을 제외한 수를 셀때 자주 쓰이기 때문에 꼭 외워두자!

```sql
select course_id, count(distinct(user_id)) as cnt_checkins 
from checkins
```

중복을 먼저 제거한 후 count를 한다고 생각하면 쉽다.

## Between

Between은 Date 형태의 컬럼과 자주 쓰이는데 포함 범위가 `값1 ≤ 조건 ≤ 값2` 와 같은 형태이다.

이를 활용하면 다음과 같다.

```sql
select * 
from employees
where employee_id between 150 and 155; // 150~155 까지 출력
```

- 2020/07/12 ~ 13 에 가입한 유저의 수

```sql
select count(*) from users
where created_at between "2020-07-12" and "2020-07-14"
and email like "%gmail.com";
```

이 쿼리를 보면 뒤의 값2의 조건이 "`2020-07-14`" 이기 때문에 14일까지 포함할 것이라고 착각하기 쉬운데, 이는 시간까지 표현하자면 `2020-07-12 00:00:00 ≤ 조건 ≤ 2020 - 07 - 14 00:00:00` 이기 때문에 14일 데이터는 포함하지 않는 것이다.

헷갈린다면 시간까지 지정해서 정확하게 표현해주면 좋을 것 같다.

## Join

![https://velog.velcdn.com/images/profile_exe/post/75f6f9b2-1a59-4ded-a099-373ef73838c6/INNER_OUTER_JOIN.jpg](https://velog.velcdn.com/images/profile_exe/post/75f6f9b2-1a59-4ded-a099-373ef73838c6/INNER_OUTER_JOIN.jpg)

(출처 : https://velog.io/@profile_exe/SQL-LEFT-JOIN-INNER-JOIN-차이)

### inner join

```sql
select * from orders o
inner join users u
on o.user_id = u.user_id;
```

`inner join`은 두 테이블의 교집합만을 출력하며, 위와 같이 user_id 라는 공통 컬럼을 갖고 있다면 두 테이블을 연결하여 출력할 수 있다.

```sql
// #1 inner 생략
select * from orders o
join users u
on o.user_id = u.user_id;

// #2 join 자체 생략
select * from orders o, users u
where o.user_id = u.user_id;
```

위와 같이 생략하여 표현도 가능하다

### left, right join

`left, rifgh join`은 위에 첨부한 그림과 같이 join 대상에 해당하는 부분집합만을 표현한다.

때문에 어디에 어떤 테이블을 붙일 것인지에 대한 순서가 상당히 중요하다.

```sql
select * from users u
left join point_users pu on u.user_id = pu.user_id
```

만일 join 대상과 붙이는 대상의 컬럼값이 일치하지 않는다면, 해당 컬럼의 값이 null인 상태로 표현된다.

## Union

`union` 같은 경우는 Select를 여러 번 하여 얻어낸 결꽈를 한 번에 모아서 보고싶을 때 사용한다.

- 7월과 8월의 값을 합쳐서 보고싶은 경우

```sql
(
	select '7월' as month, c.title, c2.week, count(*) as cnt from checkins c2
	inner join courses c on c2.course_id = c.course_id
	inner join orders o on o.user_id = c2.user_id
	where o.created_at < '2020-08-01'
	group by c2.course_id, c2.week
  order by c2.course_id, c2.week
)
union all
(
	select '8월' as month, c.title, c2.week, count(*) as cnt from checkins c2
	inner join courses c on c2.course_id = c.course_id
	inner join orders o on o.user_id = c2.user_id
	where o.created_at > '2020-08-01'
	group by c2.course_id, c2.week
  order by c2.course_id, c2.week
)
```

union 사용 시 주의할 점은 unoin을 사용하게 될 경우 order by를 통한 내부 정렬이 작동하지 않게 된다. 이러한 이유 등으로 `Subquery`를 보편적으로 사용하게 된다.

## Where 에 들어가는 Subquery

Where는 조건문이다. Subquery의 결과를 Where의 조건으로 활용하는 방식으로 유용하게 데이터를 추출할 수 있다.

- 카카오페이로 결제한 주문건 유저들만, 유저 테이블에서 출력해주고 싶을 때

```sql
select user_id, name, email from users
where user_id in (
		select user_id from orders
		where payment_method = 'kakaopay'
)
```

- 전체 유저의 포인트의 평균보다 큰 유저들의 데이터 추출하기

```sql
select * from point_users pu 
where point > (
	select avg(point) from point_users pu
)
```

- 이 씨 성을 가진 유저들의 평균 포인트보다 더 많은 포인트를 가지고 있는 데이터 추출

```sql
select * from point_users pu 
where point > (
	select avg(point) from point_users pu, users u
  where pu.user_id = u.user_id
	and u.name like '이%'
)

// 또는 이중 subqeury 
select * from point_users pu 
where point > (

	select avg(point) from point_users pu 
	where user_id in (

		select user_id from users where name like '이%'
)
	and u.name like '이%'
```

## Select 절에 들어가는 Subqeury

`Select`는 결과를 출력해주는 부분이다. 기존 테이블에 함께 보고싶은 통계 데이터를 손쉽게 붙일 수 있다.

- ‘오늘의 다짐’ 데이터를 보고 싶은데 ‘오늘의 다짐’ 좋아요의 수와 좋아요 수의 평균을 출력하고 싶을 때

```sql
select checkin_id, 
				user_id, 
				likes,
				(
				select avg(likes) 
				from checkins
				where user_id = c1.user_id
				) as avg_likes_user
	from checkins c1
```

- checkins 테이블에 course_id별 평균 likes 수 필드 우측에 붙여보기

```sql
select c1.checkin_id, c1.course_id, c1.user_id, c1.likes, 
		(
	  select avg(likes) from checkins 
		where course_id = c1.course_id
    ) as avg_likes
from checkins c1
```

- 위의 예제에서 course_id 대신 title을 넣어보기

```sql
select c1.checkin_id,
		(
		select title 
		from courses c2
		where c1.course_id = c2.course_id
		) 
		as title,
		 c1.user_id, c1.likes, 
		(
	  select avg(likes) 
		from checkins 
		where course_id = c1.course_id
    ) as avg_likes
from checkins c1

// 또는

select checkin_id, 
				c2.title,
				user_id, 
				likes,
				(
				select avg(likes) from checkins
				where user_id = c1.user_id
				) as avg_likes_user
	from checkins c1
inner join courses c2 on c1.course_id = c2.course_id
```

## From 에 들어가는 Subquery (가장 많이 사용!)

내가 만든 Select와 이미 있는 테이블을 Join하고 싶을 때 사용하면 좋다

- 유저 별 좋아요의 평균을 구했을 때 이 사람들의 평균 포인트도 알고 싶은 경우

```sql
select pu.user_id, pu.point, avg.avg_likes from point_users pu 
inner join ( 
	select user_id, round(avg(likes), 1) as avg_likes 
	from checkins
	group by user_id 
) avg on pu.user_id = avg.user_id
```

1. course_id 별 유저의 체크인 개수

```sql
select course_id, count(distinct(user_id)) as cnt_checkins 
from checkins
group by course_id;
```

1. course_id 별 인원

```sql
select course_id, count(*) as cnt_total
from orders
group by course_id;
```

1. course_id 별 like 개수에 전체 인원을 붙이기

```sql
select a.course_id, a.cnt_checkins, b.cnt_total 
from 
(
	select course_id, count(distinct(user_id)) as cnt_checkins 
	from checkins
	group by course_id
) a
inner join
(
	select course_id, count(*) as cnt_total
	from orders
	group by course_id
) b on a.course_id = b.course_id
```

1. 퍼센트를 나타내기

```sql
select a.course_id, a.cnt_checkins, b.cnt_total, 
round(a.cnt_checkins / b.cnt_total,1) as ratio 
from 
(
	select course_id, count(distinct(user_id)) as cnt_checkins 
	from checkins
	group by course_id
) a
inner join
(
	select course_id, count(*) as cnt_total
	from orders
	group by course_id
) b on a.course_id = b.course_id
```

1. 강의 제목도 나타내기

```sql
select a.course_id, a.cnt_checkins, b.cnt_total, 
round(a.cnt_checkins / b.cnt_total,1) as ratio, b.course_title
from 
(
	select course_id, count(distinct(user_id)) as cnt_checkins 
    from checkins
	group by course_id
) a
inner join
(
	select course_id, count(*) as cnt_total, course_title
	from orders
	group by course_id
) b on a.course_id = b.course_id

// 또는

select a.course_id, a.cnt_checkins, b.cnt_total, 
round(a.cnt_checkins / b.cnt_total,1) as ratio, c.title 
from 
(
	select course_id, count(distinct(user_id)) as cnt_checkins 
	from checkins
	group by course_id
) a
inner join
(
	select course_id, count(*) as cnt_total
	from orders
	group by course_id
) b on a.course_id = b.course_id
inner join courses c on a.course_id = c.course_id
```

## With 사용하기

- 위의 코드에서 with를 사용하여 더욱 간결하게 만들기

```sql
with table1 as (
	select course_id, count(distinct(user_id)) as cnt_checkins 
	from checkins
	group by course_id
), table2 as (
	select course_id, count(*) as cnt_total
	from orders
	group by course_id
)

select a.course_id, a.cnt_checkins, b.cnt_total, round(a.cnt_checkins / b.cnt_total,1) as ratio, c.title 
from table1 a
inner join table2 b on a.course_id = b.course_id
inner join courses c on a.course_id = c.course_id;
```

## SUBSTRING_INDEX(COLUMN, ‘TARGET’, INDXE)

- 문자열 쪼개보기

```sql
select user_id, email, SUBSTRING_INDEX(email, '@', 1) from users
// email 컬럼에서 '@'를 기준으로 앞에있는 것 (뒤는 -1)
```

- 문자열 자르기

```sql
select order_no, created_at, substring(created_at, 1, 10) as date 
from orders
group by date;
```

- 위에서 자른 문자열을 통해 일별로 주문이 몇개 들어왔는지 확인하기

```sql
select substring(created_at, 1, 10) as date, count(*)
from orders
group by date;
```

## Case

```sql
select pu.user_id, pu.point,
		(case when pu.point > 10000 then '잘 하고 있어요' 
		else '조금만 더 화이팅!' end) as msg
from point_users pu
```

- 위를 활용하여 구간별로 확인하고 통계를 내보기

```sql
select a.lv, count(*) 
from (
	select pu.user_id, pu.point,
		(case when pu.point > 10000 then '1만 이상' 
		      when pu.point > 5000 then '5천 이상'
		      else '5천 미만' end) as lv
	from point_users pu
) a
group by a.lv
```

- 위를 With로 바꾸기

```sql
with table1 as (
	select pu.user_id, pu.point,
		(case when pu.point > 10000 then '1만 이상' 
		      when pu.point > 5000 then '5천 이상'
		      else '5천 미만' end) as lv
	from point_users pu
)

select a.lv, count(*) 
from table1 a
group by a.lv
```

## Quiz

- 평균 이상 포인트를 기준으로 메세지를 출력하기

```sql
select point_user_id, point, 
	case when point > 
				(
        select avg(point) from point_users
        )
        then '잘 하고 있어요' 
        else '열심히 합시다!' end as msg
from point_users
```

- 이메일 도메인별 유저의 수 세어보기

```sql
select substring_index(email, '@', -1) as domain, count(*) as cnt
from users
group by domain

// 또는

select domain, count(*) as cnt 
from (
	select substring_index(email, '@', -1) as domain
	from users
    ) a
group by domain;
```

- ‘화이팅’이 포함된 오늘의 다짐만 출력해보기

```sql
select * from checkins
where comment like '%화이팅%'
```

- 수강등록정보 (enrolled_id)별 전체 강의 수와 들은 강의의 수 출력해보기

```sql
SELECT * FROM sparta.enrolleds_detail;

with table1 as(
select enrolled_id, count(*) as done_cnt from enrolleds_detail 
where done = 1
group by enrolled_id
),
table2 as (
select enrolled_id, count(enrolled_id) as total from enrolleds_detail
group by enrolled_id
)

select a.enrolled_id, a.done_cnt, b.total
from table1 a 
inner join table2 b on a.enrolled_id = b.enrolled_id
```

- 수강등록정보 (enrolled_id)별 전체 강의 수와 들은 강의의 수, 그리고 진도율 출력

```sql
SELECT * FROM sparta.enrolleds_detail;

with table1 as(
select enrolled_id, count(*) as done_cnt from enrolleds_detail 
where done = 1
group by enrolled_id
),
table2 as (
select enrolled_id, count(enrolled_id) as total from enrolleds_detail
group by enrolled_id
)

select a.enrolled_id, a.done_cnt, b.total, round(a.done_cnt / b.total, 1) as raito
from table1 a 
inner join table2 b on a.enrolled_id = b.enrolled_id
```

- **더 간단하게 만들 수 있지 않을까????**

```sql
select enrolled_id, 
		sum(done) as done_cnt,
		count(*) as total
	from enrolleds_detail
group by enrolled_id
```

- 진도율 까지

```sql
select enrolled_id, 
		sum(done) as done_cnt,
		count(*) as total,
    round(sum(done) / count(*), 1) as ratio
	from enrolleds_detail
group by enrolled_id
```

이상으로 짧게나마 MySQL 복습을 진행해보았다! `group by`의 조건문인 `having` 이라던지 아직 제대로 복습하지 못한 다양한 문법들이 남아있으므로 이러한 문법들도 차차 학습을 진행해 나가도록 하자!