# View 환경설정

## Welcome Page 만들기

- 스프링 부트가 제공하는 Weclome Page 기능
  - ``static/index.html` 을 올려두면 Welcome Page 기능을 제공한다
  - https://docs.spring.io/spring-boot/docs/current/reference/html/web.html#web

## thymeleaf 템플릿 엔진

- thymeleaf 공식 사이트: https://www.thymeleaf.org/
- 스프링 공식 튜토리얼: https://spring.io/guides/gs/serving-web-content/
- 스프링부트 메뉴얼:  https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-template-engines

### 동작 환경 그림

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ba0f6864-6299-4478-823e-21b64106d828/Untitled.png)

- 컨트롤러에서 리턴 값으로 문자를 반환하여 뷰 리졸버 (``viewResolverè`) 가 화면을 찾아서 처리한다.

  - 스프링 부트 템플릿엔진 기본 viewName 매핑
  - `resources:templates/` + {ViewName} +`html`

  즉, Spring이 자동으로 src/main/resources/templates에서 retun 값을 찾아 View를 반환해준다.

  참고로 static에 들어가는 정적 contents들은 따로 controller가 없어도 알아서 찾아준다. 정확히는 스프링 컨테이너에 해당 이름을 사용하는(GetMapping 해주는) 컨트롤러가 없다면 static에서 찾도록 설계되어 있다.

> 참고!
>
> `spring-boot-devtools` 라이브러리를 추가하면 `html`파일을 컴파일만 해주면 서버 재시작 없이 View 파일 변경이 가능하다. (검색)