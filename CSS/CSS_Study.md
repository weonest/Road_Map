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