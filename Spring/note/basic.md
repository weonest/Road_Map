# 기초

- 스프링 완전 정복 로드맵

  스프링 입문

  스프링 핵심 원리

  스프링 웹 MVC

  스프링 DB 데이터 접근 기술

  실전! 스프링 부트

스프링 부트 스타터 사이트로 이동해서 스프링 프로젝트 생성

https://start.spring.io

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3a0d58b8-5f97-4bc7-ab1f-03b119ef9529/Untitled.png)

Maven, Gradle (요즘 추세는 Gradle) // 정확히 무슨 개념인지?

Artifact : 프로젝트 명

Dependencies : 가져올 라이브러리

tomcat 서버를 내장하고 있어서 자동으로 서버를 열 수 있게 된다

- Model은 HashMap 형태를 갖고 있어 key값과 value값처럼 사용할 수 있다.

  addAttribute는 Map의 put과 같은 기능으로 해당 모델에 원하는 속성과 그것에 대한 값을 주어 전달할 View에 데이터를 전달할 수 있다.