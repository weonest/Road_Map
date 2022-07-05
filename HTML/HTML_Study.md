------

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

### a 태그 정리

------

**속성**

- href : 링크 주소를 지정합니다.
- target : 링크를 어떤 프레임에 열것인지를 결정합니다.
  - _blank : 새창에서 엽니다.
  - _parent : 부모창에 엽니다.
  - _self : 자신의 창에 엽니다
  - _top : 화면이 여러 프레임으로 이루어져있는 경우 모든 프레임을 지우고 전체 화면에 엽니다.
  - [name] : 지정된 이름에 링크를 엽니다.

**링크의 상태**

아래 상태들은 순서대로 정의해야 의도한 대로 스타일을 적용할 수 있습니다. 만약 active를 hover 보다 먼저 정의했다면 hover가 active를 오버라이드 하여 active 상대를 볼 수가 없게 됩니다.

- link : 기본적인 링크 상태로 아직 방문한적이 없거나 아무런 행동도 하기 전인 상태를 말합니다.
- visited : 방문한 적이 있는 링크의 상태를 말합니다.
- hover : 마우스 커서가 링크위에 올라가 있느 상태를 말합니다.
- focus : 링크에 키보드등을 이용해서 포커스가 위치해있는 상태를 말합니다.
- active : 마우스를 눌렀다가 놓는 동안의 상태를 말합니다.

**예제**

기본적으로 다음과 같이 사용할 수 있습니다.

```html
<nav>
    <ul>
        <li> <a href="/">Home</a> </li>
        <li> <a href="/news">News</a> </li>
        <li> <a href="/product">Product</a> </li>
        <li> <a>Examples</a> </li>
        <li> <a href="/legal">Legal</a> </li>
        <li> <a href="/blog" target="_blank">Blog</a></li>        
    </ul>
</nav>
```

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

> 링크 url 태그를 먼저 생성 후 같은 파일명을 가진 html을 생성하여      작업하면 더욱 편하다.

- 이번 작업을 통해 어떤 태그가 존재하고 무엇을 모르는지 파악이 가능하게 되었으므로, 검색하는 법을 터득하여 활용할 수 있다.

## HTML의 변천사와 통계

- html 통계 https://www.advancedwebranking.com/seo/html-study/
- html 연대기 http://www.martinrinehart.com/frontend-engineering/engineers/html/html-tag-history.html

## 단락 - P

- paragraph의 줄임말로 단락을 표현할 때 사용. 줄 바꿈의 간격이 고정임.

```html
<html>
    <head><meta charset="utf-8"></head>
    <body>
 
<p>HyperText Markup Language, commonly referred to as HTML, is the standard markup language used to create web pages. Along with CSS, and JavaScript, HTML is a cornerstone technology, used by most websites to create visually engaging webpages, user interfaces for web applications, and user interfaces for many mobile applications.[1] Web browsers can read HTML files and render them into visible or audible web pages. HTML describes the structure of a website semantically along with cues for presentation, making it a markup language, rather than a programming language.</p>
 
<p>HTML elements form the building blocks of all websites. HTML allows images and objects to be embedded and can be used to create interactive forms. It provides a means to create structured documents by denoting structural semantics for text such as headings, paragraphs, lists, links, quotes and other items.</p>
 
<p>The language is written in the form of HTML elements consisting of tags enclosed in angle brackets . Browsers do not display the HTML tags and scripts, but use them to interpret the content of the page.</p>
    </body>
</html>
```

## 줄바꿈 - <br>

- 새로운 행에서부터 입력이 시작되도록 함. A forced line-break의 줄임말

```html
<html>
<head><meta charset="utf-8"></head>
<body>
HyperText Markup Language, commonly referred to as HTML, is the standard markup language used to create web pages. Along with CSS, and JavaScript, HTML is a cornerstone technology, used by most websites to create visually engaging webpages, user interfaces for web applications, and user interfaces for many mobile applications.[1] Web browsers can read HTML files and render them into visible or audible web pages. HTML describes the structure of a website semantically along with cues for presentation, making it a markup language, rather than a programming language.<br><br><br>
 
HTML elements form the building blocks of all websites. HTML allows images and objects to be embedded and can be used to create interactive forms. It provides a means to create structured documents by denoting structural semantics for text such as headings, paragraphs, lists, links, quotes and other items.<br><br><br>
 
The language is written in the form of HTML elements consisting of tags enclosed in angle brackets. Browsers do not display the HTML tags and scripts, but use them to interpret the content of the page<br><br><br>
</body>
</html>

//우측 끝에 <br><br><br>
```

## 이미지 - img

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b4f318af-ace0-40b6-8576-d220fa3e3db9/Untitled.png)

- ATOM을 사용하여 “img123”이라는 파일명의 이미지 파일을 적용.

- 만약 파일명이 바뀌거나 파일이 깨져 이미지 표시가 불가능한 경우

  Alternative text (**alt**) 태그를 이용하여 텍스트로 대체가 가능하다.

- 이미지 파일의 **title** 태그는 이미지 위에 마우스를 올려 놓으면 툴팁을 표시해준다.