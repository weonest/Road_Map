# DI

**Depedency Injection**

> 의존대상 B가 변하면 그것이 A에 영향을 미친다.

즉, B의 기능이 추가 또는 변경되거나 형식이 바뀌면 그 영향이 A에 미친다.

```java
class BurgerChef {
		private HamBurgerRecipe hamBurgerRecipe;
	
		public BurgerChef() {
				hamBurgerRecipe = new HamBurgerRecipe();
		}
}
```

햄버거 레시피가 변화하게 되었을 때, 변화된 레시피에 따라서 요리사는 햄버거 만드는 방법을 수정해야 한다. 레시피의 변화가 요리사의 행위에 영향을 미쳤기 때문에, “요리사는 레시피에 의존한다”고 말할 수 있다.

## 의존관계를 인터페이스로 추상화하기

위 예시를 보면 `HamBurgerRecipe` 만을 의존할 수 있는 구조로 되어있다. 더 다양한 기능을 구현하려면 **인터페이스로 추상화** 해야 한다.

```java
class BurgerChef {
    private BurgerRecipe burgerRecipe;

    public BurgerChef() {
        burgerRecipe = new HamBurgerRecipe();
        //burgerRecipe = new CheeseBurgerRecipe();
        //burgerRecipe = new ChickenBurgerRecipe();
    }
}

interface BugerRecipe {
    newBurger();
    // 이외의 다양한 메소드
} 

class HamBurgerRecipe implements BurgerRecipe {
    public Burger newBurger() {
        return new HamBerger();
    }
    // ...
}
```

의존관계를 인터페이스로 추상화하게 되면, 더 다양한 의존 관계를 맺을 수가 있고, 실제 구현 클래스와의 관계가 느슨해지고, 결합도가 낮아진다.

## 그렇다면 DI는?

지금까지의 구현에서는 `BurgerChef` 내부적으로 의존관계인 `BurgerRecipe`가 어떤 값을 가질지 직접 정하고 있다. 만약 어떤 `BurgerRecipe` 를 만들지 버거 가게 사장님이 정하는 상황을 상상해보자. 즉, `BurgerChef`가 의존하고 있는 `BurgerRecipe` 를 외부에서 결정하고 주입하는 것이다.

이처럼 그 의존관계를 외부에서 결정하고 주입하는 것이 의존관계 주입이다.

## DI 구현 방법

DI는 의존관계를 외부에서 결정하는 것이기 때문에, 클래스 변수를 결정하는 방법들이 곧 DI 를 구현하는 방법이다. 런타임 시점의 의존관계를 외부에서 주입하여 DI 구현이 완성된다.

- 필드 주입 방식

```java
@Service
public class BoardService {
		
		@Autowired
		private BoradDao boradDao;

		public void doSomething() {
				//...
		}
	}
```

가장 간단한 방법으로 `Bean`으로 등록된 객체를 사용하고자 하는 클래스에 Field로 선언한 뒤 `@Autowired` 어노테이션 키워드를 부여주면 자동으로 주입된다.

> `@Autowired`는 등록된 Bean의 타입과 변수의 타입을 매칭해서 객체를 주입 시켜주며, 선언 위치는 인스턴스 변수, 생성자, 메소드 위에 선언한다.

많이 사용됨에도 불구하고 필드 주입을 통한 의존성 주입은 권장되지 않는다. 이유는 너무 추상적인 주입 기법 때문이다.

- Setter Injection

```java
@Service 
public class BoardService {
			
		private BoardDao boardDao;
		
		@Autowired
		public void setBoardDao(BoardDao boradDao) {
			this.boardDao = boardDao;
		}
}
```

세터 주입 방식은 setter 메소드에 `@Autowired`를 붙여서 객체를 주입하는 방식이다. (현재는 권장되지 않는 방법임)

- 생성자 주입 (Constructor Injection) 방식

기존의 필드 주입 방식의 단점을 극복해낸 패턴이다

```java
@Service
public class BoardService {
		
		private BoardDao boardDao;
		

		// @Autowired Spring 4.3 버전 이상부터는 생성자가 하나인 경우 생략 가능
		public BoardService(BoardDao boardDao) {
			this.boardDao = boardDao;
		}
}
```

d