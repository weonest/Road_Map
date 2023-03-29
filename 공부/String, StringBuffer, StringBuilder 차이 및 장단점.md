# String, StringBuffer, StringBuilder 차이 및 장단점

------

https://ifuwanna.tistory.com/221

Java에서 문자열을 다루는 대표적인 클래스로 String, StringBuffer, StringBuilder가 있다. 연산이 많지 않을때는 위에 나열된 어느 클래스를 사용하더라도 이슈가 발생할 가능성은 거의 없다. 그러나 연산횟수가 많아지거나 멀티쓰레드, Race condition 등의 상황이 자주 발생 한다면 각 클래스의 특징을 이해하고 상황에 맞는 적절한 클래스를 사용해 주어야 한다.

## String vs StringBuffer/StringBuilder

위의 가장 큰 차이점은 String은 **불변(immutable)**의 속성을 갖는다는 점이다

```java
String str = "hello";
str = str + " world"; // hello world
```

위의 예제에서 “hello” 값을 가지고 있던 String 클래스의 참조 변수 str이 가리키는 곳에 저장된 “hello” 에 “world” 문자열을 더해 “hello world”로 변경한 것으로 착각할 수 있다.

하지만 기존 값이 들어가있던 String 클래스의 참조변수 str이 “hello world” 라는 값을 가지고 있는 새로운 메모리 영역을 가리키게 변경되고 처음 선언했던 “hello”로 값이 할당되어 있던 메모리 영역은 Garbage로 남아있다가 GC에 의해 사라지게 되는 것이다. String 클래스는 불변하기 때문에 문자열을 수정하는 **시점에 새로운 String 인스턴스가 생성**되는 것이다

이와 같이 String은 불변성을 지니기 때문에 변하지 않는 문자열을 자주 읽어들이는 경우 String을 사용해 주면 좋은 성능을 기대할 수 있다. 그러나 문자열 추가, 수정, 삭제 등의 연산이 빈번하게 발생하는 알고리즘에 String 클래스를 사용하면 **Heap 메모리에 많은 임시 가비지가 생성되어 힙메모리 부족**으로 애플리케이션 성능에 치명적인 영향을 끼치게 된다.

이를 해결하기 위해 Java에서는 **가변성(mutable)**을 가지는 StringBuffer/ StringBuilder 클래스를 도입했다. String 과는 반대로 가변성을 가지기 때문에 .append() .delete() 등의 API를 이용하여 동일 객체내에서 문자열을 변경하는 것이 가능하다. 따라서 문자열의 추가, 수정, 삭제가 빈번하게 발생할 경우라면 String 클래스가 아닌 StringBuffer/Builder를 사용해야 한다.

## StringBuffer vs StringBuilder

그렇다면 동일한 API를 가지고 있는 StringBuffer와 StringBuilder의 차이점은 무엇일까?

가장 큰 차이점은 동기화의 유무로써 **StringBuffer는 동기화** 키워드를 지원하여 멀티쓰레드 환경에서 안전하다는 점이다. 참고로 String도 불변성을 가지기 때문에 마찬가지로 멀티쓰레드 환경에서의 안정성을 가지고 있다.

반대로 **StringBuilder는 동기화를 지원하지 않기** 때문에 멀티쓰레드 환경에서 사용하는 것은 적합하지 않지만 동기화를 고려하지 않는 만큼 단일쓰레드에서의 성능은 StringBuffer 보다 뛰어나다.

### 정리

마지막으로 각 클래스별 특징을 정리해보자. 컴파일러에서 분석할 때 최적화에 따라 다른 성능이 나올 수도 있지만, 일반적인 경우에는 아래와 같은 경우에 맞게 사용하면 좋을 것이다

- String : 문자열 연산이 적고 멀티쓰레드 환경일 경우
- StringBuffer : 문자열 연산이 많고 멀티쓰레드 환경일 경우
- StringBuilder : 문자열 연산이 많고 단일쓰레이드이거나 동기화를 고려하지 않아도 되는 경우

https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://t1.daumcdn.net/cfile/tistory/99BE23375E2F133722

> **String pool**
>
> 스트링 풀은 Heap 메모리 영역에서 String이 저장되는 String Constant Pool을 일컫는다
>
> 리터럴(” “)로 생성한 스트링 객체는 스트링 풀에 들어가고, new 연산자를 통해 생성한 스트링 객체는 이미 그 내용이 스트링 풀에 존재하더라도 Heap 메모리에 별도의 객체를 생성하여 가리킨다