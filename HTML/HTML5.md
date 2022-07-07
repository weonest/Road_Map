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