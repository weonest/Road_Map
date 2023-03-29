# Java Generics

------

```java
// ArrayList

ArrayList<String> arrList = new ArrayList<String>();

// List

List<Integer> list = new ArrayList<Integer>();
```

위와 같이 컬렉션을 사용할 때 `<>` 내부에 int형을 (primitive)을 나타내는 Integer(Reference? Wrapper?)가 들어가 있다 이 `<>`를 제네릭이라고 하는데, 이 안에 어떠한 타입을 선언해주어 해당 컬렉션을 사용할 객체의 타입을 지정해준다는 뜻이다.

이는 다룰 객체의 타입을 미리 명시하여 객체의 형변환을 사용할 필요를 없게 하며, 내가 사용하고 싶은 데이터 타입만 사용할 수 있게 해주는 효과가 있다

이렇듯 미리 사용할 객체의 타입을 명시해 주었기에 제네릭과 맞지않는 객체의 타입이 올 경우 컴파일 과정에서 에러를 발생시킨다.

https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://t1.daumcdn.net/cfile/tistory/997F71435AD553700A

### 제네릭의 장점

1. 타임의 안정성 : 의도하지 않은 타입의 객체가 저장되는 것을 막고, 다른 타입의 객체로 인한 타입 형태가 맞지 않아 발생하는 문제를 없애 준다.
2. 불필요한 형변환을 줄여 코드의 간결함 : 타입을 미리 명시함으로써 다른 타입의 객체가 저장되지 않아 객체를 꺼내 사용할 시 형변환을 통한 타입을 맞출 필요가 없어 코드를 간결하게 줄일 수 있다