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

SpringBoot 프로젝트를 생성하면 @SpringBootApplication 애노테이션이 붙어있는 클래스가 생성되는데, 이 애노테이션은 내부적으로 @ComponentScan 애노테이션을 사용한다.

이 애노테이션은 어디서부터 컴포넌트를 찾아볼 것인지 알려주는 역할을 한다. @ComponentScan이 붙어있는 클래스가 있는 패키지에서부터 모든 하위 패키지의 모든 클래스를 훑어보며 @Component 애노테이션(또는 포함하는 애노테이션)이 붙은 클래스를 찾는다.

Spring이 IoC 컨테이너를 만들때 위와 같은 과정을 거쳐 빈으로 등록해주는 것이다.

### 2. Bean Configuration File에 직접 등록하는 방법

@Configuration과 @Bean 애노테이션을 이용하여 Bean을 등록할 수 있다. @Configuration을 이용하면 Spring Project에서의 Configuration 역할을 하는 Class를 지정할 수 있다. 해당 File 하위에 Bean으로 등록하고자 하는 Class에 @Bean 애노테이션을 사용해주면 간단하게 Bean을 등록할 수 있다.

@Configuration 애노테이션을 보면 이 애노테이션도 @Componet를 사용하기 때문에 컴포넌트 스캔 대상이 되고 그에 따라 Bean 설정파일이 읽힐 때 그 안에 정의한 Bean 들이 IoC컨테이너 등록되는 것이다.