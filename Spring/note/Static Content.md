# 정적 컨텐츠

- 스프링부트 정적 컨텐츠 기능 :https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-static-content

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9d4608e9-ce19-4188-add8-e5e5d6333f84/Untitled.png)

- 컨트롤러가 우선적으로 hello-static.html을 찾은 후 후에 static 안에서 hello-static.html을 찾게된다. (View 환경설정 내용)

정적 컨텐츠 : 파일을 그대로 클라이언트에게 그대로 전달하는 것

MVC와 템플릿 엔진 : 서버에서 변형해서 전달하는 것

API : Json이라는 데이터 구조 포맷으로 전달하는 것 (보통 서버끼리)