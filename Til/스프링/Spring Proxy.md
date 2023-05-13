# Spring Proxy

------

이전에 QueryDSL 에 대한 정리를 진행하면서 QuerydslConfig 파일에 EntitiyManager를 주입하는 부분에서 사용한 **@PersistenceContext** 라는 어노테이션에 대해 짧게 알아본 적이 있다. 해당 어노테이션을 통해 주입받은 EntityManager는 **`Proxy`**를 통해 생성됨으로써 동시성 문제가 발생하지 않는다는 내용이 있었는데, 오늘은 여기서 등장한 Spring Proxy에 대해서 알아보려고 한다.

## 스프링에서의 프록시란?

스프링에서의 프록시 역시 이름에서 알 수 있듯이 무언가의 사이에서 ‘**대리**’ 역할을 수행함을 예측할 수 있다. 스프링의 많은 동작 중에서 무엇을 대신할까? 바로 **‘`호출`’**이다.

클라이언트에서 서버를 직접 호출하고 처리 결과를 받는 것을 **직접 호출**이라 한다.

클라이언트에서 서버를 직접 호출하는 것이 아니라 어떠한 **대리자**를 통해 간접적으로 처리하고 결과를 받는 것을 **간접 호출**이라 한다.

- Client → Server = 직접 호출
- Client → Proxy → Server = 간접 호출

프록시는 Client와 Server의 중간에 있기 때문에 여러가지 일을 수행할 수 있다

- 권한에 따른 접근 차단, 캐싱, 지연로딩을 수행하는 **접근 제어**
- 서버의 기능에 다른 기능까지 추가해주는 **부가 기능 추가** (요청, 응답값을 변형해주거나 로그 기록 등)
- 대리자가 또 다른 대리자를 호출하는 **프록시 체인**

## 프록시 패턴과 데코레이터 패턴

두 패턴 모두 프록시를 이용하는 방법이다. 이 둘을 사용 의도에 따라서 구분한다

- 프록시 패턴 : **접근 제어**가 목적 → (맨 위에서 동시성 문제를 막아준다는 것이 이러한 기능 덕분)
- 데코레이터 패턴 : **새로운 기능 추가**가 목적

## 프록시 패턴 예제

### 😀**CacheProxy**

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d77615a5-febe-49b3-89e8-f56c576a9a1a/Untitled.png)

### Subject 인터페이스

```java
public interface Subject {
	String operation();
}
```

### RealSubject 구현 클래스

```java
@Slf4j
public class RealSubject implements Subject{
    @Override
    public String operation() {
        log.info("실제 객체 호출");
        sleep(1000);
        return "data";
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

### ProxyPatternClient

```java
public class ProxyPatternClient {
    private Subject subject;

    public ProxyPatternClient(Subject subject) {
        this.subject = subject;
    }

    public void execute(){
        subject.operation();
    }
}
```

### 실행

```java
void noProxyTest() {
	RealSubject realSubject = new RealSubject();
	ProxyPatternClient client = new ProxyPatternClient(realSubject);
	client.execute();
	client.execute();
	client.execute();
}

//실행 결과
RealSubject - 실제 객체 호출
RealSubject - 실제 객체 호출
RealSubject - 실제 객체 호출
```

변하지 않는 데이터라면 캐싱해두는 것이 성능상 좋을 테이니, 프록시 패턴의 주요 기능인 **접근 제어를 사용해보자**

### CacheProxy

```java
@Slf4j
public class CacheProxy implements Subject{
    private Subject target;
    private String cacheValue;

    public CacheProxy(Subject target) {
        this.target = target;
    }

    @Override
    public String operation() {
      log.info("프록시 호출");
      if(cacheValue==null){
          cacheValue = target.operation();
      }
      return cacheValue;
    }
}
```

- 실제 서버와 동일하게 `Subject`를 구현한다.
- 프록시는 실제 서버를 호출해야 한다. 따라서 실제 객체의 참조를 가지고 있어야 하는데, 이렇게 프록시가 호출하는 대상을 `target`이라 한다.
- `operation()`은 내부에 저장된 값이 있으면 바로 리턴(캐싱), 없으면 실제 서버를 호출해 리턴해주는 로직이다.

### 실행

```java
void cacheProxyTest() {
	Subject realSubject = new RealSubject();
	Subject cacheProxy = new CacheProxy(realSubject);
	ProxyPatternClient client = new ProxyPatternClient(cacheProxy);
	client.execute();
	client.execute();
	client.execute();
}

//실행 결과
CacheProxy - 프록시 호출
RealSubject - 실제 객체 호출
CacheProxy - 프록시 호출
CacheProxy - 프록시 호출
```

**클라이언트의 코드는 하나도 수정하지 않고 프록시 패턴을 적용**했다. 캐싱을 위해 실제 서버는 단 한 번만 호출이 됐고, 그 외의 호출은 모두 프록시에서 즉시 리턴했다. 이러한 방법이 `AOP`를 **프록시 패턴**으로 구현하는 방법이다.

하지만, 위 방법은 프록시 객체에 중복코드가 발생할 수 있고, 다른 클래스에서도 동일한 기능을 사용하고자 할 때, 매번 코딩을 해줘야하는 부분에서 효율적이지 못하다.

이런 문제를 핵결해주는 것이 런타임 시 동적으로 프록시 객체를 만들어주는 것인데, 그것이 **스프링 AOP 이다.**

내가 원래 등록해야 하는 기존의 클래스를 Bean에 등록하는 것이 아니라 기존의 클래스를 통해 프록시 클래스가 **동적으로** 생성되고 그 객체를 Bean에 등록하여 가져다 쓰는 것이 스프링 AOP 작동 방식인 것!

알게모르게 우리는 계속해서 이러한 `AOP`를 사용해왔는데 바로 `@Transactional`이 대표적인 예이다. 레포지터리 내에서 데이터를 가져올 때 Transactional 내부에서의 begin(), commit(), rollback() 등을 공통된 비지니스로 로직으로 인식하고 공통 로직이 구현된 프록시가 생성되어 Bean에 등록되는 것이다.

### 😀데코레이터 패턴

위에서 작성한 코드를 조금 수정해서 데코레이터 패턴과 프록시 패턴을 함께 사용하는 프록시 체인을 만들어보자

- Client → MessageDecorator → CacheProxy → Server

```java
@Slf4j
public class MessageDecorator implements Subject {
	private Subject subject;
	public MessageDecorator(Subject subject) {
		this.subject = subject;
	}
    
	@Override
	public String operation() {
		log.info("MessageDecorator 실행");
		String result = subject.operation();
		String decoResult = "*****" + result + "*****";
		log.info("MessageDecorator 꾸미기 적용 전={}, 적용 후={}", result, decoResult);
		return decoResult;
	}
}
```

### 실행

```java
void decorator() {
	Subject realSubject = new RealSubject();
	Subject cacheProxy = new CacheProxy(realSubject);
	Subject MessageDecorator = new MessageDecorator(cacheProxy);
	ProxyPatternClient client = new ProxyPatternClient(MessageDecorator);
	client.execute();
}

//실행 결과
MessageDecorator - MessageDecorator 실행
CacheProxy - 프록시 호출
RealSubject - 실제 객체 호출
MessageDecorator - MessageDecorator 꾸미기 적용 전=data, 적용 후=*****data*****
```

데코레이터 패턴과 프록시 체인이 되는 것을 확인할 수 있다.



### Reference

https://velog.io/@gmtmoney2357/디자인-패턴-프록시-패턴Proxy-Pattern-데코레이터-패턴Decorator-Pattern