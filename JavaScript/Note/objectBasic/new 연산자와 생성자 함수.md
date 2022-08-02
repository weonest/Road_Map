# new 연산자와 생성자 함수

## 생성자 함수

생성자 함수(constructor function)와 일반 함수에 기술적인 차이는 없다. 다만 생성자 함수는 아래 두 관례를 다른다.

1. 함수 이름의 첫 글자는 대문자로 시작
2. 반드시 `new` 연산자를 붙여 실행

```jsx
function User(name) {
  this.name = name;
  this.isAdmin = false;
}

let user = new User("보라");

alert(user.name); // 보라
alert(user.isAdmin); // false
```

`new USer(...)` 를 써서 함수를 실행하면 아래와 같은 알고리즘이 동작.

1. 빈 객체를 만들어 `this`에 할당한다.
2. 함수 본문을 실행한다. `this`에 새로운 프로퍼티를 추가해 `this`를 수정한다.
3. `this`를 반환한다.

```jsx
function User(name) {
  // this = {};  (빈 객체가 암시적으로 만들어짐)

  // 새로운 프로퍼티를 this에 추가함
  this.name = name;
  this.isAdmin = false;

  // return this;  (this가 암시적으로 반환됨)
}
```

> **new function() { … }**
>
> 재사용할 필요가 없는 복잡한 객체를 만들어야 한다고 해보자. 많은 양의 코드가 필요할 거다. 이럴 땐 아래와 같이 코드를 익명 생성자 함수로 감싸주는 방식을 사용할 수 있다.
>
> ```jsx
> let user = new function() {
>   this.name = "John";
>   this.isAdmin = false;
> 
>   // 사용자 객체를 만들기 위한 여러 코드.
>   // 지역 변수, 복잡한 로직, 구문 등의
>   // 다양한 코드가 여기에 들어갑니다.
> };
> ```
>
> 위 생성자 함수는 익명 함수이기 때문에 어디에도 저장되지 않는다. 처음 만들 때부터 단 한 번만 호출할 목적으로 만들었기 때문에 재사용이 불가.
>
> 이렇게 익명 생성자 함수를 이용하면 재사용은 막으면서 코드를 캡슐화 할 수 있다.

## 생성자와 return문

생성자 함수엔 보통 `return` 문이 없다. 반환해야 할 것들은 모두 `this` 에 저장되고, `this`는 자동으로 반환되기 때문에 반환문을 명시적으로 써 줄 필요가 없다.

그런데 만약 `return`문이 있다면?

- 객체를 `return` 한다면 `this`대신 객체가 반환된다.
- 원시형을 `return` 한다면 `return` 문이 무시된다.

`return`뒤에 객체가 오면 생성자 함수는 해당 객체를 반환해주고, 이 외의 경우는 `this`가 반환.

```jsx
function BigUser() {

  this.name = "원숭이";

  return { name: "고릴라" };  // <-- this가 아닌 새로운 객체를 반환함
}

alert( new BigUser().name );  // 고릴라
```

> **괄호 생략하기**
>
> 인수가 없는 생성자 함수는 괄호를 생략해 호출할 수 있다. 좋은 스타일은 아님
>
> ```jsx
> let user = new User; // <-- 괄호가 없음
> // 아래 코드는 위 코드와 똑같이 동작합니다.
> let user = new User(); 
> ```

## 생성자 내 메소드

생성자 함수를 사용하면 매개변수를 이용해 객체 내부를 자유롭게 구성할 수 있다. 엄청난 유연성이 확보되는 것이다.

지금까진 `this` 에 프로퍼티를 더해주는 예시만 살펴봤는데, 메소드를 더해주는 것도 가능하다.

```jsx
function User(name) {
  this.name = name;

  **this.sayHi = function() {
    alert( "제 이름은 " + this.name + "입니다." );**
  };
}

let bora = new User("이보라");

bora.sayHi(); // 제 이름은 이보라입니다.

/*
bora = {
   name: "이보라",
   sayHi: function() { ... }
}
*/
```

## 요약

- 생성자 함수(짧게 줄여서 생성자)는 일반 함수입니다. 다만, 일반 함수와 구분하기 위해 함수 이름 첫 글자를 대문자로 씁니다.
- 생성자 함수는 반드시 `new` 연산자와 함께 호출해야 합니다. `new`와 함께 호출하면 내부에서 `this`가 암시적으로 만들어지고, 마지막엔 `this`가 반환됩니다.

생성자 함수는 유사한 객체를 여러 개 만들 때 유용합니다.

자바스크립트는 언어 차원에서 다양한 생성자 함수를 제공합니다. 날짜를 나타내는 데 쓰이는 `Date`, 집합(set)을 나타내는 데 쓰이는 `Set` 등의 내장 객체는 이런 생성자 함수를 이용해 만들 수 있습니다.