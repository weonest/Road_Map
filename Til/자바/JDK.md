# JDK

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/cb44732b-3d53-48e1-bf65-5d5612f40841/Untitled.png)

## Java Development Kit 자바 개발 키드

- 개발자들이 Java로 프로그램을 만들 수 있도록 다양한 기능을 제공하는 키트
- 컴파일러, JRE, JVM 등의 도구들을 가지고 있다
- 일반 사용자들이 자바로 만든 프로그램을 실행만 해보고 싶다면 JRE만 설치해도 되지만, 자바로 무엇인가를 만들어보고 싶다면 JDK를 설치해야 한다

### JDK 구성

- javac = 자바 컴파일러 ( 자바 소스파일 ⇒ 바이트코드로 변환)
- java = javac가 만든 클래스 파일을 해석 및 실행
- jdb = 자바 디버깅 툴
- JRE
  - Java Runtime Environment 자바 런타임 환경
  - 자바 코드를 실행하기 위한 도구들
  - **자바 클래스 라이브러리/JVM/자바 클래스 로더**를 가지고 있음
  - 작성된 자바 코드를 JVM에게 넘겨 실행시켜줌
  - 즉, JRE는 JVM이 원활히 작동할 수 있게 환경을 맞춰주는 역할
- JVM
  - Java Virtual Machine 자바 가상 머신
  - Java가 실제로 동작하는 가상 환경
  - 자바 프로그램이 다양한 OS 혹은 기기에서도 원활히 실행될 수 있도록 함
  - 메모리를 효율적으로 관리해주며 이를 **Garbage Collection** 이라 칭함

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9df3765d-5e91-4a7a-aff8-4b88d1c32036/Untitled.png)

## JDK 종류

- Java SE
  - Java Standard Edtion
  - 표준 에디션의 자바 플랫폼. 자바 언어의 핵심 기능을 제공
  - 주요 패키지는 java.lang.*, [java.io](http://java.io).*, java.util.*, java.awt.*, javax.rmi.*, [javax.net](http://javax.net).*
- Java EE
  - Java Enterprise Edition
  - JavaSE에 웹 애플리케이션 서버에서 동작하는 기능을 추가한 플랫폼
  - 즉, 서버 측 개발을 하기 위해 필요
  - JSP, Servlet, JDBC 등 기업용 애플리케이션을 개발하는데 필요한 다양한 것들이 포함된 플랫폼
  - 이 스펙에 따라 제품을 구현한 것을 WAS ( Web Application Server)라고 함
- Java ME
  - Java Mircro Edition
  - 임베디드 기기들에서 구동되기 위한 환경을 제공하는 API를 모아둔 플랫폼
  - 제한된 자원을 가진 휴대전화, PDA 등에서 Java 프로그래밍 언어를 지원하기 위해 만듬

출처 : https://pythontoomuchinformation.tistory.com/277