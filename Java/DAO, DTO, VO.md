# DAO, DTO, VO

출처 : https://bperhaps.tistory.com/entry/Repository와-Dao의-차이점

https://kkminseok.github.io/posts/Spring_semina/

> 들어가기에 앞서 DAO와 Repository의 차이점부터 시작한다
>
> 둘의 차이에 대해서는 다음의 선행지식이 필요하다
>
> 1. 객체지향
> 2. DAO
> 3. DDD
> 4. 기본적인 웹 설계 구조

### DAO란 무엇일까?

DAO (Data Access Object)는 J2EE에서 등장한 개념이다.

애플리케이션을 사용하다보면 영구저장소 매커니즘이 필요할때가 매우 많아진다. 이때 영구저장소의 구현체는 정말 무수히 많다. (Oracle, Mysql, MongoDB …) 그렇다면 애플리케이션에서 영구저장소에 접근하기 위해서는 어떻게 하면 될까? 정답은 각 영구 저장소 밴더에서 제공하는 API를 사용하면 된다.

하지만, 이 방식에는 여러 문제점이 있다.

- 첫 번째, 구현체와 로직이 너무 강한 결합을 가지게 된다.

만일 내가 Mysql을 쓰고 있었다고 가정해 보자. 이때 Oracle로 바꿔야 한다는 요구사항이 들어왔다면, 우리는 Mysql의 API를 사용한 모든 구현을 변경해야 할 것이다. 즉, 변경에 자유롭지 않을 것이다. (**OCP 위반 = OCP 찾아보자**)

- 두 번째, 레이어가 깨진다

일반적으로 웹 애플리케이션을 만들 때 우리는 레이어를 나눠서 설계를 진행한다.

https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FceyiML%2Fbtq3b98T62p%2FckDFQh5eK1B8SP4HQqCkqK%2Fimg.png

예시를 위해 일반적인 웹 애플리케이션의 아키텍쳐를 가지고 왔다. 어렵게 볼 필요 없다. 표현계층은 흔히 view, 응용 계층은 service, 도메인은 비즈니스 로직(도메인), infrastructure는 db, 외부 라이브러리.. 등 애플리케이션을 만드는데 필요한 인프라들을 의미한다.

흔히 퍼시스턴트 레이어라고 부르는게 인프라 스트럭쳐에 속해있다고 말할 수 있다.

이때, 우리의 예시에서 Mysql의 API를 서비스 로직에서 사용하기 위해 Application 계층에서 new 하여 생성했다고 가정해 보자. 이 순간 무슨일이 발생하는가? service 로직을 담당하는 객체와 db와 관련된 API가 강한 결합을 가지며, 영속성과 관련된 로직이 서비스 로직에 생성된다. 즉, 인프라와 응용계층이 섞여 버리는 일이 발생한다

- 세 번째, 개발자의 러닝커브가 증가한다.

밴더마다 자신의 영구저장소의 API 설계가 완전히 동일할 수는 없다. 회사마다, 그리고 그 특징마다 서로 다른 구현을 가질 것이다. 이는 새로운 밴더 API를 사용할 때 마다 사용자에게 학습에 대한 부담을 증가시키며 러닝커브를 향상시킨다.

이런 문제점을 해결하기 위해 나온 패턴이 DAO패턴이다. DAO는 밴더들의 API와 로직 사이에 있는 어댑터와 같은 역할을 한다.

먼저, DAO가 어댑터의 역할을 수행함으로써 밴더들 사이의 구현의 차이점을 극복한다. 이를 통해 세 번째 문제점을 해결할 수 있다. 또한 이를 통해 얻을 수 있는 이점으로 강한 결합을 해결했다. 밴더의 구현체를 그대로 사용하는 것이 아니라 DAO라는 객체로 한 번 더 감쌈으로서 내가 사용하는 데이터 소스가 변경되더라도 그 로직에는 변화가 없도록 한다. 이를 통해 첫 번째, 세 번째 문제점을 해결한다.

그러면 두 번째 문제는 어떻게 해결할까? 이는 DAO의 시퀸스 다이어그램을 보면 알 수 있다.

https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcLUqGx%2Fbtq3buTgxD7%2Fk1S2KrO8KPK3yQRZL4pLG0%2Fimg.webp

그림을 보면 데이터를 가지고 오는데 있어서 TransferObject를 사용하는 것을 볼 수 있다. 이는 우리가 주로 말하는 DTO와 같은 개념이다 (혹은 VO 라고 알고있을 수도 있다. 하지만 엄밀히 말하면 이런 계층간의 데이터이동을 위해 사용되는 객체는 TO또는 DTO가 맞다. )

이때 우리는 명심해야 할 부분이 있다. DTO는 말 그대로 “데이터”를 전송하기 위한 객체이다. Entity 그 자체를 전송해서는 안 된다. 여기서 하고싶은 말이 무엇인고 하니, 가끔 구현들을 보면 DAO를 사용하는데 그 반환값으로 도메인 객체 (Entity)를 바로 내보내거나, DAO로 데이터를 저장하는데 Entity를 그대로 인수로 받는 경우가 있는데, 이는 완전히 잘못된 구현이다. 서로간의 레이어를 존중해야만 한다

### Repository란 무엇인가?

DDD에서 처음 등장한 개념이다. 많은 사람들이 Repository를 객체의 컬렉션이라고 부른다. 왜 그렇게 부르는지 한 번 확인해 보도록 하자.

원칙적으로는 Repository는 “객체의 상태를 관리하는 저장소”이다 즉, Entity(도메인 객체라고 생각하면 된다) 그 자체를 저장하고 불러오는 역할을 한다. 그러니 당연하게 Repository는 도메인 레이어에 대한 지식을 알고 있어야 한다.

> 엄밀히 말하면 여기서의 Entity는 DDD의 애그리거트를 기준으로 한다. 일단은 쉽게 도메인 객체라고 생각하자

정확히, Repository는 영구 저장소를 의미하는 게 아니다. 말 그대로 “객체의 상태를 관리하는 저장소”일 뿐이다. 즉, Repository의 구현이 파일시스템으로 되든, 아니면 HashMap으로 구현됐든지 상관 없다.그냥 객체에 대한 CRUD를 수행할 수 있으면 된다.

이와 같은 이유로 일반적으로 Repository의 인터페이스를 도메인 로직에 넣어둔다. 여기서 바로 Repository는 도메인 레이어 라는 말이 나온 것. (명심. 인터페이스 = 도메인 레이어)

------

### DTO와 Entity를 분리하는 이유

- View Layer와 DB Layer의 역할을 철저하게 분리하기 위해서이다.
- 테이블과 매핑되는 Entity 클래스가 변경되면 여러 클래스에 영향을 끼치게 되는 반면 View와 통신하는 DTO클래스는 자주 변경되므로 분리해야 한다.
- Domain Model을 아무리 잘 설계했다고 해도 View 내에서 Domain Model의 getter만을 이용해서 원하는 정보를 표시하기 어려운 경우가 있다고 함. 이런 경우 Presentation을 위한 필드나 로직을 추가하게 되는데, 이러한 방식이 모델링의 순수성을 깨고 Domain Model 객체를 망가뜨리게 됨.
- 또한 Domain Model을 복잡하게 조합한 형태의 Presentation 요구사항들이 있기 때문에 Domain Modl을 직접 사용하는 것은 어려움.
- 즉 DTO는 Domain Model을 복사한 형태로, 다양한 Presentation Logic을 추가한 정도로 사용하며 Domain Model 객체는 Persistent만을 위해서 사용한다.
- 여기서 Persentation은 View계층, persistent는 인프라스트럭쳐 = DB, 외부 라이브러리 등의 인프라

![https://kkminseok.github.io/assets/img/sample/JAVA/study/semina/DTODOMAIN.JPG](https://kkminseok.github.io/assets/img/sample/JAVA/study/semina/DTODOMAIN.JPG)

------

출처 : https://dkswnkk.tistory.com/500

### VO (Value Object)

**VO는 DTO와 달리 Read-Only속성을 지닌 값 오브젝트**입니다. **DTO**는 **setter를 가지고 있어서 값이 변할 수 있지만** **VO의 경우에는 getter만 가지고 있어서 수정이 불가능**합니다.

**DTO와 VO의 차이점**은 **DTO는 인터턴스 개념**이고, **VO는 리터럴 값 개념**입니다. VO는 값들에 대해 Read-Only를 보장해줘야 존재의 신뢰성이 확보되지만 DTO의 경우에는 단지 데이터를 담는 그릇의 역할일 뿐 값은 그저 전달되어야 할 대상일 뿐입니다. **따라서 값 자체에 의미가 있는 VO와 전달될 데이터를 보존해야 하는 DTO의 특성상 개념이 다릅니다.**

**따라서 VO의 핵심은 두 객체의 모든 필드 값들이 동일하면 두 객체는 같다**입니다. 따라서 완전히 값 자체 표현 용도로만 사용하는 게 목적이라면, 두 객체의 모든 필드 값들이 모두 같으면 같은 객체이도록 만드는 것(equals() 와 hashCode()의 오버라이딩)이 중요하지, 메소드는 어떤 메소드가 있든 말든 상관 없습니다.