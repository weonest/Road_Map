# Runtime Data Area (JVM의 메모리 영역)

------

https://93jpark.tistory.com/57

https://m.blog.naver.com/heartflow89/220954420688

https://jiwonxdoori.tistory.com/33

https://johngrib.github.io/wiki/jvm-stack/

https://sanghoonly.tistory.com/62

https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/bhKMdd/btrhzs0Emt4/smKYuk9wf1A6HiuFy7xaWk/img.png

https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/do4gpo/btqDL4SagQt/anbyIGPQBEWsmzQGwmhOX1/img.png

Java 프로그램을 실행하기 위해서는 클래스파일을 저장/실행하기 위해 컴퓨터 내부 메모리 공간이 필요하다. 이것을 담당하는 것이 JVM의 Runtime Data Area이다. 이것은 5개의 영역으로 나누어진다.

### 1. 메서드 영역

> 모든 클래스 레벨의 데이터가 저장된다

```java
class Person {
	static boolean isLive = true;
	static ...
}
```

Person 이라는 클래스의 멤버로 isLive라는 변수가 정의되어있다. 이 때, Method Area는 이러한 클래스 멤버 변수인 isLive가 저장된다.

### 2. 힙 메모리

> 모든 객체와 인스턴스 변수가 저장된다

```java
Person p1 = new Person();
```

**참조형 데이터 타입 (Reference Type)의 데이터 타입**을 갖는 **객체(인스턴스), 배열 등은 Heap 영역에 데이터가 저장**된다. 이때 변수(객체, 객체변수, 참조변수)는 **Stack 영역의 공간에서 실제 데이터가 저장된 Heap 영역의 참조값 (reference value, 해시코드/ 메모리에 저장된 주소를 연결해주는 값)을 new 연산자를 통해 리턴** 받는다. 다시 말하면 실제 데이터를 갖고 있는 Heap 영역의 참조 값을 Stack 영역의 객체가 갖고 있다. 이렇게 리턴 받은 참조 값을 갖고 있는 객체를 통해서만 해당 인스턴스를 핸들 할 수 있다.

```java
public class HeapAreaEx01 {
	public static void main(String[] args) {
		int[] a = null; // int형 배열 선언 및 Stack 영역 공간 할당
		System.out.println(a); // 결과 : null
		a = new int[5]; // Heap 영역에 5개의 연속된 공간 할당 및 
		                // 변수 a에 참조값 할당
		System.out.println(a); // 결과 : @15db9742 (참조값)
	}
}
public class HeapAreaEx02 {
	public static void main(String[] args) {
		String str1 = new String("joker");
		String str2 = new String("joker");
		if(str1 == str2){
			System.out.println("같은 주소값 입니다.");
		}else{
			System.out.println("다른 주소값 입니다.");
		}
	}
}
```

new 연산자를 이용해서 생성하면 데이터는 **Heap 영역**에 저장되고 **str1과 str2는 참조 값을 리턴** 받는다. 저장된 주소가 다르기 때문에 “==” 으로 비교 시 다른 주소값을 출력한다.

> 참고로 Heap에 저장된 데이터가 더 이상 사용이 불필요하다면 메모리 관리를 위해 JVM에 의해 알아서 해제된다. 이러한 기능을 가비지컬렉션 (GC) 라고 한다

### 3. 스택 메모리

스택 메모리는 세 가지로 나누어볼 수 있다

- Local Varialbe
- Operand Stack : 메소드 내부의 모든 연산자
- Frame Data : 예외처리를 위한 try/catch 블록

스택 메모리 영역은 메소드 내에서 정의하는 기본 자료형 (int, double, type, long, boolean 등)에 해당되는 지역변수(매개 변수 및 블럭문 내 변수 포함)의 데이터 값이 저장되는 공간이 Stack 영역이다. 해당 메서드가 호출될 때 메모리에 할당되고 종료되면 메모리가 해제된다.

```java
public class StackAreaEx {
	public static void main(String[] args) {
		int a = 5;	a = 4;	a = 3;	a = 2;
		System.out.println(a);
		for(int i=0; i<5; i++){
		}
//		System.out.println(i); 컴파일 에러
	}
}
```

위의 소스코드처럼 a라는 변수는 main 메소드가 호출될 때 Stack 영역에 할당되고 종료 시 해제된다. 또한 a라는 변수의 값이 5, 4, 3, 2 순으로 값을 할당하였고 출력되는 값은 2가 출력된다. 이전 데이터는 지워지는 것이고 결국 2라는 값만 출력된다. 즉, **스택 영역**은 **LIFO (Last In First Out)**의 구조를 갖고 **변수에 새로운 데이터가 할당되면 이전 데이터는 지워진다**는 것을 알 수 있다.

또한 for문 내에 int i 를 정의하였는데 for 문이 종료된 다음 i를 출력하지 못하는 이유는 **지역변수**이므로 for문의 종료와 함께 **스택 영역에서 해제**되기 때문이다.

### 4. PC Register

프로그램의 실행은 사실 CPU에서 명령어(Instruction)을 수행하는 과정으로 이루어진다. 이때 CPU는 이런 연산을 수행하는 동안 필요한 정보를 ‘레지스터’라고 하는 **CPU 내의 기억장치**를 사용하게 된다. 그러니까, A와 B라는 데이터, 즉 비연산 값인 Operand가 있고 이를 더하라는 연산, Instruction이 있다고 보자. A와 B 그리고 더하라는 연산이 순차적으로 입력이 되는데, 이 때 A를 받고 B를 받는 동안 이 값을 CPU가 기억해두어야 한다. 하지만 모든 정보를 다 기억하기 위해서 메모리에 올리는 방법은 **너무 비효율적**이기 때문에 **잠시 동안만 기억하는 공간**이 필요하다. 이 공간이 바로 CPU내의 기억장치, **Register**이다.

하지만 PC Register는 위의 Register와 다르다. 자바는 Register-base가 아닌, **Stack-base**로 작동하기 때문이다. 자바는 OS나 CPU의 입장에서는 하나의 프로세스이기 때문에 가상 머신, JVM의 자원을 이용해야 한다. 그래서 자바는 CPU에서 직접 연산을 수행하도록 하는 것이 아닌, 현재 작업하는 내용을 CPU에게 연산으로 제공해야 하며, 이를 위한 버퍼 공간으로 PC Register라는 메모리 영역을 만들게 된 것이다. JVM은 스택에서 **비연산값 (Operand)**를 뽑아 **별도의 메모리 공간, PC Register**에 저장하는 방식을 취한다.

PC Register에 직접적으로 연산을 저장하는 방식이 **아닌 연산의 주소 값을 저장하는 방식**을 취하고 있다. 그래서 PC Register는 현재 실행하고 있는 부분의 주소를 가지고 있으며, PC의 값은 현재 명령이 끝난 뒤에 값을 증가시켜, 해당하는 값의 명령을 실행하게 된다. PC의 풀네임이 Program Counter라는 것이 명시적으로 드러나는 부분이다.

PC Register는 **스레드가 시작할 때 생성되며 스레드마다 하나씩 존재**한다. **만약에 스레드가 자바 메소드를 수행하고 있으면 JVM 명령의 주소를 PC Register에 저장**하게 된다. 하지만 다른 언어의 메소드를 수행하고 있다면, **undefined** 상태가 된다. 왜냐하면 이 두 경우를 따로 처리하기 때문이다. 이 부분이 뒤에 언급하게 될 Native Method Stack 공간이다

> 즉, JVM은 CPU에 직접 명령을 수행하지 않는다. 스택 프레임에 있는 Operand 스택 영역에서 명령어를 뽑아내어 PC Register라는 별도의 메모리 공간에 저장한 후 CPU에 명령을 전달한다. JVM이 Stack - base의 PC Register를 사용하는 목적은 JVM을 OS에 종속적이지 않게 하기 위함이다.

### 5. Native Method Stack

이 공간은 자바 프로그램의 바이트 코드가 아닌 **실제 실행할 수 있는 기계어로 작성된 프로그램(c, c++ 등?)** 의 호출을 저장하는 영역이다. JVM은 네이티브 방식 (JNI : Java Native Interface)을 지원하기 때문에 스레드에서 네이티브 방식의 메소드가 실행되면 Native Method Stack Area에 쌓이게 된다. 일반적으로 메소드를 실행하는 경우 JVM 스택에 쌓이다가 해당 메서드 내부에 네이티브 방식을 사용하는 메서드가 있다면 해당 메서드는 네이티브 스택에 쌓인다. 그리고 네이티브 메서드가 수행이 끝나면 다시 자바 스택으로 돌아오게 되는데, 네이티브 메서드가 호출한 스택 프레임으로 돌아가는 것이 아닌 **새로운 스택 프레임을 하나 생성해서 여기서 다시 작업을 수행**하게 되는 것이다. 그래서 네이티브 코드로 되어 있는 함수의 호출을 **자바 프로그램 내에서도 직접** 수행할 수 있고 그 결과를 받아올 수도 있는 것이다.

### Stack과 Frame?

- JVM 스레드가 생겨날 때, 해당 스레드를 위한 Stack(JVM Stack ≠ Stack Memory)도 같이 만들어진다.
  - 그 스택엔 Frame이 들어간다.
- 그렇다면 프레임은 무엇인가?
  - 프레임은 메소드가 호출될 때마다 만들어지며, 함수 실행 시 생성되는 변수, 연산 스택, 상수 풀 참조들을 담는다.
  - 스택 영역에 저장되는 함수의 호출정보 = **stack frame**

```java
Thread1   Thread2   Thread3
+-------+ +-------+ +-------+
|       | |       | |       |
|       | |       | | frame |
|       | |       | | frame |
| frame | |       | | frame |
| frame | | frame | | frame |
+-------+ +-------+ +-------+
```

- 스레드가 쓸 수 있는 스택의 사이즈를 넘기게 되면 `StackOverflowError`가 발생한다.
- 스택 사이즈를 동적으로 확장할 수도 있는데 확장할 메모리가 부족하거나, 새로운 스레드를 만들 때 필요한 새로운 스택에 할당할 메모리가 부족하면 `OutOfMemoryError`가 발생한다.

## Frame

프레임은 함수 실행 시 생성되며 데이터나 부분적인 결과값들을 저장한다. 타 언어들과 마찬가지로 함수가 실행될 때 scope 가 생기는데, 프레임에선 이 scope 내의 값들을 저장한다고 보면 된다. 프레임은 다음과 같이 세 가지의 부분으로 구성되어 있다.

### 1. Local Variable

- scope 내 지역 변수들, 지역 변수들은 array 형태로 저장된다.

### 2. Operand Stack

- 연산을 위한 작업 공간

### 3. Run-time Constant Pool Reference

- 컴파일 시 확인해야 하는 상수들부터 런타임 시 확인해야 하는 메서드및 필드 값까지, 다양한 상수들을 담는 테이블