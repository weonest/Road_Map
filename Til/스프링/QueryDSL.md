# QueryDSL

------

```java
public interface MapRepository extends JpaRepository<Map, Long> {

    List<Map> findByNameContainingOrDesContainingOrSumContaining(String name, String des, String sum);
}
```

위 메서드는 사용자에게 이렵받은 키워드를 통해 해당하는 데이터를 찾는 메서드이다. 보다시피 메서드 명이 지나치게 길다. `Spring Data JPA`의 쿼리 메서드를 이용하였더니 메서드 명이 상당히 길다 ..

기존의 `JPQL`을 사용하는 것도 나쁜 선택은 아니지만, 두 방법 모두 Entity 구조와 검색 범위에 따라 더욱 더 복잡하고 길어지게 되는 경우가 존재한다.

이러한 문제를 해결하기 위해 알아보던 중 `QueryDSL`이라는 동적쿼리를 알게되었고, 이를 적용해보기로 하였다!

### QueryDSL이란?

QueryDSL은 정적 타입을 이용해서 SQL과 같은 쿼리를 생성할 수 있도록 해주는 프레임워크이다.

이를 통해 수 많은 쿼리를 수동으로 생성하는 과정을 자동화하여 Java코드로 작성할 수 있게 된다.

### Gradle

우선 QueyrDSL을 적용하기 위해서는 다음과 같은 설정이 필요하다

```java
// 1. queryDsl version 정보 추가
buildscript {
	ext {
		queryDslVersion = "5.0.0"
	}
}

plugins {
	id 'java'
	id 'org.springframework.boot' version '2.7.6'
	id 'io.spring.dependency-management' version '1.0.15.RELEASE'
	// 2. querydsl plugins 추가
	id "com.ewerk.gradle.plugins.querydsl" version "1.0.10"
}

group = 'naver'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

configurations {
	compileOnly {
		extendsFrom annotationProcessor
	}
}

repositories {
	mavenCentral()
}

dependencies {

	// 3. querydsl dependencies 추가
	implementation "com.querydsl:querydsl-jpa:${queryDslVersion}"
	implementation "com.querydsl:querydsl-apt:${queryDslVersion}"

}

tasks.named('test') {
	useJUnitPlatform()
}

/*
 * queryDSL 설정 추가
 */
// querydsl에서 사용할 경로 설정
def querydslDir = "$buildDir/generated/querydsl"

// JPA 사용 여부와 사용할 경로를 설정
querydsl {
	jpa = true
	querydslSourcesDir = querydslDir
}

// build 시 사용할 sourceSet 추가
sourceSets {
	main.java.srcDir querydslDir
}

// querydsl 컴파일시 사용할 옵션 설정
compileQuerydsl{
	options.annotationProcessorPath = configurations.querydsl
}

// querydsl 이 compileClassPath 를 상속하도록 설정
configurations {
	compileOnly {
		extendsFrom annotationProcessor
	}
	querydsl.extendsFrom compileClasspath
}
```

### EntityMager 주입

QueryDsl이 query를 생성할 수 있도록 **EntityManager**를 주입하기 위해 `QuerydslConfig` 클래스를 만들어 이를 설정해준다.

```java
@Configuration
public class QuerydslConfig {

    @PersistenceContext
    private EntityManager em;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }
}
```

> **@PersistenceContext란?**
>
> EntityManager를 Bean으로 주입할 때 사용하는 어노테이션이다
>
> - Spring 에서는 영속성 관리를 위해 EntityManager가 존재
> - Spring 컨테이너가 시작될 때 EntityManager를 만들어 Bean으로 등록해둠
> - 이 때 Spring이 만들어 둔 EntityManager를 주입받을 때 사용한다
>   - 최신 버전에서는 @Autowired로 주입이 가능하다고 함
>
> 위 어노테이션으로 지정된 프로퍼티에 아래의 두 가지 중 한 가지 방법으로 EntityManager를 주입한다
>
> 1. EntityManagerFactory에서 새로운 EntityManager를 생성
> 2. Transaction에 의해 기존에 생성된 EntityManager를 반환

> **@PersistenceContext를 사용해야 하는 이유**
>
> 1. EntityManager를 사용할 때 주의해야 할 점은 여러 쓰레드가 동시에 접근하면 동시성 문제가 발생하여 쓰레드 간에는 EntityManager를 공유해서는 안된다.
>
>    영속성을 다루는 부분에서 동시성 문제가 발생한다면 데이터 정합성에서 큰 문제가 발생할 터!
>
>    - 일반적으로 Spring은 `Singleton` 기반으로 동작하기에 **Bean은 모든 Thread가 공유**한다
>    - 하지만, 위 어노테이션을 사용하면 EntityManager를 주입받아도 **동시성 문제**가 발생하지 않는다
>
> 2. 동시성 문제가 발생하지 않는 이유
>
>    - Spring Container가 초기화되면서 @PersistenceContext로 주입받은 EntityManager를 `Proxy`로 감싼다
>
>    - 그리고 EntityManager 호출 시 마다 Proxy를 통해 EntityManager를 생성하여 Thread-Safe를 보장한다
>
>      > Proxy 객체는 원래 객체를 감싸고 있는 같은 타입의 객체이다. 프록시 객체가 원래 객체를 감싸서 클라이언트의 요청을 처리하게 하는 패턴이다.
>      >
>      > 사용 이유로는 접근을 제어하고 싶거나, 부가 기능을 추가하고 싶을 때(AOP) 주로 사용한다

### QueryDSL 사용 Repository 구성하기

Spring 에서 QueryDsl을 사용하는 방법에는 총 3가지 방법 (`Spring Data Jpa Custom Repository / QueryRepositorySupport / JPAQueryFactory`) 이 있다.

나는 Spring Data Jpa Custom Repository를 사용하기로 했는데, 예전에 둘러봤던 어느 개발자 분의 프로젝트에서 이와 같은 구조를 사용했던 것을 본적이 있어 다른 2개의 방법보다 익숙하기 때문이다

### **Spring Data JPA Custom Repository**

스프링에서 QueryDSL과 JPA Repository 를 함께 사용하려면 각 역할 별 파일을 두 개 만들어주거나, 하나의 파일이 두 개의 의존성을 가져야 한다. 이러한 경우 Spring Data JPA 에서 제공하는 `Spring Data Custom Repository`를 사용하면 된다고 한다.

해당 방식을 통해서 CustomRepository 를 JpaRepository 상속 클래스에서 함께 상속 받아서 사용할 수 있다. 구현된 Repository의 구조는 다음과 같다.

![https://velog.velcdn.com/images/soyeon207/post/916ecd10-4cdc-4f71-81a5-020c72a3f811/image.png](https://velog.velcdn.com/images/soyeon207/post/916ecd10-4cdc-4f71-81a5-020c72a3f811/image.png)

Repository(interface) 가 JpaRepository(interface), CustomRepository(interface)를 다중 상속 받고,

→ CustomRepository 인터페이스에 선언되어 있는 메소드에 대한 구현은 `RepositoryImpl`에서 한다.

→ 그리고 사용자는 Repository 인터페이스를 주입 받아서 사용한다.

즉, JPA에서 제공하는 Jpa Repository를 제외한 3개의 Repository를 생성하게 된다.

프로젝트에서 생성한 모습은 다음과 같다.

```java
public interface UserRepositoryCustom {
}
---------------------------------------

@RequiredArgsConstructor // queryFactory를 생성자에 포함
public class UserRepositoryImpl implements UserRepositoryCustom{
    private final JPAQueryFactory queryFactory;
}

---------------------------------------

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
}
```

**여기서 UserRepositoryImpl은 Repository를 직접적으로 구현하지 않는데 어떻게 Repository에서 사용이 가능할까?**

이는 **사용자 정의 구현 클래스**인 경우 JPA가 파일명 **repository interface 이름 + `Impl` 인 클래스를 찾아서 interface에 JpaRepository를 주입할 때에 Impl 객체를 같이 삽입**해주기 때문에 사용할 수 있게 된다고 한다.

> 즉, UserRepository가 UserRepositoryCustom을 extends 함과 동시에 Impl 구현체도 사용이 가능하게 되는 것!

→ 그렇기에 반드시 구현체 파일명의 마지막에는 `Impl`이 와야 한다.

### 적용 이전

```java
public interface MapRepository extends JpaRepository<Map, Long> {

    List<Map> findAll();

    List<Map> findByCamp(int camp);

    @Query("select m from Map m ORDER BY m.star DESC, m.id ASC")
    List<Map> findAllByOrder();

    @Query("select m from Map m where m.camp =:camp ORDER BY m.star DESC, m.id ASC")
    List<Map> findWithCampOrder(int camp);

    List<Map> findByNameContainingOrDesContainingOrSumContaining(String name, String des, String sum);
}
```

마지막 메서드를 보면 파라미터로 받은 3개의 값을 `ContainingOr`을 통해 확인하고 가져올 수 있도록  **Spring Data Jpa 쿼리메소드로** 구현하였는데, 한눈에 봐도 메서드 명이 길다는 것을 볼 수 있다. 의미만 명확하다면 쿼리명이 길어도 괜찮다고들 하지만, 만약 파라미터의 개수가 더욱 늘어난다거나 컬럼명이 더욱 길어진다거나 조건이 추가되거나 한다면….?

즉, 이와 같은 문제점을 보완하기 위해 QueryDSL을 적용하여 동적 쿼리를 구성하는 것!

> 추가적으로 QueryString이 아닌 Java 코드로 동적 쿼리를 구성하기 때문에 컴파일 타임에 에러를 잡을 수 있기도 하다

### 적용 모습

```java
package naver.map.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import naver.map.domain.Map;

import static naver.map.domain.QMap.map;

import java.util.List;

@RequiredArgsConstructor
public class MapRepositoryImpl implements MapRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Map> findAll() {
        List<Map> maps = queryFactory
                .selectFrom(map)
                .fetch();
        return maps;
    }

    @Override
    public List<Map> findByCamp(int camp) {
        List<Map> maps = queryFactory
                .selectFrom(map)
                .where(map.camp.eq(camp))
                .fetch();
        return maps;
    }

    @Override
    public List<Map> findAllByOrder() {
        List<Map> maps = queryFactory
                .selectFrom(map)
                .orderBy(map.star.desc(), map.name.asc())
                .fetch();
        return maps;
    }

    @Override
    public List<Map> findWithCampOrder(int camp) {
        List<Map> maps = queryFactory
                .selectFrom(map)
                .where(map.camp.eq(camp))
                .orderBy(map.star.desc(), map.name.asc())
                .fetch();

        return maps;
    }

    @Override
    public List<Map> findByKeyword(String keyword) {
//        if (keyword == null || keyword.isEmpty())
//            return null;

        List<Map> maps = queryFactory
                .selectFrom(map)
                .where(nameCon(keyword)
                        .or(desCon(keyword)
                                .or(sumCon(keyword))))
                .fetch();

        return maps;
    }

    private BooleanExpression nameCon(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
        return map.name.contains(keyword);
    }

    private BooleanExpression desCon(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
        return map.des.contains(keyword);
    }

    private BooleanExpression sumCon(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
        return map.sum.contains(keyword);
    }

}
```

적용한 모습은 다음과 같다.

`JPAQueryFactory`의 API는 Query문을 작성하듯이 사용하면 되기 때문에 크게 어려운 점은 없었지만, 값의 비교를 `eq`로 진행하는 것처럼 조금씩 다른 점이 있기 때문에 따로 공부를 해두면 좋을 것 같다.

적용을 하면서 한 가지 문제점을 발견했는데 기존의 쿼리 메서드에서 keyword를 통한 검색은 keyword 값이 공백이더라도 `NullPointerException`이 발생하지 않았다.

하지만 QueryDSL을 통한 구현에서는 NullPointerException이 발생하였고 이에 대한 처리를 해주어야만 했다. 몇 가지의 방법을 찾아 본 결과 `BooleanExpression`이라는 방법이 자주 쓰이는 것 같아서 적용을 해보았다.

쉽게 설명하자면 null이 들어오거나 값이 비어있는 경우 where 절을 실행시키지 않는 것이다. 하지만 비교하려는 값에 대한 메서드를 모두 만들어 주어야 한다는 점이 조금 아쉬워서 `findByKeyword` 함수 **최상단에 주석처리한 코드**로 단축시켜 보았는데, 이 역시 잘 동작하는 것을 확인했다!

### Reference

https://velog.io/@soyeon207/QueryDSL-Spring-Boot-에서-QueryDSL-JPA-사용하기

https://velog.io/@jkijki12/Spring-QueryDSL-완벽-이해하기

https://batory.tistory.com/497