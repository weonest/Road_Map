# Spring Thread & Transaction

------

https://zzang9ha.tistory.com/414

## 트랜잭션이 새로 생성될 때 스레드도 생성이될까?

먼저 하나의 스레드에서 하나의 트랜잭션을 관리하는지 혹은 하나의 스레드에서 여러 개의 트랜잭션을 관리하는지 간단한 예제를 통해 알아보자

```java
@Service
@RequiredArgsConstructor
public class OuterService {
 
    private final InnerService innerService;
 
    @Transactional
    public void outerMethod() {
        System.out.println("=========================================");
        System.out.println(Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
        System.out.println("=========================================");
        innerService.innerMethod();
    }
 
}
 
 
@Service
public class InnerService {
 
    /* 새로운 트랜잭션 생성 */
    **@Transactional(propagation = Propagation.REQUIRES_NEW)**
    public void innerMethod() {
        System.out.println("=========================================");
        System.out.println(Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
        System.out.println("=========================================");
    }
}
 
 
...
 
@Test
void transactionNewTest() {
    outerService.outerMethod();
}
```

https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https://blog.kakaocdn.net/dn/lG8v4/btrLecaUOdw/FPwWoKOb1d8edBaYIAjHH1/img.png

결과를 확인해보면 트랜잭션을 새로 생성했으나 스레드의 id, name이 동일한 것으로 보아 **하나의 스레드에서 여러 개의 트랜잭션**을 관리하는 것을 확인할 수 있다.

하나의 스레드에서 여러 트랜잭션을 관리하기 때문에 위 코드에서 새로운 트랜잭션이 생성되는 InnverService에서 예외가 발생하여 롤백이 된다면, 상위 트랜잭션인 OuterService에서도 롤백이 된다는 것을 예측할 수 있다.

```java
@Service
@RequiredArgsConstructor
public class OuterService {
 
    private final InnerService innerService;
    private final ItemRepository itemRepository;
 
    @Transactional
    public void outerMethod() {
        System.out.println("=========================================");
        System.out.println(Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
        System.out.println("=========================================");
 
        itemRepository.addCount("item", 0);
 
        innerService.innerMethod();
 
        // 롤백 되어서 실행 안됨
        itemRepository.print();
    }
 
}
 
 
@Service
@RequiredArgsConstructor
public class InnerService {
 
    private final ItemRepository itemRepository;
 
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void innerMethod() {
        System.out.println("=========================================");
        System.out.println(Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
 
        itemRepository.addCount("item", 5);
 
        **throw new RuntimeException();**
    }
 
}
 
 
@Repository
public class ItemRepository {
 
    private Map<String, Integer> item = new HashMap<>();
 
    public void addCount(String name, Integer count) {
        item.put(name, count);
    }
 
    public void print() {
        System.out.println(item);
    }
 
}
 
 
@SpringBootTest
class OuterServiceTest {
 
    @Autowired
    private OuterService outerService;
 
    @Autowired
    private InnerService innerService;
 
    @Test
    void transactionNewTest() {
        outerService.outerMethod();
    }
}
```

ㅇ