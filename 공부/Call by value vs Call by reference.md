# Call by value vs Call by reference

------

https://bcp0109.tistory.com/360

## Call by value

- 함수가 호출될 때 메모리 공간 안에 **임시 공간**이 생성된다. 그리고 함수가 종료되면 해당 공간은 사라진다

- 함수 호출 시 전달되는 

  변수의 값을 복사하여

   함수의 인자로 전달한다

  - **복사하여 처리하기 때문에 원본 값의 변경을 막을 수 있어 안전하다. (보존)**
  - **복사를 하기 때문에 메모리 사용량이 늘어난다**

## Call by reference

- 함수가 호출될 때 메모리 공간 안에서 함수를 위한 별도의 **임시 공간**이 생성된다. (함수 종료시 사라짐)

- 함수 호출 시 전달되는 변수의 

  레퍼런스

  를 전달한다. (해당 변수를 가리킴)

  - **복사하지 않고 직접 참조를 하기 때문에 빠르다.**
  - **직접 참조로 인해 원본 값에 영향을 미친다.**

- 함수 안에서 인자의 값이 변경되면, 함수 호출 시에 있던 변수들도 값이 바뀐다

## Java 에서의 파라미터 전달 방법

그럼 Java에서는 어떤 방법을 사용할까?

Java로 개발을 홰봤다면 메서드로 변수를 넘기고 거기서 값을 수정해 본 경험이 있을 것이다. 그래서 `Call by reference` 라고 오해하기 쉽지만, Java는 오직 `Call by value`로만 동작한다.

### JVM 메모리에 변수가 저장되는 위치

Java의 Call by value에 대해 이해하기 위해선 먼저 변수 생성 시 메모리에 어떤 식으로 저장되는지 알아야 한다.

Java에서 변수를 선언하면 Stack 영역에 할당된다. 원시타입은 Stack 영역에 변수와 함께 저장되며, 참조 타입 객체는 `Heap` 영역에 저장되고 **`Stack` 영역에 있는 변수가** 객체의 주소값을 갖고 있다.

![https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_20_51_33.png](https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_20_51_33.png)

이제 각 타입별로 파라미터를넘겨줄 때 어떤 식으로 동작하는지 알아보자.

### 원시 타입 전달

원시 타입은 Stack 영역에 위치한다. 메서드 호출 시 넘겨받는 파라미터들도 원시 타입이라면 Stack 영역에 생성된다.

```java
public class PrimitiveTypeTest {

    @Test
    @DisplayName("Primitive Type 은 Stack 메모리에 저장되어서 변경해도 원본 변수에 영향이 없다")
    void test() {
        int a = 1;
        int b = 2;

        // Before
        assertEquals(a, 1);
        assertEquals(b, 2);

        modify(a, b);

        // After: modify(a, b) 호출 후에도 값이 변하지 않음
        assertEquals(a, 1);
        assertEquals(b, 2);
    }

    private void modify(int a, int b) {
        // 여기 있는 파라미터 a, b 는 이름만 같을 뿐 test() 에 있는 a, b 와 다른 변수
        a = 5;
        b = 10;
    }
}
```

위 코드에서 `test()` 의 변수 `a`, `b` 와 `modify(a, b)` 로 전달받은 파라미터 `a`, `b` 의 이름과 값은 같다. 하지만, 엄연히 다른 변수이다.

`modify(a, b)` 를 호출하는 순간 Stack 영역에 새로운 변수 `a`, `b` 가 새로 생성되어 총 4 개의 변수가 존재하게 되는 것이다.

![https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_22_01_33.png](https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_22_01_33.png)

Stack 내부에 `test()`와 `modify()` 라는 영역이 나뉘어져 있고 그곳에 동일한 이름을 가진 변수 `a, b`가 존재한다. 그렇기에 `modify()` 영역의 값을 바꿔도 `test()` 영역의 변수는 변화가 없는 것이다.

즉, 원시 타입의 전달은 Call by value로 동작한다.

### 참조 타입 전달

참조 타입은 원시 타입과는 조금 다르다. 변수 자체는 Stack 영역에 생성되지만 실제 객체는 Heap 영역에 위치한다. 그리고 Stack 에 있는 변수가 Heap 에 있는 객체를 바라보고 있는 형태이다.

```java
class User {
    public int age;

    public User(int age) {
        this.age = age;
    }
}

public class ReferenceTypeTest {

    @Test
    @DisplayName("Reference Type 은 주소값을 넘겨 받아서 같은 객체를 바라본다" +
                 "그래서 변경하면 원본 변수에도 영향이 있다")
    void test() {
        User a = new User(10);
        User b = new User(20);

        // Before
        assertEquals(a.age, 10);
        assertEquals(b.age, 20);

        modify(a, b);

        // After
        assertEquals(a.age, 11);
        assertEquals(b.age, 20);
    }

    private void modify(User a, User b) {
        // a, b 와 이름이 같고 같은 객체를 바라본다.
        // 하지만 test 에 있는 변수와 확실히 다른 변수다.

        // modify 의 a 와 test 의 a 는 같은 객체를 바라봐서 영향이 있음
        a.age++;

        // b 에 새로운 객체를 할당하면 가리키는 객체가 달라지고 원본에는 영향 없음
        b = new User(30);
        b.age++;
    }
}
```

원시 타입 코드와 마찬가지로 동일한 변수 `a, b` 가 존재한다.

여기서 `modify(a, b)`를 호출한 후에 `a.age`의 값이 변경되었기 때문에 Call by reference 로 파라미터를 넘겨주었다고 착각하기 쉽다. 하지만 Reference 자체를 전달하는 게 아니라 주소값만 전달해주고 `modify()` 에서 생긴 변수들이 주소값을 보고 객체를 같이 참조하고 있는 것이다.

단계별 그림으로 확인해보자.

### 1. 처음 변수 선언 시 메모리 상태

![https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_22_44_00.png](https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_22_44_00.png)

원시 타입과는 다르게 변수만 Stack 영역에 생성되고 실제 객체는 Heap 영역에 생성된다.

각 변수는 Heap 영역에 있는 객체를 바라보고 있다.

### 2. modify(a, b) 호출 시점의 메모리 상태

![https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_22_50_06.png](https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_22_50_06.png)

넘겨받은 파라미터는 Stack 영역에 생성되고 넘겨받은 주소값을 똑같이 바라본다.

### 3. modify(a, b) 수행 직후 메모리 상태

![https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_23_12_16.png](https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_23_12_16.png)

`test()` 영역과 `modify()` 영역에 존재하는 `a` 라는 변수들은 같은 객체인 `User01` 을 바라보고 있기 때문에 객체를 공유한다.

`b` 라는 변수는 서로 같은 객체인 `User02` 를 바라보고 있었지만, `modify(a, b)` 내부에서 새로운 객체를 생성해서 할당했기 때문에 `User03` 이라는 객체를 바라본다.

그래서 `User03` 의 `age` 값을 변경해도 `test()`에 있는 `b`에는 아무런 변화가 없다.

### 4. test() 끝난 후 최종 메모리 상태

![https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_23_15_36.png](https://raw.githubusercontent.com/ParkJiwoon/PrivateStudy/master/java/images/screen_2022_01_30_23_15_36.png)

modify(a, b) 메서드를 빠져나오면 Stack 영역에 할당된 변수들은 사라진다.

최종적으로 위와 같은 상태가 되며 `User03` 은 어떤 곳에서도 참조되고 있지 않기 때문에 나중에 GC에 의해 제거될 것이다.

### 결론

Call by reference 는 참조 자체를 넘기기 때문에 새로운 객체를 할당하면 원본 변수도 영향을 받는다.

가장 큰 핵심은 호출자 변수와 수신자 파라미터는 Stack 영역 내에서 각각 존재하는 다른 변수다 라고 생각.

아래 블로그 참조도 도움!

https://velog.io/@ahnick/Java-Call-by-Value-Call-by-Reference

ㅇ