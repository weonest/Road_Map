# 테스트 자동화와 Mocha

## Behavior Driven Development

[Behavior Driven Development(BDD)](http://en.wikipedia.org/wiki/Behavior-driven_development)라 불리는 방법론에 대해 알아보자.

## 거듭제곱 함수와 명세서

`x`를 `n`번 곱해주는 함수, `pow(x, n)`를 구현하고 있다고 가정해 보자. (단, n은 자연수이고, 조건 n≥0을 만족해야 한다.)

자바스크립트엔 거듭제곱 연산자`**`가 있다. 그럼에도 불구하고 함수를 직접 구현하는 이유는, 구현 과정에 초점을 두면서 BDD를 직접 적용해 보기 위해서다.

- 본격적으로 코드를 작성하기 전에 먼저 해야 할 것은, 코드가 무슨 일을 하는지 상상한 후 이를 자연어로 표현해야 한다.
- 이때, 만들어진 산출물을 BDD에선 명세서(specification) 또는 짧게 줄여 스펙(spec)이라고 부른다.

```jsx
describe("pow", function() {

  it("주어진 숫자의 n 제곱", function() {
    assert.equal(pow(2, 3), 8);
  });

});
```

스펙은 세 가지 주요 구성 요소로 이루어진다.

- ```
  describe("title", function() {...})
  ```

  - 구현하고자 하는 기능에 대한 설명이 들어간다. 위 예시에선 함수 `pow`가 어떤 동작을 하는지에 대한 설명이 들어갈 것이다. `it` 블록을 한데 모아주는 역할도 한다.

- ```
  it("유스 케이스 설명", function() {...})
  ```

  - `it`의 첫 번째 인수엔 특정 유스 케이스에 대한 설명이 들어간다. 이 설명은 누구나 읽을 수 있고 이해할 수 있는 자연어로 적어준다. 두 번째 인수엔 유스 케이스 테스트 함수가 들어간다.

- ```
  assert.equal(value1, value2)
  ```

  - 기능을 제대로 구현했다면 `it` 블록 내의 코드 `assert.equal(value1, value2)`가 에러 없이 실행된다.
  - 함수 `assert.*`는 `pow`가 예상한 대로 동작하는지 확인해 준다. 위 예시에선 `assert.equale`이 사용되었는데, 이 함수는 인수끼리 동등 비교했을 때 다르다고 판단되면 에러를 반환한다. 예시에선 `pow(2, 3)`의 결괏값과 `8`을 비교한다.

## 개발 순서

실제 개발에 착수하면 아래와 같은 순서로 개발이 진행된다.

1. 명세서 초안을 작성한다. 초안엔 기본적인 테스트도 들어간다.
2. 명세서 초안을 보고 코드를 작성한다.
3. 코드가 작동하는지 확인하기 위해 Mocha라 불리는 테스트 프레임워크를 사용해 명세서를 실행한다. 이때, 코드가 잘못 작성되었다면 에러가 출력. 에러를 다 잡을 때까지 코드를 수정한다.
4. 명세서에 지금까진 고려하지 않았던 유스 케이스 몇 가지를 추가한다.
5. 세 번째 단계로 돌아가 테스트를 돌리며 보완

- 위와 같은 방법은 반복적인(iterative)성격을 지닌다. 명세서를 작성하고 실행한 후 테스트를 모두 통과할 때까지 코드를 작성하고, 또 다른 테스트를 추가해 앞의 과정을 반복하기 때문이다.

## 스펙 실행하기

본 튜토리얼에선 총 3개의 라이브러리를 사용해 테스트를 진행해보겠다. 각 라이브러리는 다음과 같다.

- [Mocha](http://mochajs.org/) – 핵심 테스트 프레임워크로, `describe`, `it`과 같은 테스팅 함수와 테스트 실행 관련 주요 함수를 제공합니다.
- [Chai](http://chaijs.com/) – 다양한 assertion을 제공해 주는 라이브러리입니다. 우리 예시에선 `assert.equal` 정도만 사용해 볼 예정입니다.
- [Sinon](http://sinonjs.org/) – 함수의 정보를 캐내는 데 사용되는 라이브러리로, 내장 함수 등을 모방합니다. 본 챕터에선 사용하지 않고, 다른 챕터에서 실제로 사용해 볼 예정입니다.

세 라이브러리 모두, 브라우저나 서버 사이드 환경을 가리지 않고 사용 가능하다. 아래 예시에는 `pow`의 스펙, 라이브러리 모두가 들어있다.

```jsx
<!DOCTYPE html>
<html>
<head>
  <!-- 결과 출력에 사용되는 mocha css를 불러옵니다. -->
  <link rel="stylesheet" href="<https://cdnjs.cloudflare.com/ajax/libs/mocha/3.2.0/mocha.css>">
  <!-- Mocha 프레임워크 코드를 불러옵니다. -->
  <script src="<https://cdnjs.cloudflare.com/ajax/libs/mocha/3.2.0/mocha.js>"></script>
  <script>
    mocha.setup('bdd'); // 기본 셋업
  </script>
  <!-- chai를 불러옵니다 -->
  <script src="<https://cdnjs.cloudflare.com/ajax/libs/chai/3.5.0/chai.js>"></script>
  <script>
    // chai의 다양한 기능 중, assert를 전역에 선언합니다.
    let assert = chai.assert;
  </script>
</head>

<body>

  <script>
    function pow(x, n) {
      /* 코드를 여기에 작성합니다. 지금은 빈칸으로 남겨두었습니다. */
    }
  </script>

  <!-- 테스트(describe, it...)가 있는 스크립트를 불러옵니다. -->
  <script src="test.js"></script>

  <!-- 테스트 결과를 id가 "mocha"인 요소에 출력하도록 합니다.-->
  <div id="mocha"></div>

  <!-- 테스트를 실행합니다! -->
  <script>
    mocha.run();
  </script>
</body>

</html>
```

위 페이지는 다섯 부분으로 나눌 수 있다.

1. `<head>` - 테스트에 필요한 서드파티 라이브러리와 스타일을 불러옴
2. `<script>` - 테스트할 함수(`pow`)의 코드가 들어감
3. 테스트 - `describe("pow", ...)` 를 외부 스크립트(`test.js`)에서 불러옴
4. HTML 요소 `<div id = "mocha">`- Mocha 실행 결과가 출력됨
5. `mocha.run()` - 테스트를 실행시켜주는 명령어

결과

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/eb6295a4-de8a-457d-a6f5-a9a9b9c9d42a/Untitled.png)

지금은 함수 `pow` 본문에 아무런 코드도 없기 때문에 테스트가 실패할 수밖에 없다. 지금 상황에선 `pow(2, 3)`가 `8` 이 아닌 `undefined`를 반환하기 때문에 에러가 발생한다.

## 스펙 개선하기

1. 기존 `it` 블록에 `assert` 를 하나 더 추가하기

```jsx
describe("pow", function() {

  it("주어진 숫자의 n 제곱", function() {
    assert.equal(pow(2, 3), 8);
    assert.equal(pow(3, 4), 81);
  });

});
```

1. 테스트를 하나 더 추가하기 (`it` 블록 하나 더 추가하기)

```jsx
describe("pow", function() {

  it("2를 세 번 곱하면 8입니다.", function() {
    assert.equal(pow(2, 3), 8);
  });

  it("3을 네 번 곱하면 81입니다.", function() {
    assert.equal(pow(3, 4), 81);
  });

});
```

`assert` 에서 에러가 발생하면 `it` 블록은 즉시 종료된다. 따라서 `it` 블록에 `assert` 를 하나 더 추가하면 **첫 번째 `assert` 가 실패했을 때 두 번째 `assert` 의 결과를 알 수 없다. 두 방법의 근본적인 차이는 여기에 있다.**

두 번째 방법처럼 `it` 블록을 하나 더 추가해 테스트를 분리해서 작성하면 더 많은 정보를 얻을 수 있기 때문에 두 번째 방법을 더 추천한다.

결과

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/beff04ff-45fd-4483-a299-598dc24a8d4e/Untitled.png)

(꼼수로 `pow` 함수를 `return 8;` 로 설정 후 돌렸기 때문)

## 코드 개선하기

두 번째 테스트도 통과할 수 있게 코드를 개선해 보자. 이번엔 꼼수 사용 x

```jsx
function pow(x, n) {
	let result = 1;

	for (let i = 0;, i < n; i++) {
		result *= x;
	}
	
	return result;
}
```

함수가 제대로 작동하는지 확인하기 위해 더 많은 값을 테스트해 보자. 수동으로 여러 개의 `it` 블록을 만드는 대신 `for` 문을 사용해 자동으로 `it` 블록을 만들어보자.

```jsx
describe**(**"pow", function() {
	
	function makeTest(x) {
		let expected = x * x* x;
		it(`${x}을/를 세 번 곱하면 ${expected}입니다.`, function() **{**
			assert.equal(pow(x, 3), expected);
	**}**);
}

for (let x = 1; x <= 5; x++) {
	makeTest(x);
}

**}**);
```

결과

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/25a2de7b-07fe-47b7-a941-55f5895809c9/Untitled.png)

## 중첩 describe

아래 예시에서 헬퍼 함수 `makeTest` 와 `for` 문이 중첩 `describe` 안에 함께 묶여있다는 것을 눈여겨 보자. `makeTest` 는 오직 `for` 문에서만 사용되고, 다른 데선 사용되지 않기 때문에 이렇게 묵어놓았다. 아래 스펙에서 `makeTest` 와 `for` 문은 함께 어우러져 `pow` 가 제대로 동작하는지 확인해 주는 역할을 한다.

이렇게 중첩 `describe` 를 쓰면 그룹을 만들 수 있다.

```jsx
describe("pow", function() {

  describe("x를 세 번 곱합니다.", function() {

    function makeTest(x) {
      let expected = x * x * x;
      it(`${x}을/를 세 번 곱하면 ${expected}입니다.`, function() {
        assert.equal(pow(x, 3), expected);
      });
    }

    for (let x = 1; x <= 5; x++) {
      makeTest(x);
    }

  });

  // describe와 it을 사용해 이 아래에 더 많은 테스트를 추가할 수 있습니다.
});
```

중첩 `describe` 는 새로운 테스트 ‘하위 그룹(subgroup)’을 정의할 때 사용된다. 이렇게 새로 정의된 테스트 하위 그룹은 테스트 결과 보고서에 들여쓰기 된 상태로 출력된다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/026a1f4d-5431-4dbc-8487-7867f7dc1734/Untitled.png)

```
                      (raises x to power 3)
```

> **`before/after`와 `beforeEach/afterEach`**
>
> 함수 `before` 는 테스트가 실행되기 전에 실행되고, 함수 `after` 는 테스트가 실행된 후에 실행된다. `beforeEach` 는 매 `it`이 실행되기 전에 실행되고, 함수 `afterEach`는 매 `it` 이 실행된 후에 실행된다.

예시

```jsx
describe("test", function() {

  before(() => alert("테스트를 시작합니다 - 테스트가 시작되기 전"));
  after(() => alert("테스트를 종료합니다 - 테스트가 종료된 후"));

  beforeEach(() => alert("단일 테스트를 시작합니다 - 각 테스트 시작 전"));
  afterEach(() => alert("단일 테스트를 종료합니다 - 각 테스트 종료 후"));

  it('test 1', () => alert(1));
  it('test 2', () => alert(2));

});
테스트를 시작합니다 - 테스트가 시작되기 전          (before)
단일 테스트를 시작합니다 - 각 테스트 시작 전         (beforeEach)
1
단일 테스트를 종료합니다 - 각 테스트 종료 후         (afterEach)
단일 테스트를 시작합니다 - 각 테스트 시작 전         (beforeEach)
2
단일 테스트를 종료합니다 - 각 테스트 종료 후         (afterEach)
테스트를 종료합니다 - 테스트가 종료된 후            (after)
```

## 스펙 확장하기

첫 번째 반복(iteration)에선 함수 `pow`의 기본적인 기능을 구현했다.

앞서 정의했듯이 함수 `pow(x, n)` 의 매개변수 `n` 은 양의 정수이어야 한다.

자바스크립트에선 **수학 관련 연산을 수행하다 에러가 발생하면 `NaN`  을 반환**한다. `n` 이 조건에 맞지 않을 때 함수가 `NaN` 을 반환하는지 아닌지를 검사해주는 테스트를 추가해 보자.

```jsx
describe("pow", function() {

  // ...

  it("n이 음수일 때 결과는 NaN입니다.", function() {
    assert.isNaN(pow(2, -1));
  });

  it("n이 정수가 아닐 때 결과는 NaN입니다.", function() {
    assert.isNaN(pow(2, 1.5));
  });

});
```

결과

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b5c7b3c5-1b37-4e24-a6de-ff96b578cc96/Untitled.png)

> **다양한 assertion**
>
> 위에서 사용한 `assert.isNaN`은 `NaN`인지 아닌지를 확인해줍니다.
>
> [Chai](http://chaijs.com/)는 이 외에도 다양한 assertion을 지원합니다.
>
> - `assert.equal(value1, value2)` – `value1`과 `value2`의 동등성을 확인합니다(`value1 == value2`).
> - `assert.strictEqual(value1, value2)` – `value1`과 `value2`의 일치성을 확인합니다(`value1 === value2`).
> - `assert.notEqual`, `assert.notStrictEqual` – 비 동등성, 비 일치성을 확인합니다.
> - `assert.isTrue(value)` – `value`가 `true`인지 확인합니다(`value === true`).
> - `assert.isFalse(value)` – `value`가 `false`인지 확인합니다(`value === false`).
> - 이 외의 다양한 assertion은 [docs](http://chaijs.com/api/assert/)에서 확인할 수 있습니다.

새롭게 추가한 테스트를 통과할 수 있도록 `pow`에 코드를 몇 줄 추가해보자.

```jsx
function pow(x, n) {
  **if (n < 0) return NaN;
  if (Math.round(n) != n) return NaN;**

  let result = 1;

  for (let i = 0; i < n; i++) {
    result *= x;
  }

  return result;
}
```

## 요약

BDD에선 스펙을 먼저 작성하고 난 후에 구현을 시작합니다. 구현이 종료된 시점에는 스펙과 코드 둘 다를 확보할 수 있습니다.

스펙의 용도는 세 가지입니다.

1. **테스트** – 함수가 의도하는 동작을 제대로 수행하고 있는지 보장함
2. **문서** – 함수가 어떤 동작을 수행하고 있는지 설명해줌. `describe`와 `it`에 설명이 들어감
3. **예시** – 실제 동작하는 예시를 이용해 함수를 어떻게 사용할 수 있는지 알려줌

스펙이 있기 때문에 개발자는 안전하게 함수를 개선하거나 변경할 수 있습니다. 함수를 처음부터 다시 작성해야 하는 경우가 생겨도 스펙이 있으면 기존 코드와 동일하게 동작한다는 것을 보장할 수 있습니다.

코드가 바뀌어도 기존에 구현된 **기능에 영향을 주지 않게 하는 건** 대규모 프로젝트에서 **매우 중요**합니다. 프로젝트 규모가 커지면 함수 하나를 이곳저곳에서 사용하는데, 수동으로 변경된 함수가 이 함수를 사용하는 모든 곳에서 제대로 동작하는지 확인하는 건 불가능하기 때문입니다.

테스트를 하지 않고 코드를 작성해왔다면 개발자들은 둘 중 한 갈래의 길로 빠져버리고 맙니다.

1. 아무 대책 없이 코드를 변경합니다. 부작용을 생각하지 않고 함수를 수정했기 때문에 어디선가 버그가 발생하고 맙니다.
2. 수정이나 개선을 기피하게 됩니다. 버그의 대가가 가혹하기 때문이죠. 코드가 구식이 되어도 그 누구도 코드를 건드리려 하지 않습니다. 좋지 않은 상황이죠.

**테스팅 자동화는 이런 문제를 피하게 도와줍니다!**

테스팅 자동화를 수행하고 있는 프로젝트라면 이런 문제를 걱정하지 않아도 됩니다. 코드에 변화가 있어도 스펙을 실행해 테스트를 진행하면 몇 초 만에 에러 발생 여부를 확인할 수 있습니다.

장점이 하나 더 있습니다. **잘 테스트 된 코드는 더 나은 아키텍처를 만듭니다.**

수정과 개선이 쉬우니까 당연히 좋은 아키텍처를 만들 수 있다고 생각할 수 있습니다. 하지만 또 다른 이유가 있습니다.

테스트를 작성하려면 함수가 어떤 동작을 하는지, 입력값은 무엇이고 출력값은 무엇인지 정의하고 난 후에 구현을 시작합니다. 코드는 정의된 사항을 뒷받침 할 수 있게 작성해야 하죠. 구현을 시작하는 순간부터 이미 좋은 아키텍처가 보장됩니다.