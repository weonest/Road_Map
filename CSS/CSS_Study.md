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