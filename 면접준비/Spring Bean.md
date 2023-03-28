# Spring Bean

------

https://melonicedlatte.com/2021/07/11/232800.html

## 스프링 빈이란?

Spring IOC 컨테이너가 관리하는 자바 객체를 Bean 이라고 부른다.

[IOC (Inversion Of Control), DI (Dependecy Injection)](https://www.notion.so/IOC-Inversion-Of-Control-DI-Dependecy-Injection-9ba90fbd3c7b4420b7997a9034820ab1)

우리가 알던 기존의 Java Programming 에서는 Class를 생성하고 new 를 입력하여 원하는 객체를 직접 생성한 후에 사용했었다. 하지만 Spring 에서는 직접 new를 이용하여 생성한 객체가 아니라, Spring에 의하여 관리당하는 자바 객체(Bean)을 얻기 위하여 `ApplicationContext.getBean()` 과 같은 메서드를 사용하여 Spring 에서 직접 자바 객체를 얻어 사용한다.

## Spring Bean을 Spring IoC Container에 등록하는 방법

### 1. @Component 애노테이션을 사용하는 방법

Java에서 Annotation은 자바 소스 코드에 추가하여 사용할 수 있는 메타데이터의 일종이다. 소스코드에 추가하면 단순 주석의 기능을 하는 것이 아니라 특별한 기능을 사용할 수 있게 된다.

Srping 에서는 여러 가지 Annotaion을 사용하지만, Bean을 등록하기 위해서는 `@Component` 애노테이션을 사용한다. 이 애노테이션이 등록되어 있는 경우에는 Spring이 Annotaion을 확인하고 자체적으로 Bean으로 등록한다.

### 2. Bean Configuration File에 직접 등록하는 방법

@Configuration과 @Bean 애노테이션을 이용하여 Bean을 등록할 수 있다. @Configuration을 이용하면 Spring Project에서의 Configuration 역할을 하는 Class를 지정할 수 있다. 해당 File 하위에 Bean으로 등록하고자 하는 Class에 @Bean 애노테이션을 사용해주면 간단하게 Bean을 등록할 수 있다.