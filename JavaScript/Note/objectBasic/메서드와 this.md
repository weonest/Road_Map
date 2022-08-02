# 메서드와 this

객체는 사용자(user), 주문(order) 등과 같이 실제 존재하는 개체(entity)를 표현하고자 할 때 생성된다.

```jsx
let user = {
  name: "John",
  age: 30
};
```

사용자는 현실에서 장바구니에서 물건 선택하기, 로그인하기, 로그아웃하기 등의 행동을 한다. 이와 마찬가지로 사용자를 나타내는 객체 user 도 특정한 행동을 할 수 있다.

자바스크립트에선 객체의 프로퍼티에 함수를 할당해 객체에게 행동할 수 있는 능력을 부여해준다.

## 메소드 만들기

```jsx
let user = {
  name: "John",
  age: 30
};

user.sayHi = function() {
  alert("안녕하세요!");
};
// 선언된 함수를 메서드로 등록
user.sayHi = sayHi;

user.sayHi(); // 안녕하세요!
```

함수 표현식으로 함수를 만들고, 객체 프로퍼티 `user.sayHi` 에 함수를 할당해 주었다.

이렇게 객체 프로퍼티에 할당된 함수를 메소드(method)라고 부른다.

위 예시에선 `user` 에 할당된 `sayHi` 가 메소드이다.

## 메소드 단축 구문

```jsx
// 아래 두 객체는 동일하게 동작합니다.

user = {
  sayHi: function() {
    alert("Hello");
  }
};

// 단축 구문을 사용하니 더 깔끔해 보이네요.
user = {
  sayHi() { // "sayHi: function()"과 동일합니다.
    alert("Hello");
  }
};
```

위처럼 `function` 을 생략해도 메소드를 정의할 수 있다.

일반적인 방법과 단축 구문을 사용한 방법이 완전히 동일하진 않다. 객체 상속과 관련된 미묘한 차이가 존재하긴 한다.

## 메소드와 this

메소드는 객체에 저장된 정보에 접근할 수 있어야 제 역할을 할 수 있다. 모든 메소드가 그런 건 아니지만, 대부분의 메소드가 객체 프로퍼티의 값을 활용한다.

`user.sayHi()` 의 내부 코드에서 객체 `user` 에 저장된 이름을 이용해 인사말을 만드는 경우가 이런 경우에 속한다.

**메소드 내부에서 `this` 키워드를 사용하면 객체에 접근할 수 있다.**

```jsx
let user = {
  name: "John",
  age: 30,

  sayHi() {
    // 'this'는 '현재 객체'를 나타냅니다.
    alert(this.name);
  }

};

user.sayHi(); // John
```

그런데 이렇게 외부 변수를 사용해 객체를 참조하면 예상치 못한 에러가 발생할 수 있다. `user` 를 복사해 다른 변수에 할당(`admin = user` ) 하고, `user` 는 전혀 다른 값으로 덮어썼다고 가정해 보자. `sayHi()` 는 원치 않는 값을 참조할 거다.

```jsx
let user = {
  name: "John",
  age: 30,

  sayHi() {
    alert( user.name ); // Error: Cannot read property 'name' of null
  }

};

let admin = user;
user = null; // user를 null로 덮어씁니다.

admin.sayHi(); // sayHi()가 엉뚱한 객체를 참고하면서 에러가 발생했습니다.
```

`alert` 함수가 `user.name`대신 `this.name`을 인수로 받았다면 에러가 발생하지 않았을 거다.

## 자유로운 this

자바스크립트의 `this` 는 다른 프로그래밍 언어의 `this` 와 동작 방식이 다르다.

자바스크립트에선 모든 함수에 `this` 를 사용할 수 있다.

`this` 값은 런타임에 결정된다. 컨텍스트에 따라 다라지는 것.

동일한 함수라도 다른 객체에서 호출했다면 ‘this’가 참조하는 값이 달라진다.

```jsx
let user = { name: "John" };
let admin = { name: "Admin" };

function sayHi() {
  alert( this.name );
}

// 별개의 객체에서 동일한 함수를 사용함
user.f = sayHi;
admin.f = sayHi;

// 'this'는 '점(.) 앞의' 객체를 참조하기 때문에
// this 값이 달라짐
user.f(); // John  (this == user)
admin.f(); // Admin  (this == admin)

admin['f'](); // Admin (점과 대괄호는 동일하게 동작함)
```

## this 가 없는 화살표 함수

화살표 함수는 일반 함수와 달리 ‘고유한’ `this` 를 가지지 않습니다. 화살표 함수에서 `this` 를 참조하면, 화살표 함수가 아닌 ‘평범한’ **외부 함수에서 `this` 값을 가져온다.**

```jsx
let user = {
  firstName: "보라",
  sayHi() {
    let arrow = () => alert(this.firstName);
    arrow();
  }
};

user.sayHi(); // 보라
```

별개의 `this` 가 만들어지는 건 원하지 않고, 외부 컨텍스트에 있는 `this` 를 이용하고 싶은 경우 화살표 함수가 유용하다.

## 요약

- 객체 프로퍼티에 저장된 함수를 '메서드’라고 부릅니다.
- `object.doSomthing()`은 객체를 '행동’할 수 있게 해줍니다.
- 메서드는 `this`로 객체를 참조합니다.

`this` 값은 런타임에 결정됩니다.

- 함수를 선언할 때 `this`를 사용할 수 있습니다. 다만, 함수가 호출되기 전까지 `this`엔 값이 할당되지 않습니다.
- 함수를 복사해 객체 간 전달할 수 있습니다.
- 함수를 객체 프로퍼티에 저장해 `object.method()`같이 ‘메서드’ 형태로 호출하면 `this`는 `object`를 참조합니다.

화살표 함수는 자신만의 `this`를 가지지 않는다는 점에서 독특합니다. 화살표 함수 안에서 `this`를 사용하면, 외부에서 `this` 값을 가져옵니다.

문제

```jsx
let calculator = {
  sum() {
    return this.a + this.b;
  },

  mul() {
    return this.a * this.b;
  },

  read() {
    this.a = +prompt('첫 번째 값:', 0);
    this.b = +prompt('두 번째 값:', 0);
  }
};

calculator.read();
alert( calculator.sum() );
alert( calculator.mul() );
```

`this.a`, `this.b` 로 하는 이유는?