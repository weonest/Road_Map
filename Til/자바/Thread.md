# Thread

http://www.tcpschool.com/java/java_thread_concept

https://velog.io/@cateto/Java-Thread-Safe란

## Process란?

프로세스란 단순히 실행 중인 프로그램이라고 할 수 있다. (실행 단위) 즉, 사용자가 작성한 프로그램이 운영체제에 의해 메모리 공간을 할당받아 실행 중인 것을 말한다. 이러한 프로세스는 프로그램에 사용되는 데이터와 메모리 등의 자원 그리고 스레드로 구성된다.

## Thread란?

스레드란 프로레스 내에서 실제로 작업을 수행하는 주체를 의미한다. 모든 프로세스에는 한 개 이상의 스레드가 존재하여 작업을 수행한다.

## Thread 생성과 실행

자바에서 스레드를 생성하는 방법에는 다음과 같이 두 가지 방법이 있다.

1. Runnable 인터페이스를 구현하는 방법
2. Thread 클래스를 상속받는 방법

```java
class ThreadWithClass extends Thread {

    public void run() {

        for (int i = 0; i < 5; i++) {

            System.out.println(getName()); // 현재 실행 중인 스레드의 이름을 반환함.

            try {

                Thread.sleep(10);          // 0.01초간 스레드를 멈춤.

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

    }

}

 

class ThreadWithRunnable implements Runnable {

    public void run() {

        for (int i = 0; i < 5; i++) {

            System.out.println(Thread.currentThread().getName()); // 현재 실행 중인 스레드의 이름을 반환함.

            try {

                Thread.sleep(10);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

    }

}

 

public class Thread01 {

    public static void main(String[] args){

        ThreadWithClass thread1 = new ThreadWithClass();       // Thread 클래스를 상속받는 방법

        Thread thread2 = new Thread(new ThreadWithRunnable()); // Runnable 인터페이스를 구현하는 방법

 

        thread1.start(); // 스레드의 실행

        thread2.start(); // 스레드의 실행

    }

}
// 실행 결과
Thread-0

Thread-1

Thread-0

Thread-1

Thread-0

Thread-1

Thread-0

Thread-1

Thread-0

Thread-1
```

위 예제의 실행 결과를 살펴보면, 생성된 스레드가 서로 번갈아가며 실행되고 있는 것을 확인할 수 있다. **Thread 클래스를 상속받으면 다른 클래스를 상속받을 수 없으므로, 일반적으로 확장성을 위해 Runnable 인터페이스를 구현한다.**

## 동기화(Synchronized)란?

하나 이상의 Thread가 어떤 로직에 동시에 접근했을 때, 그 연산에 대한 값의 무결성을 보장하기 위해 수행 영역에 대한 lock을 걸어주는 것.

## Thread-safe란?

멀티쓰레드 프로그래밍에서 여러 쓰레드로부터 어떤 메서드나 변수, 객체에 동시에 접근이 이뤄져도 프로그램의 실행에 문제가 없는 상태를 말한다.

하나의 function이 한 Thread로 부터 호출되어 실행 중일 때, 다른 Thread가 동일한 함수를 호출하여 동시에 실행되더라도 각 Thread에서 함수의 수행 결과가 바르게 나오는 것.

> **서버를 비동기 처리하면 어떤 장점이 있을까?**
>
> 요청에 따른 결과가 반환되는 시간 동안 다른 작업을 수행할 수 있다. (카페 주문 처럼)
>
> 단점으로는 설계가 복잡하고, DB와 같은 데이터를 다루는 경우 비동기로 처리할 시 데이터의 정합성이 맞지 않을 수 있다ㅇㅇ