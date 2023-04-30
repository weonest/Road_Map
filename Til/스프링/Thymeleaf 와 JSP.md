# Thymeleaf 와 JSP

우선 타임리프와 JSP에 앞서 템플릿 엔진이란 무엇일까?

- 템플릿 양식 (html)과 데이터 모델(DB)에 따른 입력 자료를 결합해서 문서를 출력하는 소프트웨어를 템플릿 엔진이라고 한다
- 즉, View를 담당하는 html코드와 DB Logic Code를 따로 분리해서 합쳐주는 기능을 하는 것이다
- 그리고 두 가지의 종류로 나뉘는데, 하나는 **서버사이드 템플릿 엔진**, 하나는 **클라이언트 사이드 템플릿 엔진**이다

### 서버사이드 템플릿 엔진이란?

- **서버에셔 가져온 데이터를 미리 만들어진 템플릿에 넣어서 html을 완성**시키고 클라이언트에게 전달한다. 예로는 Thymeleaf, JSP가 있다
- html 코드에서 고정적으로 사용되는 부분은 템플릿으로 만들어두고 동적으로 생성되는 부분만 템플릿 특정 장소에 끼워 넣는 방식으로 동작할 수 있도록 해준다.

![https://velog.velcdn.com/images/hi_potato/post/03a0b91f-ff0b-4259-89dc-5060369c658a/image.png](https://velog.velcdn.com/images/hi_potato/post/03a0b91f-ff0b-4259-89dc-5060369c658a/image.png)

### 동작 과정

1. 클라이언트의 요청을 받는다
2. 필요한 데이터 DB나 API에서 가져온다
3. 미리 정의된 Template에 해당 데이터를 배치한다
4. 서버에서 HTML(데이터가 반영된 Template)을 그린다
5. 해당 HTML을 클라이언트에 전달한다

### 클라이언트 사이드 템플릿 엔진이란?

- HTML 형태로 코드를 작성할 수 있으며, 동적으로 DOM (Document Object Model)을 그리게 해주는 역할이다.
- 데이터를 받아서 DOM 객체에 동적으로 그려주는 프로세스를 담당하고 있다

![https://velog.velcdn.com/images/hi_potato/post/1ce0b1f9-1012-41d1-9ecd-81d48fc540b2/image.png](https://velog.velcdn.com/images/hi_potato/post/1ce0b1f9-1012-41d1-9ecd-81d48fc540b2/image.png)

### 동작과정

1. 클라이언트에서 공통적인 프레임을 미리 Template으로 만든다
2. 서버에서 필요한 데이터를 받는다
3. 데이터를 Template에 배치하고 DOM 객체에 동적으로 그려준다

URL이 바뀌어도 HTML을 다시 내려받지 않고 클라이언트에서 알아서 그리기 때문에 주로 단일 화면에서 화면이 변경되는 경우에 사용된다. 대표적으로 react나 vue.js가 있다

### 타임리프와 JSP의 차이

1. JSP는 Servlet 형태로 변환되어 실행된다. 서블릿은 자바소스라 jsp에서 자바코드를 사용하는 것이 가능해지는데 그렇다보니 view에 비지니스 로직이 포함되어 복잡하고 무거워지게 된다.

   반면, **타임리프는 서블릿으로 변환되지 않다보니 비지니스 로직이 완전히 분리가 된다는 장점**이 있다.

2. JSP는 JAR 패키징이 불가하고, WAR 패키징만이 가능하다. war는 jar와 달리 웹 서버나 WAS가 필요하고, web-inf 디렉토리에서 사전 정의된 구조만을 사용한다. 그래서 jar 패키징이 가능한 thymeleaf는 was 없이도 브라우저에 직접 띄울 수가 있으니 훨씬 편리하다고 볼 수 있다.

### JSP와 Servlet의 차이

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5ae1df34-02d7-4ca3-938e-374cabfe4ffd/Untitled.png)

### 템플릿 엔진의 장점

1. 많은 코드를 줄일 수 있다
2. 재사용성이 높다
3. 유지보수에 용이하다

## 타임리프를 사용한 이유

spring-boot-starter-web에 포함된 WAS = Tomcat은 JSP 엔진을 포함하고 있지 않는다. JSP를 사용하기 위해서 추가적인 설정이 필요하고, 스프링 부트가 공식적으로 JSP보다 Thymeleaf 사용을 권장하고 있다. 또한 Thymeleaf는 html과 비지니스 자바 코드의 분리가 가능하기 때문