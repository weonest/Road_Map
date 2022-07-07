# HTML5

## 비디오 삽입

```html
<!DOCTYPE html>
<html>
  <body>
    <video width="500" height="500" controls>
      <source src="videos/dog.mp4">
    </video>
  </body>
</html>
```

- video 태그
  - controls 속성으로 컨트롤 바 표시 (아마 필수)
- source 태그를 통해 영상을 넣을 수 있으며 여러개도 가능

## Can I use

https://caniuse.com/

새롭게 도입된 기술은 오래된 웹브라우저에서 동작하지 않습니다. 결국 얼마나 많은 웹브라우저가 이 기술을 사용할 수 있는가에 따라서 신기술의 도입 여부를 결정해야 합니다. Can I use는 이런 결정을 할 때 도움을 주는 서비스입니다.

## HTML5의 입력양식

- color : 컬러 지정
- date : 날짜
- datetime : 서버로 전송할 때 국제표준시로
- datetime-local : 지원 x
- email : 이메일 정보
- month : 월 지정
- number : 숫자입력
- range : 숫자의 범위
- search : 검색
- tel : 전화번호
- time : 시간
- url : 주소
- week : 주

**각 타입별로 알맞는 입력형태를 제공한다는 특징이 있다.**

## 입력양식의 속성들

```html
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
    </head>
    <body>
        <form action="login.php" autocomplete="on">
            <input type="text" name="id" placeholder="id를 입력해주세요." autofocus>
            <input type="password" name="password" autocomplete="off" placeholder="비밀번호를 입력해주세요.">
            <input type="submit">
        </form>
    </body>
</html>
```

- autocompelte 태그 : 자동완성
- placeholder 태그 : 입력창이 어떤 정보를 요구하는지 텍스트를 담을 수 있음
- autofocus 태그 : 자동으로 커서 위치

## 입력 값 체크

```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
  </head>
  <body>
    <form action="register.php">
      <input type="text" name="id" placeholder="아이디를 입력해주세요" 
							**required pattern="[a-zA-z].+[0-9]">**
      <input type="email" name="email" placeholder="이메일 입력" required>
      <input type="submit">
    </form>
  </body>
</html>
```

- required 태그 : 정보의 필수 여부를 지정

- patter 태그 : 안에는 html 언어가 아닌 정규표현식 언어가 들어간다.

  정규표현 언어는 필요할 때 따로 공부를 하는 것으로

사용자가 입력한 정보에 대해서 엄격한 테스트를 한 후에 받아들여야 한다. html 에서 입력받은 정보를 곧이곧대로 신뢰하고 받아들여서는 안 된다.