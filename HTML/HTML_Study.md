# HTML



[사전지식](https://www.notion.so/ab8ead10ef7d48f3b3b39fc65e72e84d)



## 기술소개

- HTML = Hyper ext Markup Language

  HyperText = 하이퍼 텍스트를 가장 중요한 특징으로 하는 ( ‘링크’ )

  Markup = 마크업이라는 형식을 가진

  **Language** = 컴퓨터 프로그래밍 언어

- Language 부터 살펴보면, ‘언어’의 핵심은 ‘**약속**’에 있다.

  

## 기본문법 (태그)

- 메모장 > 내용 입력 후 다른 이름으로 파일 저장 > a.html (파일 형식 : 모든파일, UTF - 8) 저장

- <strong> ~ </strong> 문자 강조

  시작태그       닫히는 태그

- HTML은 프로그래밍 언어기 때문에 메모장에서 “엔터”로 줄바꿈을 해도 적용되지 않음. 이를 위해서는

  <h1> ~ </h1>태그를 사용할 필요가 있음. (숫자는 Heading 의 크기) 

## 하피어텍스트와 속성

- 링크 태크 `<a href =**url**> ~ </a>` * 링크가 현재 페이지에서 열린다
- 새로운 페이지에서 열고 싶으면 `<a href=url target=**”_blank”**> ~ </a>` * target과 속성 위치 상관없이 작동한다.
- 툴팁 표시 `title=”text”`
- a 라는 태그가 얼마나 간단하고 중요한 태그인지 알 수 있다. (Anchor의 a)

GML > SGML > SGMLguid > HTML 순으로 언어가 발전함.

- 맥에서 텍스트 에디터 사용시 주의 사항

  ‘스마트 인용 체크’를 해제해야 “” 따옴표가 바뀌지 않음

  

## 리스트와 태그의 중첩

- <li> ~ </li> list 태그 = 각 항목을 리트스화 시킴

- <ul> ~ </ul> unordered 태그 = 각 리스트를 그룹핑 시킴

  여기서 중요한 것은 태그 안에 태그를 사용할 수 있다는 것. 하지만, 태그가 많아지면 가독성이 낮아짐. 코드처럼 들여쓰기를 하는 게 좋다.

- <ol> ~ </ol> ordered 태그 = 순서를 매겨서 리스트화 시킴

## 문서의 구조

- html 열고 주소를 변경해서 직접 파일을 열수도 있음

- <title> ~ </title> 태그

- 글씨가 깨지는 경우 `<meta charset = “utf-8”>` 를 주면 해결 가능

- <title> 이나 <meta~~> 태그는 웹브라우저의 본문에 해당하지 않는 태그이다. 

  문서를 수식하는 부가적인 정보에 해당

  하는 태그이다.

  - 본문이란? 말 그대로 텍스트 내용이 아닌 웹 구성의 본문

- <head>와 <body> 태그를 통해 본문과 본문이 아닌 태그들을 구분한다 > 전체 범위의 <html> 태그가 있다.

## DOCTYPE

- <!DOCTYPE html>

  **Doc**ument **type** declaration  = 문서 타입 선언

  

## 웹사이트 만들기

- 파일 항목들을 세분화 하기 전에 링크를 먼저 만들어두고 파일을 만드는 것이 좋다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8d466c88-8c01-4244-be49-6e74b7e9cf17/Untitled.png)

> 링크 url 태그를 먼저 생성 후 같은 파일명을 가진 html을 생성하여 작업하면 더욱 편하다.

- 이번 작업을 통해 어떤 태그가 존재하고 무엇을 모르는지 파악이 가능하게 되었으므로, 검색하는 법을 터득하여 활용할 수 있다.

  

## HTML의 변천사와 통계

- html 통계 https://www.advancedwebranking.com/seo/html-study/
- html 연대기 http://www.martinrinehart.com/frontend-engineering/engineers/html/html-tag-history.html