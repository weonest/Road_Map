# CSS

------

[사전지식](https://www.notion.so/cf5eddbcd29147b29d8300a8add87825)

## HTML과 CSS가 만나는 법

```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    **<style media="screen">
      h2{color:blue}
    </style>**
  </head>
  <body>
    **<h1 style="color:red">Hello World</h1>**
    <h2>Hello World</h2>
  </body>
</html> // style 태그 이용과 h1의 style 속성 이용한 2가지 방법
<h1 style="**color:red**">
```

- color : red 까지가 CSS의 문법이다

## 선택자와 선언

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
      <style>
        li{
          color:red;
          text-decoration:underline
        }
      </style>
  </head>
  <body>
    <ul>
      <li>Html</li>
      <li>CSS</li>
      <li>JavaScript</li>
    </ul>
  </body>
</html>
```

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b646d709-787f-4fa0-8202-5e7277fca1d8/Untitled.png)

- `;` 세미콜론은 선언 구분자

## 선택자의 종류

### 선택자의 타입들

- 태그 선택자
- 클래스 선택자
- 아이디 선택자

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
      <style>
        li{
          color:red;
          text-decoration:underline
        }
        **#s**elect{
          font-size:50px;
        }
      </style>
  </head>
  <body>
    <ul>
      <li>Html</li>
      <li **id="select"**>CSS</li>
      <li>JavaScript</li>
    </ul>
  </body>
</html>
```

- 여기서 사용한 id의 속성과 select값은 html 언어이다.

  해당 태그를 CSSs안에서 호출할 때에는 위와 같이 앞에 `#`을 붙여준다.

## 부모 자식 선택자

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title></title>
    <style media="screen">
      ul li{
        color:red;
      }
      **#lecture**>li{     // ol이 두 번 나오므로 첫 번째 ol에 id 값을 주어 사용
        border:1px solid red;
        **color:blue; // 조건에 lecture를 주었지만 모든 ol이 적용되는 것은 추후에 배울 상속에서 다룸**
      }
      **ul, ol{    // ul과 ol 모두에게 배경색을 줌. (, 사용)**
        background-color: powderblue;
      }
    </style>
  </head>
  <body>
    <ul>
      <li>HTML</li>
      <li>CSS</li>
      <li>JavaScrpit</li>
    </ul>
    **<ol id="lecture">  // 첫 번째 ol**
      <li>HTML</li>
      <li>CSS
        **<ol>      // 두 번째 ol**
          <li>selector</li>
          <li>declaration</li>
        </ol>
      </li>
      <li>JavaScript</li>
    </ol>
  </body>
</html>
```

- body 태그 안에서 ul태그 속 li와 ol태그 속 li로 나뉘는데 ul 태그 속 li에만 style을 주고 싶은 경우에는 위와 같이 **ul li** { … 으로 가능하다.
- style 태그 안에서 같은 ol태그를 구분하는 방법은 개별적으로 id 속성을 주어 적용시키는 방법이 있다.
- 복수의 태그에 동시적용을 원하면 `,`를 사용하여 적용할 수 있다.

## 선택자 공부 팁

http://flukeout.github.io/

주어진 그림에 해당하는 태그를 골라내는 능력을 기를 수 있음.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f43fd0ae-ba5d-46f7-916e-e70f0873e639/Untitled.png)

## font-size

font는 글꼴의 크기를 지정하는데, 주요 단위로는 px, em, rem이 있다.

- px : 모니터 상의 화소 하나의 크기에 대응하는 단위. 고정된 값이기 때문에 이해하기 쉽다. 사용자가 글꼴의 크기를 조정할 수 없기 때문에 가급적 사용을 하지 않는 것이 좋음.
- em : 부모 태그의 영향을 받는 상대적인 크기. 부모의 크기에 영향을 받기 때문에 파악하기가 어렵다. rem이 등장하면서 이 단위 역시 사용이 권장되지 않음
- rem : html 태그에 적용된 font-size의 영향을 받는다. html 태그의 폰트 크기에 따라서 상대적으로 크기가 결정되기 때문에 이해하기 쉽다. 가장 바람직한 태그

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <style media="screen">
      #px{font-size:16px;}  // 사용자 환경에서 변경 불가
      #rem{font-size:1rem;}  // 사용자 환경에서 변경 가능
    </style>
  </head>
  <body>
    <div>PX</div>
    <div>REM</div>
  </body>
</html>
```

## Color

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <style media="screen">
      h1{color:red;}
      h2{color:rgb(0,0,226);}
    </style>
  </head>
  <body>
    <h1>Hello world</h1>
    <h2 class="name">Geonhee</h2>
  </body>
</html>
```

- 16진수, RGB, 대표색상 속성 등을 활용하여 font-color 를 지정 가능하다.

## text-align (텍스트 정렬)

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <style media="screen">
      p{
        text-align: justify;
        border:1px solid gray;
      }
    </style>
  </head>
  <body>
    <p>
      Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis eu luctus nisl. Maecenas at eros nulla. Praesent aliquet, tellus vitae hendrerit consectetur, ligula neque venenatis diam, suscipit accumsan dui lectus at orci. Donec suscipit malesuada eros non tempor. Interdum et malesuada fames ac ante ipsum primis in faucibus. Quisque iaculis elit vitae urna aliquet, sit amet convallis mi egestas. Curabitur gravida, enim vel semper ornare, mi lectus dictum est, in egestas diam orci pellentesque orci. Proin cursus libero sit amet nisi porttitor, sit amet congue lacus tincidunt. Vestibulum dapibus quam non justo iaculis, sed aliquam leo tincidunt. Quisque sagittis diam id metus vestibulum, sit amet hendrerit mi lacinia. Nullam dignissim tortor finibus volutpat ultricies. Pellentesque non facilisis tortor.

Duis molestie libero tellus, eu mollis dolor hendrerit nec. Integer maximus rhoncus ex sed posuere. Aliquam quis semper leo. Aenean venenatis convallis turpis eu suscipit. Cras sit amet luctus est. Aenean consectetur, est nec tristique porta, turpis est semper mauris, in bibendum eros dui sit amet elit. Pellentesque iaculis blandit mi, at maximus massa auctor ut. Quisque at mollis enim. Ut in malesuada arcu, condimentum viverra tellus. Nam tincidunt mattis ipsum ut mattis. Quisque pharetra, nunc vel sollicitudin lacinia, ipsum odio dignissim tortor, at lobortis ipsum arcu ac diam. Nulla ut dui sit amet mauris lacinia vehicula. Sed fermentum metus eget lacus bibendum, sit amet iaculis diam fermentum. Quisque eget porta neque, eu laoreet enim.

Etiam interdum lobortis tellus eget volutpat. Quisque vel augue mi. Nunc urna sapien, malesuada at ligula ut, convallis ultricies erat. Duis rhoncus diam eget mollis tempus. Maecenas a sapien ut nulla vulputate dapibus. Nam a erat euismod, suscipit orci vitae, iaculis tortor. Fusce a odio semper, elementum massa in, blandit ipsum. Donec ipsum tortor, varius eget venenatis at, pharetra vel ipsum. Nulla in laoreet enim.

Mauris quis metus in arcu lobortis euismod et in erat. Maecenas iaculis libero metus, id pretium metus sodales in. Etiam et hendrerit dolor. In hendrerit ullamcorper augue. Phasellus mollis interdum euismod. Nam feugiat elit ante. Vestibulum ligula felis, eleifend nec finibus ac, fermentum nec ligula. Proin pellentesque, nisi vel tempus lobortis, ligula felis faucibus nulla, tempus venenatis libero ipsum congue quam.

Nunc consequat elit ut quam elementum, ut tincidunt neque feugiat. Donec at odio eget mi accumsan sollicitudin sed vel elit. Etiam ultrices lorem sapien, at sagittis neque dapibus id. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Cras sit amet lorem ornare, malesuada enim eu, varius risus. Nam laoreet orci at tincidunt vulputate. Nunc vitae tincidunt nisl. Nam ut turpis neque. Donec congue arcu in pretium posuere. Fusce rhoncus nibh ex, ut auctor felis pellentesque nec. Maecenas mauris nibh, ornare eu ultrices in, vestibulum eu ante. Curabitur ante sem, fermentum vel consectetur luctus, dictum vitae diam. Morbi elementum lobortis orci, a faucibus dolor egestas in. Integer pharetra sem in convallis pretium.
    </p>
  </body>
</html>
```

- text-align : ‘속성’
  - center, legt, right, justify(양쪽에 알맞게)
- border, solid gray (테두리 속성)

## font

```html
<!DOCTYPE html>
<html>
  <head>
    <style>
      #type1{
        font-size:5rem;
        font-family: arial, verdana, "Helvetica Neue", serif;
        font-weight: bold;
        line-height: 2;
      }
      **#type2{
        font:bold 5rem/2 arial, verdana, "Helvetica Neue", serif;**
      }
    </style>
  </head>
  <body>
    <p id="type1">
      Hello world<br>
      Hello world
    </p>
    <p id="type2">
      Hello world<br>
      Hello world
    </p>
  </body>
</html>
```

- font-family : 서체 지정하는 태그
- font-weight : 폰트의 두께. 대체로 bold만 쓰임
- font-height : 행과 행 사이의 간격을 지정. 기본 값은 normal로 수치로는 1.2에 해당

## **font**

폰트와 관련된 여러 속성을 축약형으로 표현하는 속성. 형식은 아래와 같다. 순서를 지켜서 기술해야 한다.

font: font-style font-variant font-weight **font-size**/line-height **font-family**|caption|icon|menu|message-box|small-caption|status-bar|initial|inherit;

ex) **font:bold 5rem/2 arial, verdana, "Helvetica Neue", serif;**

## 웹폰트

```html
<!DOCTYPE html>
<html>
  <head>
    <link href="<https://fonts.googleapis.com/css?family=Indie+Flower|Londrina+Outline|Open+Sans+Condensed:300>" rel="stylesheet">
    <style>
      #font1{
        font-family: 'Open Sans Condensed', sans-serif;
      }
      #font2{
        font-family: 'Indie Flower', cursive;
      }
    </style>
  </head>
  <body>
    <p id="font1">
      Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce facilisis lacus eu ex rhoncus pretium. Sed vestibulum risus pharetra, consequat nibh ac, ornare nunc. Nunc eu dui eget lorem aliquet finibus.
    </p>
    <p id="font2">
      Quisque nec arcu felis. Vestibulum gravida, augue eu facilisis tempus, neque erat tincidunt nunc, consequat ultrices felis urna eu augue. Nulla ut urna purus. Curabitur ultricies rutrum orci malesuada tempor.
    </p>
  </body>
</html>
```

- Google font를 이용하여 웹폰트 링크를 태그한 후, 사용하는 방법.
- style 태그 안에서 사용하는 것과 같이 폰트의 이름과 속성을 지정하여 사용.

## 상속

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <style media="screen">
      /*li{
        color:red;
      }
      h1{
        color:red;
      }*/
      html{
        color:red;
      }
      #select{color:black;}
      body{
        border:1px solid red;
      }
    </style>
  </head>
  <body>
    <h1>수업내용</h1>
    <ul>
      <li>html</li>
      <li>css</li>
      <li id="select">javascript</li>
    </ul>
  </body>
</html>
```

- 개별적으로 속성을 부여하는 것 보다 전체 상속 후 변경이 필요한 곳에만 속성을 부여하는 것이 편한 경우가 있다.
- color는 상속이 되지만, border는 상속되지 않음.
  - 상속이 유리한 경우에는 상속, 불리한 경우에는 상속x 로직.
    - 상속 여부에 관한 것은 검색해서 찾아 보는 게 편함

## Cascading Style Sheet

- 기본 우선순위 : 웹브라우저 < 사용자< 저자 (중첩의 우선순위)

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <style media="screen">
      li{color:red; !important}
      #idsel{color:blue;}
      #idsel{color:yellow;}
      .classsel{color:green;}
    </style>
  </head>
  <body>
    <ul>
      <li>html</li>
      <li id="idsel" class="classsel" style="color:powderblue" >css</li>
      <li>javascript</li>
    </ul>
    <ol>
      <ul>
        <li>style attribute</li>
        <li>id selector</li>
        <li>class selector</li>
        <li>tag selector</li>
      </ul>
    </ol>
  </body>
</html>
```

- style, id, class, tag 순으로 우선순위를 가진다. 더욱 명시적인 성질을 우선순위로 두어야 생산성이 높아지기 때문이다.
  - **! important** 속성을 추가하면 가장 높은 우선순위를 가질 수 있다. 가장 쉬운 방법이지만, 가장 좋은 방법이라고 할 수는 없으므로 우선순위를 잘 파악하고 사용할 수 있도록 하자.
- CSS 부분에서 `#idsel` 이 2번 나오는 경우에는 마지막 태그를 따라간다?

## inline

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <style media="screen">
      h1, a{border:1px solid red;}
			h1 { display : inline;}
    </style>
  </head>
  <body>
    <h1>Hello world</h1>
    안녕하세요. <a href="<https://localhost.php>">원건희</a>입니다.
  </body>
</html>
```

- h1 태그와 같이 화면 전체를 사용하는 태그를 block level element
- a 태그와 같이 화면의 일부를 차지하는 태그를 inline lever element
- display : inline or block 태그 속성을 통해 변경하는 것도 가능하다.

## Box model (박스모델) *중요

- **box model :** 웹 페이지에 표현될 때 태그의 부피감, 여백, 위치, 크기 등을 결정하는 것.

```html
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <style media="screen">
      p, a{/*border-width:10px;
          border-style: solid;
          border-color:red;*/
          border:10px dotted red;
          padding:60px;
          margin:40px;
        }
    </style>
  </head>
  <body>
    <p>
      Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
    </p>
    <p>
      Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
    </p>
    안녕하세요.<a href="<http://localhost.php>">생활코</a>입니다.
  </body>
</html>
```

- padding : 테두리와 테두리 안 내용의 간격 설정 (안주와 접시 테두리 사이의 간격 ㅋㅋ)
- margin : 테두리와 그 밖의 것에 대한 간격 설정 (접시와 접시 사이의 간격 ㅋㅋ)
- inline 요소는 기본적으로 width와 height 값이 무시되고, padding과 margin이 적용되어 있다.

## box-sizing

box-sizing은 박스의 크기를 화면에 표시하는 방식을 변경하는 속성이다. width와 height는 엘리먼트의 컨텐츠의 크기를 지정한다. 따라서 테두리가 있는 경우에는 테두리의 두께로 인해서 원하는 크기를 찾기가 어렵다. box-sizing 속성을 border-box로 지정하면 테두리를 포함한 크기를 지정할 수 있기 때문에 예측하기가 더 쉽다. 최근엔 모든 엘리먼트에 이 값을 지정하는 경우가 늘고 있다.

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <style media="screen">
      *{  // *은 모든 값에 적용한다는 뜻
        box-sizing:border-box;
      }
      div{
        margin:10px;
        width:150px;
      }
      #small{
        border:10px solid black;
      }
      #large{
        border:30px solid black;
      }
    </style>
  </head>
  <body>
    <div id="small">Hello</div>
    <div id="large">Hello</div>
  </body>
</html>
```

- CSS초기에는 컨텐츠의 크기값을 기준으로 했기 때문에 같은 width 값을 주더라도 테두리의 크기가 달라질 수 있다.
- box-sizing:border-box를 지정하면 예상하기 쉬운 결과를 도출해낼 수 있다.

## 마진겹침 현상

마진겹침(margin-collapsing) 현상은 상하 마진값이 어떤 상황에서 사라지는 현상을 의미한다.

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <style media="screen">
      h1{
        border:1px solid red;
        margin:100px;
      }
    </style>
  </head>
  <body>
    <h1>Hello world</h1>
    <h1>Hello world</h1>
  </body>
</html>
```

- 두 Hello world 사이의 간격에 대한 마진

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <style media="screen">
      #parent{
        /* border:1px solid tomato; */
        margin-top:100px;
      }
      #child{
        background-color: powderblue;
        margin-top:50px;
      }
    </style>
  </head>
  <body>
    <div id="parent">
      <div id="child">Hello world</div>
    </div>
  </body>
</html>
```

- 부모 element가 시각적인 요소가 없는 투명한 상태일 때, 부모의 margin값과 자식의 margin값에서 **큰 쪽의 margin 값이 자식 element의 위치 값**을 사용된다.

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <style media="screen">
      #empty{
        margin-top:50px;
        margin-bottom:100px;
        /* border:1px solid tomato; */
      }
      #normal{
        background-color: powderblue;
        margin-top:100px;
      }
    </style>
  </head>
  <body>
    <div id="empty"></div>
    <div id="normal">normal</div>
  </body>
</html>
```

- 시각적인 요소가 없다면 top과 bottom 중 더 큰 값을 margin 기준값으로 갖는다.

## Position

엘리먼트의 위치를 지정하는 4가지 방법이 있다.

- static
- relative
- absolute
- fixe

### static VS realtive

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <style media="screen">
      html{
        border:1px solid gray;
      }
      div{
        border:5px solid tomato;
        margin:10px;
      }
      #me{
        position: relative; or static
        left:100px;
        bottom:100px;
      }
    </style>
  </head>
  <body>
    <div id="other">other</div>
      <div id="parent">
         parent
         <div id="me">me</div>
      </div>
  </body>
</html>
```

- static은 기본값이다.
- position: relative; 를 통해 상대적, 정적인 위치를 정할 수 있다.
- postiion이 relaive일 때에 offset을 사용할 수 있다.

### absolute

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <style media="screen">
      #parent, #other{
        border:5px solid tomato;
      }
      #grand{
        position:relative;
      }
      #me{
        background-color: black;
        color:white;
        position: absolute;
        left:0;
        top:0;
      }
    </style>
  </head>
  <body>
    <div id="other">other</div>
    <div id="grand">
      grand
      <div id="parent">
         parent
         <div id="me">me</div>
      </div>
    </div>
  </body>
</html>
```

- absolute로 지정했을때 offset을 지정해주지 않으면 부모에 속했을 때 기대되는 위치를 기본값으로 하게된다.
- 어떠한 element를 absolute로 지정하면 더 이상 부모에 속하지 않게된다. 부모 자식 관계의 link가 끊긴다.
- 여기서 abosolute 포지션의 offset을 주지 않은 ‘me’는 **html을 기준**으로 (0, 0)위치에 오게되지만, **부모 이외의 새로운 종속관계가 등장**하면 (예시에서는 ‘grand’의 등장) **새로 등장한 존속관계를 기준**으로 (0, 0)에 위치하게 된다.

### fixed

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <style media="screen">
      #parent, #other{
        border:5px solid tomato;
      }
      #large{
        height:10000px;
        background-color: tomato;
      }
      #me{
        background-color: black;
        color:white;
        position: fixed;
        left:0;
        top:0;
      }
    </style>
  </head>
  <body>
    <div id="other">other</div>
      <div id="parent">
         parent
         <div id="me">me</div>
         <div id="large">large</div>
      </div>
    </div>
  </body>
</html>
```

- fixed : 스크롤을 내려도 위치를 고정시켜준다. (offset을 주지 않으면 html기준), 대체로 absolute와 성격이 비슷하다.

## flex

CSS의 flex는 엘리먼트들의 크기나 위치를 쉽게 잡아주는 도구이다. 지금까지 레이아웃에 관련된 다양한 속성들이 있었지만, 그리 효과적이지 않았다. flex를 이용하면 레이아웃을 매우 효과적으로 표현할 수 있다.

이전까지는 table로 레이아웃을 짜왔는데, 유지보수 측면이나 디자인 측면에서 상당히 비효율적이었다. 이후로 position과 float 등을 통해서도 레이아웃을 잡았었지만 flex가 등장하며..

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
  </head>
  <style media="screen">
    .container{
      background-color:powderblue;
      display:flex;
      flex-direction:column-reverse;
    }
    .item{
      background-color: tomato;
      color:white;
      border:1px solid white;
    }
  </style>
  <body>
     <!--div 태그는 block 엘리먼트이기에 화면 전체를 쓴다 -->
    <div class="container">
      <div class="item">1</div>
      <div class="item">2</div>
      <div class="item">3</div>
      <div class="item">4</div>
      <div class="item">5</div>
    </div>
  </body>
</html>
```

- flex 사용은 부모의 값에 display: flex;를 주는 게 시작이다.
- flex-direction:row-reverse; : 태그들을 오른쪽(행)으로 역순 정렬.
  - column-reverse; : 태그들을 아래로 역순으로(열) 정렬

### grow & shrink

아이템은 컨테이너의 크기에 따라서 작아지기도 하고 커지기도 한다. 작아지고 커지는 비율을 지정하는 방법이 grow & shrink다.

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
  </head>
  <style media="screen">
    .container{
      background-color:powderblue;
      display:flex;
      height:200px;
      flex-direction:row;
    }
    .item{
      background-color: tomato;
      color:white;
      border:1px solid white;
    }
    .item:nth-child(1){
      flex-basis:150px;
    }
    .item:nth-child(2){
      flex-basis:150px;
      flex-shrink: 0;
    }
  </style>
  <body>
     <!--div 태그는 block 엘리먼트이기에 화면 전체를 쓴다 -->
    <div class="container">
      <div class="item">1</div>
      <div class="item">2</div>
      <div class="item">3</div>
      <div class="item">4</div>
      <div class="item">5</div>
    </div>
  </body>
</html>
```

- nth-child(n) : 해당 클래스의 n번 째
- flex-basis: npx; 크기 픽셀
- flex-grow : 아이템들이 컨테이너의 여백을 균등하게 채움
  - 하나만 키우고 싶은 경우 cascading을 통해 우선순위를 활용하자
- flex-shrink : basis 값이 있을 때 크기가 줄어드는 기능

### Holy Grail Layout

Holy Grail은 성배라는 뜻이다. 많은 사람들이 성배를 찾기 위해서 노력했지만 찾지 못한 것처럼 많은 사람들이 아래와 같은 레이아웃을 만들기 위해서 노력했지만 완벽한 방법을 찾지 못했다. 이것에 비유해서 이런 레이아웃을 성배 레이아웃이라고 부르곤 한다. flex는 이러한 문제를 아주 간편하게 해결 가능하다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/48a88e93-c592-447d-8fd5-0648c210065f/Untitled.png)

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <style media="screen">

    </style>
  </head>
  <body>
    <div class="container">
        <header>
          <h1>생활건희</h1>
        </header>
        <section class="content">
          <nav>
            <ul>
              <li>html</li>
              <li>css</li>
              <li>javascript</li>
            </ul>
          </nav>
          <main>
            생활건희는 일반인을 위한 코딩 수업입니다.
          </main>
          <aside>
            AD
          </aside>
        </section>
        <footer>
          <a href="<https://opentutorials.org/course/1>">홈페이지</a>
        </footer>
    </div>
  </body>
</html>
```

성배 레이아웃을 사용하기 전 기초적인 틀

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <style media="screen">
      .container{
        display:flex;
        flex-direction: column;
        }
      header{
        border-bottom:1px solid gray;
        padding-left: 20px;
      }
      footer{
        border-top:1px solid gray;
        padding-left: 20px;
        text-align: center;
      }
      .content{
        display: flex;
      }
      .content nav{
        border-right: 1px solid gray;
      }
      .content aside{
        border-left: 1px solid gray;
      }
      nav, aside{
        flex-basis:150px;
        flex-shrink:0;
      }
      main{
        padding:10px;
      }
    </style>
  </head>
  <body>
    <div class="container">
        <header>
          <h1>생활건희</h1>
        </header>
        <section class="content">
          <nav>
            <ul>
              <li>html</li>
              <li>css</li>
              <li>javascript</li>
            </ul>
          </nav>
          <main>
            생활건희는 일반인을 위한 코딩 수업입니다.
          </main>
          <aside>
            AD
          </aside>
        </section>
        <footer>
          <a href="<https://opentutorials.org/course/1>">홈페이지</a>
        </footer>
    </div>
  </body>
</html>
```

성배 레이아웃

### flex의 다양한 속성들

https://codepen.io/enxaneta/pen/adLPwv 클론해보기?

## media query

화면의 종류와 크기에 따라서 디자인을 달리 줄 수 있는 CSS의 기능이다. 특히 최근의 트랜드인 반응형 디자인의 핵심 기술이라고 할 수 있다.

```html
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
      @media (max-width:600px){
        body{
          background-color: green;
        }
      }
      @media (max-width:500px){
        body{
          background-color: red;
        }
      }
      @media (min-width:601px){
        body{
          background-color: blue;
        }
      }
    </style>
  </head>
  <body>
    ~500px : red
    501~600px : green
    601px : blue
  </body>
</html>
```

- max-width : 500 이하, min-width : 500 이상
- 나중에 나온 코드가 우선순위가 높다 (cascading)
- 페이지 검사 (개발자모드) toggle device로 테스트를 해볼 수도 있다.
- **<meta name="viewport" content="width=device-width, initial-scale=1.0"> 장치에 맞게 설정**

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <style media="screen">
      .container{
        display:flex;
        flex-direction: column;
        }
      header{
        border-bottom:1px solid gray;
        padding-left: 20px;
      }
      footer{
        border-top:1px solid gray;
        padding-left: 20px;
        text-align: center;
      }
      .content{
        display: flex;
      }
      .content nav{
        border-right: 1px solid gray;
      }
      .content aside{
        border-left: 1px solid gray;
      }
      nav, aside{
        flex-basis:150px;
        flex-shrink:0;
      }
      @media(max-width: 500px){
        .content{
          flex-direction: column;
        }
        .content nav{
          border: none;
          flex-basis: auto;
      }
      .content aside{
          border: none;
          flex-basis: auto;
        }
        main{
          order:0;
        }
        nav{
          order:1;
        }
        aside{
          order:2;
          display: none;
        }
      }
      main{
        padding:10px;
      }
    </style>
  </head>
  <body>
    <div class="container">
        <header>
          <h1>생활건희</h1>
        </header>
        <section class="content">
          <nav>
            <ul>
              <li>html</li>
              <li>css</li>
              <li>javascript</li>
            </ul>
          </nav>
          <main>
            생활건희는 일반인을 위한 코딩 수업입니다.
          </main>
          <aside>
            AD
          </aside>
        </section>
        <footer>
          <a href="<https://opentutorials.org/course/1>">홈페이지</a>
        </footer>
    </div>
  </body>
</html>
```

(성배 레이아웃에 미디어쿼리 응용)

## flaot

```html
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <style media="screen">
      img{
        width:300px;
        float:right;
        margin:20px;
      }
      p{
        border:1px solid gray;
      }
    </style>
  </head>
  <body>
    <img src="images/img123.jpg" alt="">
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
    <p style="clear:both;">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
  </body>
</html>
```

- float : 이미지를 삽입할 때 위치를 결정하는 태그
- <p style=”clear:@@@”> : float를 무시하고 위치를 지정하는 태그

### float를 활용한 holy grail layout

```html
<!doctype html>
<html>
<head>
  <style>
    *{
      box-sizing:border-box;
    }
    .container{
      width:540px;
      border:1px solid gray;
      margin:auto;
    }
    header{
      border-bottom: 1px solid gray;
    }
    nav{
      float:left;
      width:120px;
      border-right:1px solid gray;
    }
    article{
      float:left;
      width:300px;
      border-left:1px solid gray;
      border-right:1px solid gray;
      margin-left:-1px;
    }
    aside{
      float:left;
      width:120px;
      border-left:1px solid gray;
      margin-left:-1px;
    }
    footer{
      clear:both;
      border-top:1px solid gray;
      text-align: center;
      padding:20px;
    }
  </style>
</head>
<body>
 <div class="container">
    <header>
    <h1>
      CSS
    </h1>
    </header>
    <nav>
      <ul>
        <li>position</li>
        <li>float</li>
        <li>flex</li>
      </ul>
    </nav>
    <article>
      <h2>float</h2>
      Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sit quae earum enim ab distinctio corrupti eius reprehenderit non, rerum ut nisi autem cum sint perferendis eum id velit, molestias nesciunt. Ullam dignissimos consequuntur explicabo id voluptas vel deleniti nesciunt veritatis iusto commodi, laudantium cumque vero deserunt laboriosam. Ea, quia est?
    </article>
    <aside>
      ad
    </aside>
    <footer>
      copyleft
    </footer>
 </div>

</body>
</html>
```

- 확실히 flex로 성배 레이아웃을 짜는 것이 더욱 간편하고 용이하다.
- border를 포함하지 않고 width 를 계산하게 되는 경우 레이아웃이 깨지는 경우가 있기 때문에 이를 간편하게 하기 위해 box-sizing은 필수!

## multi column

다단은 아래 신문처럼 화면을 분할해서 좀 더 읽기 쉽도록 만든 레이아웃이다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/adb80c98-fe5d-409d-9a1f-c1a129845e68/Untitled.png)

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="author" content="egoing">
    <style>
      .column{
        text-align: justify;
        column-count: 4;
/*        column-width: 200px;*/
        column-gap:30px;
        column-rule-style: solid;
        column-rule-width: 5px;
        column-rule-color: red;
      }
      h1{
        column-span: all;
      }
    </style>
</head>
<body>
   <div class="column">
     Lorem ipsum dolor sit amet, consectetur adipisicing elit. Molestiae blanditiis nostrum eum ipsam,

     quam expedita distinctio aspernatur voluptas inventore in officia at, a repudiandae modi vel dicta exercitationem accusamus? Tenetur minima doloremque, sequi id, necessitatibus deleniti porro ex maxime perferendis quaerat rerum molestias dolor fugit ullam expedita? Earum velit eaque, esse aliquid labore, ex, corporis odit deserunt consectetur sit aspernatur
      <h1>Lorem ipsum dolor sit amet.</h1>
       ipsam quos cupiditate dolores voluptatem non nam voluptas ab animi quidem adipisci repellat id quod. Laboriosam, distinctio, ut. Quia deserunt, voluptates illum eos, qui, doloremque recusandae laudantium aliquam amet rerum nulla, eveniet. Libero quas iusto, suscipit esse beatae voluptas labore. Nobis facere architecto adipisci ipsa molestias, possimus tempore. Obcaecati, quae laborum atque perspiciatis natus dolore repellendus in officia, sit! Placeat, nesciunt cupiditate similique vitae minima iusto blanditiis perferendis obcaecati enim odio delectus. Quaerat quos deserunt, voluptas aperiam. Quo neque ducimus accusamus quibusdam minima incidunt, voluptatem saepe iusto sit numquam, expedita distinctio aliquid voluptatum alias voluptate sint est ab similique ipsam unde quas porro error? Illum unde consequuntur ab optio architecto, adipisci odit saepe dolor est perferendis error autem iusto a iste tempore nam enim quaerat dicta fugit vel eaque itaque, laborum? Dolores consequatur quo labore dolorem nemo in, tempora animi enim delectus ipsam amet possimus et deserunt recusandae eveniet provident cum quaerat dolorum esse, nam doloremque! Porro sapiente labore aliquam incidunt temporibus praesentium est tempora magnam placeat rem. Autem non provident eos perferendis nihil numquam quisquam suscipit aut, vero minima ex iure cum possimus eveniet veniam aliquam nulla a dignissimos, fugit tempora eaque totam temporibus! Magni minus expedita tempore deserunt necessitatibus, quibusdam, repellat sequi quos exercitationem aliquam sapiente libero eius vitae rem ea nihil deleniti nemo debitis tempora soluta a similique inventore. Sit vero dignissimos facere dolore dicta nulla iure magni quos officiis esse hic accusantium, praesentium adipisci laudantium impedit provident fuga suscipit, placeat porro itaque voluptatum dolorem ullam velit quasi. Laboriosam distinctio explicabo, ullam fugit nesciunt nam itaque repellendus nemo doloribus officia unde quaerat aspernatur odit. Porro quisquam at officia, ad totam minima minus aliquid aliquam rerum dicta, odio sint optio. Exercitationem similique, dignissimos sit nihil fuga ex dolores molestiae ratione impedit error, vitae aliquid reiciendis maxime id odit sed eveniet. Corporis in mollitia assumenda, exercitationem ullam explicabo dolorum tempore architecto cum. Possimus natus ipsam facilis porro magni deleniti nulla eveniet aliquam incidunt minima nihil alias voluptatem, odio molestiae quaerat suscipit, officiis temporibus itaque veritatis, placeat modi corporis saepe harum delectus officia. Libero rerum expedita dolorem porro architecto reprehenderit eligendi molestiae, amet minima quae assumenda neque nulla error, officiis suscipit placeat, illo eius. Aliquid dicta cupiditate culpa consequatur totam. Qui consequuntur eveniet eum dicta repellendus quam ea quisquam dolore, distinctio, quidem facilis minus ratione ullam perferendis. Ad ea, aliquid doloremque distinctio enim hic sunt illum dolore, commodi iusto nobis temporibus nesciunt, vero quae velit perferendis dicta. Quod necessitatibus sit, accusamus odit aspernatur! Sequi, nisi aut at totam perspiciatis fugit quos minus sapiente consequatur neque officiis quibusdam qui nemo, voluptatibus minima dolore laboriosam. Recusandae similique accusamus vel eaque tempore fugiat dolore adipisci, tempora, impedit harum facere aspernatur et nam sequi, facilis architecto voluptate sunt iusto soluta. Itaque laboriosam tempore ab harum fugit natus, eius laborum culpa, impedit autem magni totam. Et dicta animi molestiae, aliquam, sequi enim. Accusamus consectetur eum eligendi nemo sunt provident ut repudiandae, distinctio! Recusandae harum animi quia perferendis maiores! Ratione quis cupiditate odio dolore nulla minus iure veritatis hic temporibus neque beatae delectus doloribus repellat quisquam alias mollitia accusantium perferendis, quibusdam recusandae, iusto modi maiores excepturi corrupti voluptatibus! Facere, maiores ea natus culpa necessitatibus temporibus, eaque, vitae nihil animi repudiandae expedita aspernatur quo sequi, soluta. Minus eligendi tenetur ullam, doloremque, quod libero provident excepturi beatae cumque quo, voluptate. Quam deleniti minus officiis. Sit velit, debitis voluptatem modi consequatur doloremque aperiam assumenda expedita odio nesciunt quis ipsam, cumque impedit quia veritatis error quidem similique accusantium eos, qui sunt? Blanditiis facere tenetur eius minus dolorum, praesentium doloremque consequatur accusamus quis expedita, reiciendis odio optio illo voluptatibus ut asperiores quos officia totam distinctio eaque. Aspernatur eaque nihil porro illo laboriosam ex ea id fuga ipsa! Cum reprehenderit, cumque dolorum aliquam quidem aliquid soluta corrupti pariatur fugiat quae excepturi tempora, nostrum eveniet accusamus consectetur alias minus nulla unde. Rem expedita vitae labore culpa, id sit reiciendis quaerat, temporibus nemo eos modi error excepturi voluptatem. Non officia, accusantium inventore reiciendis cupiditate laboriosam ullam quaerat officiis facilis modi eum iste eius, soluta, iure nobis dolor. Similique deserunt beatae officia reprehenderit quo, aliquam facilis autem nihil in! Quisquam minima sunt corporis, ipsum maiores nam quia corrupti, odit id suscipit ratione voluptate nisi incidunt sequi eum facilis dicta deleniti aliquid ducimus maxime esse qui. Sunt eius, deserunt illo quod ducimus quasi, sed soluta ipsum inventore nisi! Optio modi omnis, ex, fugit nemo eveniet. Nostrum, incidunt porro, dolores non, dolor velit commodi eius recusandae eaque necessitatibus molestiae dolorem unde ipsa voluptatum. Ipsa ab minus atque non quis debitis delectus similique excepturi, ipsum suscipit perferendis doloribus deleniti nam blanditiis architecto quae fuga porro perspiciatis magnam ipsam pariatur. Quia, temporibus. Molestias quia nesciunt vitae quam, deserunt dolor adipisci architecto minus natus facere, molestiae, sint eum. Soluta magni totam ducimus, deserunt quam, nisi unde, asperiores iusto, repudiandae aperiam dolorem ab libero aliquam fugit ratione voluptatem sunt vel earum saepe debitis id nostrum minus ullam quaerat. Amet molestias deserunt quae assumenda ut odit corporis accusantium saepe labore ad. Distinctio in doloremque, deleniti provident ducimus quisquam at, temporibus odio eligendi, consequuntur sequi quibusdam facere aut enim vel similique eius asperiores aperiam ratione suscipit est eum dolore. In eius dignissimos, quod illum dolores! Molestiae possimus eius, illo incidunt optio deleniti, nihil odit nisi harum, quo ex. Corporis omnis accusantium consequuntur ratione laudantium magni tempora expedita. Earum ullam, aspernatur quisquam doloremque soluta quae eius tempora atque magni omnis ex vel iusto, similique eos provident! Autem ipsum aliquam quasi minima, incidunt perferendis facere, optio nobis rerum dolor ipsam quas. Quam consectetur recusandae voluptatem. Praesentium excepturi sunt ut neque eum labore ducimus repudiandae eveniet quibusdam explicabo, iure obcaecati nobis, veritatis quis et accusantium ipsa.
   </div>
</body>
</html>
```

- column-count : 컬럼을 분할하는 태그
  - column-width 로 넓이를 지정해서 분할할 수도 있다.
- column-gap : 컬럼 사이의 간격을 지정하는 태그
- column-rule-!@# : 컬럼의 규치를 설정하는 태그
- column-span: all; : 컬럼을 무시하고 빼낼 수 있다

## background

```html

```

- background-color : red
- background-image : url("bg.png")
- background-repeat : repeat, no-repeat, repeat-x, repeat-y
- background-attachment : scroll, fixed
- background-position : left top or x% y% or x y
- background-size : 100px 100px or cover or contain