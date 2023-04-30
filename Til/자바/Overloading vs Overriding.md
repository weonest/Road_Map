# Overloading vs Overriding

------

![https://blog.kakaocdn.net/dn/k8S6d/btrEsiG8piR/GJ4E7pXx70UXByfecKW42k/img.gif](https://blog.kakaocdn.net/dn/k8S6d/btrEsiG8piR/GJ4E7pXx70UXByfecKW42k/img.gif)

## Overload (과적, 과부하)

클래스의 상속시, 기존에 없던 **새로운 메서드를 추가**하는 것이다. 어떤 메서드의 구현 내용을 **다양하게, 다른 형태**로 동시에 제공하기 위해 사용한다.

동일한 이름의 메서드를 파라미터, 리턴 값 등을 바꿔서 여러 번 작성하며 다음과 같은 특징이 있다.

1. 기존 메서드명과 새로 추가되는 메서드명이 같음
2. **리턴 값**이 같거나 다름
3. **파라미터 개수**가 같거나 다름. 파라미터 개수가 같다면 **데이터 타입**이 달라야 함

→ **메서드명만 같을뿐 동작과 리턴 값이 다르다는 것**

```java
public class Test {

    // 매개변수가 없는 overloadingTest() method
    void overloadingTest(){
        System.out.println("매개변수를 받지 않는 메서드");
    }

    // 매개변수로 int형 인자 2개를 요청하는 overloadingTest(int a, int b) method
    void overloadingTest(int a, int b){
        System.out.println("int형 인자 2개를 요청하는 메서드 "+ a + ", " + b);
    }

    // 매개변수로 String형 인자 1개를 요청하는 overloadingTest(String str) method
    void overloadingTest(String str){
        System.out.println("String형 인자 1개를 요청하는 메서드 " + d);
    }
}
```

### 오버로딩을 사용하는 이유

- 첫 번째로는 같은 기능을 하는 메서드를 하나의 이름으로 사용할 수 있기 때문
- 두 번째로는 메서드의 이름을 절약할 수 있기 때문

많이 사용하는 println() 메서드를 예로 보면 이해하기 쉽다. println() 메서드는 오버로딩 되어있기 때문에 int, char, String 등의 파라미터를 모두 받아 동작이 가능하다.

## Override (덮어쓰다)

기존의 것을 무시하고 덮어씀. 부모 클래스로부터 상속받은 메서드를 자식 클래스에서 확장 및 재정의 하는 것

동일한 이름의 메서드를 사용, 동작 방법만 재정의하며 다음과 같은 특징을 지님

1. 상위 클래스의 메서드여야 사용 가능
2. 메서드 이름이 같음
3. 파라미터 개수, 타입 같음
4. 리턴 값의 데이터 타입도 같음
5. 오버라이드 하는 메서드와 행동이 동일하거나 추가 되어야 함

→ 상위 클래스(부모)의 것을 그대로 쓰거나 더 추가해서 사용

```java
public class Parent {
    public void overridingTest() {
        System.out.println("부모 메서드의 내용");
    }
}

public class Child extends Parent {
    @Override
    public void overridingTest() {
        System.out.println("부모 클래스의 메서드를 상속받아 내용을 재정의해서 사용");
    }
}
```

오버라이딩의 특징으로는 상위 클래스의 메서드보다 접근 제어자를 더 좁은 범위로 변경할 수 없다는 것과 상위 클래스의 메서드보다 더 큰 범위의 예외를 선언할 수 없다는 것. (부모보다 나대기 금지!)

또한 상위 클래스의 static 메서드는 클래스에 속하는 메서드이기 때문에 상속되지 않기에 오버라이드 되지 않는다

- static 메서드는 런타임 시에 생성되는 것이 아니라 컴파일 시 생성되어 메모리에 적제되는 방식이기 때문에 런타임 시에 해당 메서드를 구현한 실체 객체를 찾아서 호출하게 된다. static 메서드에 대해서는 다형성이 적용되지 않는다.

final이 지정된 메서드 역시 오버라이드를 할 수 없으며, private 접근 제어자를 가진 메서드는 상속 자체가 불가능하기 때문에 오버라이드가 성립되지 않는다. (final의 경우 하위 클래스가 해당 메서드를 재정의 할 수 없도록 하기 위해서 사용)

또 실무에서 인터페이스를 implements로 가져와서 인터페이스에 정의된 메서드를 @Override 어노테이션을 사용하여 재정의하는데, 이때 interface 의 메서드를 오버라이드해서 구현하는 경우 반드시 public 접근 제어자를 사용해야 한다

### @Override 애노테이션

위 애노테이션을 사용하는 이유는 무엇일까? @Override 애노테이션은 시스템에서 오버라이딩한 메서드라고 알리는 역할로 오버라이딩이 잘못된 경우 경고를 준다.

애노테이션이 적용되지 않은 상태에서는 전에 오버라이드 한 메서드가 업데이트 이후 그냥 추가적인 메서드로 인식되어 컴파일 오류가 발생하지 않는다. 이때 애노테이션을 적용하면 의도적으로 컴파일 오류를 일으켜 작동방식이 바뀌는 것을 대비할 수 있다. 또한, 이러한 표시를 통해 코드 리딩 시에 해당 메서드가 오버라이딩 되었다는 것을 쉽게 파악할 수 있다.