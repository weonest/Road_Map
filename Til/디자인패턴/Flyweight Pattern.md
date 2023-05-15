# Flyweight Pattern

------

## 플라이웨이트 패턴이란?

싱글톤 패턴과 비슷한 원리로 인스턴스를 가능한 한 공유하여 사용함으로써 메모리를 절약하는 패턴이다.

공통으로 사용하는 클래스를 만드는 팩토리 클래스를 두고, 사용 클래스의 인스턴스가 팩토리 클래스 내에 있을 시에는 꺼내서 사용하고, 없을 시에는 새로 생성해서 사용하는 패턴이다.

대표적으로 Java의 String 문자열을 저장하는 String Constant Pool이 플라이웨이트 패턴을 구현하고 있다.

마인크래프트라는 게임을 예로 들어보자

![https://thecraftdaily.com/content/images/2021/04/SE-1a538c64-61b4-49fc-a2d4-a5e3005bf8ec.png](https://thecraftdaily.com/content/images/2021/04/SE-1a538c64-61b4-49fc-a2d4-a5e3005bf8ec.png)

(이미지 출처 : https://thecraftdaily.com/ko-kr/minecraft-tree/)

마인크래프트라는 게임의 맵의 크기는 공식적으로 **4,096,000,000㎢,** 즉 ****실제 지구 면적의 약 8배이다. 아직 맵 전체에 나무가 심어져 있지 않다고 가정하고, 맵 전체를 다양한 종류의 나무로 채우려고 한다.

이때 나무의 데이터는 `type : 4byte, x : 4byte, y : 4byte` 라고 가정하고 모든 나무를 심을 때마다 새로운 객체를 생성한다면, 우리는 작업을 완수할 수 있을까? 아마 얼마 안가서 메모리가 버티지 못할 것이다.

이러한 경우에 필요한 디자인 패턴이 바로 **플라이웨이트 패턴**이다.

각각의 나무의 x, y 값은 다르겠지만 type에 대한 정보는 상당히 겹치는 부분이 많을 것이다. 즉, **type 별로 인스턴스를 하나씩만 생성하고 저장해둔 다음**, 나무를 심을때에만 꺼내서 사용한다면 메모리를 보다 효율적으로 사용할 수 있을 것이다.

기존에 100그루의 나무를 설치하기 위해서는 100 * 12byte 크기만큼의 메모리를 사용해야 했지만, 변경한 방법을 적용하면 12byte * (나무의 종류) 만큼의 메모리만 사용하면 되는 것이다.

### 구현

플라이웨이트 패턴을 구현하기 위해서는 다음의 세 가지가 필요하다

1. 실제 공유될 객체 (나무)
2. 객체의 인스턴스를 생성하고 공유하는 Factory
   1. 같은 type의 객체가 없다면 생성하고, 있다면 그 객체를 반환
3. 패턴을 사용할 클라이언트

코드를 통해 살펴보도록 하자!

- 공유할 Tree 객체

```java
public class Tree {

	private String type;
	private int x;
	private int y;

	public Tree(String type) {
		this.type = type;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void install() {
		System.out.println("%d, %d 좌표에 %s 타입의 나무를 설치했습니다. ", x, y, type");
	}
}
```

- Tree를 제공해줄 TreeFactory

```java
public class TreeFactory {
    //HashMap 자료구조를 활용해서 만들어진 나무들을 저장
    public static final HashMap<String, Tree> treeMap = new HashMap<>();
    
   
    public static Tree getTree(String treeType){
        //Map에 입력받은 종류의 나무가 있는지 찾는다. 있으면 그 객체를 반환
        Tree tree = (Tree)treeMap.get(treeType); 

       //만약 아직 같은 종류의 나무가 Map에 없다면 새로 객체를 생성해 반환
        if(tree == null){
            tree = new Tree(treeType);
            treeMap.put(treeType, tree);
            System.out.println("새 객체 생성");
        }

        return tree;
    }
}
```

- 클라이언트

```java
public class Main {
    public static void main(String[] args) throws IOException {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        		
        for(int i = 0; i < 10; i++){
            //나무 색 입력
            String type = br.readline();
            //팩토리를 통해 나무 반환
            Tree tree = (Tree)TreeFactory.getTree(type);
      
            tree.setX((int) (Math.random()*100));
            tree.setY((int) (Math.random()*100));
           
            tree.install();
        }

    }
}
```

이와 같은 구조로 코드를 작성하면 같은 종류의 타입에 대해서는 기존에 생성된 나무 객체를 가져와 x, y 값만 변경하여 나무를 심을 수 있기 때문에 메모리를 효율적으로 사용할 수 있게 된다.

## 싱글톤 패턴과의 차이점

우리는 플라이웨이트 패턴을 통해 종류별로 하나의 인스턴스만을 생성하여 나무를 심을 수 있었는데, 결과적으로 만들어진 나무 인스턴스의 개수는 **나무의 종류만큼 존재한다**. 나무 인스턴스가 나무 종류의 수**만큼 존재한다는 점이 싱글톤 패턴과의 핵심적인 차이점**이다.

싱글톤 패턴은 어떠한 조건에서도 인스턴스가 **단 한 개만 생성**되어야 한다. 즉, 위와 같은 조건을 달성하기 위해서 싱글톤 패턴을 사용하려면 나무를 종류별로 저장하고 사용하는 것이 아니라, 단 한개의 나무 객체만을 생성하고 생성할 때마다 x, y 값과 함께 type값도 수정하여 생성해야 한다는 것.

### Reference

https://velog.io/@hoit_98/디자인-패턴-Flyweight-패턴