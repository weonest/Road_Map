# 경기요 - 경기대맛집 review

------

경기요는 경기대생들의 식사 메뉴 고민 시간을 덜어드리기 위해 경기대 주변 맛집 정보를 제공하는 서비스입니다.

이번 프로젝트에서 사용한 스펙은 다음과 같습니다.

- IDE
  - Intellij IDEA Ultimate
- FrontEnd
  - HTML
  - CSS
  - Javascript
- BackEnd
  - Java 11
  - Spring Boot
    - JPA
    - thymeleaf
- DB
  - MySQL
- OS
  - Windows 10 & Mac
- API
  - Naver MAP API
- Server
  - AWS (EC2, RDS)

홈페이지 :

[경기요 - 경기대 맛집리스트](http://3.39.29.226:8080/map)

GitHub Repo : https://github.com/weonest/KguM.git

## 프로젝트를 만든 이유

프로젝트를 시작한 주요 목적은 지금까지 독학으로 공부한 개발 지식들을 실전으로 끌어내 활용하는 것이 가능한가를 확인하는 것이었습니다. HTML부터 시작하여 JAVA, Spring, Docker 등에 이르기까지 기존에 **배운 지식들을 직접** 써보지 않고서는 나의 것으로 만들었다고 할 수 없기에 알고 있는 모든 기술들을 최대한 활용해보고자 하였습니다.

또한, 이전까지 진행했던 프로젝트들은 배포단계까지는 이루어지지 않는 연습용 프로젝트였기 때문에 실제로 배포까지 가능한 나름의 기능을 갖춘 프로젝트를 만들어 보고 싶었습니다. 이를 만들어가는 과정에서 겪는 시행착오를 통해 더욱 성장할 수 있을 거란 확신도 있었습니다.

## 개발 환경 구성

본격적인 프로젝트를 진행하기에 앞서 Window 10과 Mac 두 가지 OS를 모두 사용하며 개발 공부를 하고 있었기 때문에 두 가지 환경을 넘나들며 개발할 수 있는 환경을 만드는 것이 중요하다고 생각하였습니다. 이를 위해 이전에 공부했었던 Docker를 활용하면 좋지 않을까? 라는 생각을 하여 Docker를 통해 Mysql DB를 관리하는 환경을 만들었습니다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5a361d33-f91d-4f3c-be20-04ef2cffbbf8/Untitled.png)

지금 생각해보면 굳이 Docker를 이용할 필요 없이 DB의 Dump를 만들어 옮겨가며 작업을 했었어도 됐겠지만, 당시에는 Dump를 만든다는 개념 자체를 몰랐으며 아직 개발에 대한 이해도가 낮았기 때문에 알고 있는 지식들로 직접 부딪쳐 가며 배워보자! 라는 다소 무식한? 방법으로 프로젝트를 진행했습니다.

RDS 사용 이전까지 Docker 사용에서 한 가지 큰 문제점이 있었는데, Docker 컨테이너 내부의 Guest OS에서 `vim`을 사용하지 못한다는 것이었습니다. Guest OS가 Oracle Linux Server로 설정되어 있었는데 Oracle Linux Server 에서 기본으로 제공하는 `yum` 외에도 `apt-get, dpkg, dnf` 등 모든 기능들이 작동하지 않아 `vim`을 다운받을 수 없었습니다. 이유는 잘 모르겠으나 이러한 오류로 인해 mysql 설정 파일인 my.cnf 등을 수정할 수가 없어 DB로의 외부 IP접속을 허용할 수 없다는 것이었습니다.

이는 배포 단계에서도 EC2의 탄력적 IP가 Docker 컨테이너 내부로 접속할 수 없음을 의미하기 때문에 시간이 흘러 프로젝트 후반부에서 배포 단계를 공부하면서 RDS (Relational Database Service)를 알게 돼어 Docker가 아닌 RDS를 적용하여 DB를 관리할 수 있도록 하였습니다.

## 1. DB

맛집 정보는 학교 게시판에서 설문조사를 통해 수집하였으며 프로젝트에서 사용된 DB 정보는 다음과 같습니다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/cb258435-ad58-4151-8dcf-c65174f4eeb1/Untitled.png)

좌표는 Google Map을 통해 수집하였으며, 컬럼명 camp는 수원캠퍼스(0), 서울캠퍼스(1)을 나타냅니다. 좌표 적용에 있어서 Mysql 상의 좌표값이 float의 유효숫자 10자리를 초과하여 반올림이 적용돼고 이때문에 의도치 않은 곳에 좌표 마커가 찍히는 문제점이 있었습니다. 이는 double을 사용하거나 float 표현 범위를 직접 지정해주는 방법으로 해결이 가능했습니다.

WorkBench를 통해 RDS에 직접 접속하는 것에서 문제가 발생한적이 있었는데, 이는 RDS 설정에서 보안그룹 인바운드 규칙을 편집해 주는 것으로 EC2의 탄력적 IP 뿐만아니라 제 로컬 IP도 접속 가능하도록 하였습니다.

## 2. 프로젝트 BackEnd

애플리케이션과 RDB의 테이블 매핑 방식으로는 JPA를 사용하였습니다. JPA에 대한 이해도가 아직은 많이 부족하지만, 기존에 공부해왔던 예제들을 활용하여 원하는 동작이 가능하게끔 만들어 낼 수 있었습니다.

```java
//SpringConfig
@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public MapService mapService(){
        return new MapService(mapRepository());
    }

    @Bean
    public MapRepository mapRepository() {
        return new JpaMapRepository(em);
    }
}
```

`MapService`와 `MapRepository`를 사용하기 위해 SpringConfig 파일을 생성하여 `@Bean` 을 등록해주었습니다.

```java
@Controller
public class MapController {

    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @RequestMapping("/map")
    public String list(Model model) {
        List<Map> maps = mapService.findMaps();
        List<Map> suwons = mapService.findSuwon();
        List<Map> seouls = mapService.findSeoul();

        model.addAttribute("maps", maps);
        model.addAttribute("suwons", suwons);
        model.addAttribute("seouls", seouls);
        System.out.println(maps.toString());
        return "mainMap";
    }
```

Controller에서는 전체리스트인 `maps`와 수원, 서울캠퍼스의 리스트인 `suwons`, `seouls`를 Model에 더해주도록 하였습니다.

```java
//Repository
public class JpaMapRepository implements MapRepository {

    private final EntityManager em;

    public JpaMapRepository(EntityManager em) {
        this.em = em;
    }

		@Override
    public List<Map> getCampus(long camp) {
        return em.createQuery("select m from Map m where m.camp = :camp")
                .setParameter("camp", camp)
                .getResultList();

    }

    @Override
    public List<Map> findAll() {   
        return em.createQuery("select m from Map m", Map.class)
                .getResultList();
    }
}
```

Repository에서는 JPA를 통해 MapRepository 인터페이스를 오버라이드하여 `getCampus`메소드와 `findAll` 메소드를 만들었습니다. `getCampus` 메소드는 `camp`변수를 입력받아 DB 속 맛집이 위치한 캠퍼스가 수원(0)인지 서울(1)인지 파악한 후 결과값을 List형태로 반환합니다.

```java
//Service
@Transactional
public class MapService {
    private final MapRepository mapRepository;

    @Autowired
    public MapService(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    public List<Map> findMaps() {
        return mapRepository.findAll();
    }

    public List<Map> findSuwon() {
        return mapRepository.getCampus(0);
    }
    public List<Map> findSeoul() {
        return mapRepository.getCampus(1);
    }

}
```

Service에서는 Controller에서 사용될 세 가지 메소드를 등록해두었습니다. 위 세 가지 메소드들은 Repository에서 Override한 메소드들을 통해 동작합니다.

## 3. 프로젝트 FrontEnd

BackEnd 개발자를 희망하고 있다보니 FrontEnd 부분이 미흡하여 어려움이 많았습니다. 완벽하진 않더라도 원하는 동작들이 구현될 수 있도록 수많은 시행착오를 경험하며 이번 프로젝트 내에서 가장 큰 성장을 이루어낸 부분이라고 생각합니다. 한 가지 아쉬운점이 있다면  **mainMap.html** 파일에 모든 코드가 들어가 있다는 점입니다.

프로젝트 내부에서 JavaScript, CSS, HTML 파일을 분리하여 관리하고 싶었지만, 어째서인지 Improt작업에서 계속해서 오류가 발생하였습니다. 초반에는 경로 자체를 참조하지 못하는 오류가 발생하여 `**application.properties**` 파일에서 `spring.web.resources.static-locations=classpath:/templates/` 을 작성해주는 것으로 templates 폴더 내부에 있는 경로의 파일들을 참조 가능할 수 있게끔 하였습니다.

하지만 이후에도 계속해서 발생하는 다양한 문제들의 해결 방법을 찾지 못하여 결국에는 **mainMap.html** 파일에 모든 코드가 들어가야만 하는 불상사가 발생하였습니다. php방식이 아닌 thymeleaf 방식으로 진행한 부분에서 생긴 문제인 것 같은데 아직은 정확한 원인을 모르겠습니다. 이는 추후에 꼭 해결하도록 하겠습니다.

```java
// 사이드바 리스트 생성하기
<div id="suSidenav" class="sidenav">
     <a href="javascript:void(0)" class="closebtn" onclick="closeSu()">&times;</a>
         <script th:inline="javascript">
              var cnt = 0;
              var ListArr = [];
              for (var i = 0; i < [[${suwons}]].length; i++) {

                 var element = document.createElement("a");
                 element.style.fontSize = "20px";
                 element.href = "#";
                 element.id = "list" + cnt++;      
                 ListArr.push(element.id);
                 element.text = [[${suwons}]][i].name;

                 var img = document.createElement("img");
                 img.setAttribute("src", "<https://cdn-icons-png.flaticon.com/128/2276/2276931.png>");
                 img.setAttribute("width", "16");
                 img.setAttribute("height", "16");
                 element.prepend(img);

                 var suSidenav = document.getElementById("suSidenav");
                 suSidenav.appendChild(element);

                 document.write('<p class="smP" href="#" style="font-size: 16px">' + [[${suwons}]][i].des + '</p>')
                 document.write('<p class="smP" href="#" style="font-size: 14px">' +
                  '<img src="<https://cdn-icons-png.flaticon.com/128/1828/1828884.png>" width="14">' +
                  '' + [[${suwons}]][i].star + "&nbsp;&nbsp;&nbsp;" + [[${suwons}]][i].sum + '</p><br>')

                }
         </script>
</div>
// 각 리스트에 이벤트 등록
for (var i = 0, ii = ListArr.length; i < ii; i++) {
        document.getElementById(ListArr[i]).addEventListener('click', ListHandler(i));
    }

    function ListHandler(seq) {
        return function (e) {
            var gMarkers = getMarkers();
            var gInfoWindow = getInfoWindows();
            var marker = gMarkers[seq];
            var infowindow = gInfoWindow[seq];

            if (infowindow.getMap()) {
            } else {
                infowindow.open(map, marker)
            }
            var pos = marker.getPosition();
            map.panTo(pos);
        };
    }
```

반응형 SideBar에서 표현될 리스트를 만드는 부분입니다. Controller에서 전달된 DB값을 가져오는 방식으로는 thymeleaf의 `th:inline` 기능을 사용하였습니다. 반응형 SideBar는 API에서 제공하는 것이 아닌 직접 만들어야 하는 기능이었는데, 어떻게 Controller에서 DB를 가져올 것이며, 어떻게 DB값을 화면에 표현할 것인지 고민하는 과정이 정말 재밌었던 시간이었습니다.

초기에는 반복문 내에서 `document.write` 를 사용하여 생성되는 a태그 내부에 데이터를 가져와 표현하는 방식으로 작성하였지만, 이는 후에 `EventListener` 를 추가하는 과정에서 반복문으로 생성된 a태그 전체가 같은 `id`값을 갖게되어 각각의 a태그에 개별적으로 `EventListener` 적용시킬 수 없다는 문제점이 있었습니다. 이는 반복문 내에서 `create.Element`를 통해 생성하여 고유의 `id`값을 가질 수 있도록 하여 해결할 수 있었습니다.

`EventListener` 를 사용하기 까지에도 많은 난관이 있었습니다. 처음에는 태그 내에서 `onclick` 을 통해 좌표로의 이동 메소드를 작동시킬 계획이었으나 각각의 좌표값에 대응하게끔 메소드를 만드는 부분에서 해답을 찾지 못하여 `EventListener` 방식으로 변경하였습니다.

네이버 지도 API를 사용하는 부분에 있어서는 처음 오픈 API를 사용하는 것이다보니 막막한 감정이 앞섰지만, 구글링과 네이버에서 제공하는 예제들을 통해 API와 통신하는 방법 부터 시작하여 활용과 변형이 가능할 정도로 성장하게 되면서 자신감이 붙었던 것 같습니다.

```java
var markers = [],
    infoWindows = [];

    for (let i = 0; i < [[${maps}]].length; i++) {
        var name = [[${maps}]][i].name;
        var des = [[${maps}]][i].des;
        var star = [[${maps}]][i].star;
        var x = [[${maps}]][i].x;
        var y = [[${maps}]][i].y

        var contentString = [
            '<div class="info">' +
            '<div class="right">' +
            '<h2>' +
            '<img src="<https://cdn-icons-png.flaticon.com/512/3183/3183463.png>" width="20" height="20">' +
            name,
            '</h2>' +
            '<p class="des">' +
            des,
            '</p' +
            '><p>' +
            '<img src="<https://cdn-icons-png.flaticon.com/128/1828/1828884.png>" width="12" height="12">' +
            star,
            '</p></div>' +
            '</div>',
        ].join('');

        var marker = new naver.maps.Marker({
            map: map,
            position: new naver.maps.LatLng(x, y)
        })
        var infowindow = new naver.maps.InfoWindow({
            content:
            contentString,
            backgroundColor: "",
            borderWidth: "",
            disableAnchor: true,
            anchorSkew: false,
            anchorColor: "",
        });
        markers.push(marker);
        infoWindows.push(infowindow)
    }
```

반복문의 마지막 단계에서 입력된 `marker` 값과 `infowindow` 값을 `markers, infoWindows` 배열에 담아준 후에 뒤에서 반복문을 통해 모든 값에 메소드 기능들이 적용될 수 있도록 하였습니다. 적용시킬 메소드로는 `updateMarkers`, `showMarker`, `hideMarker`, `getClickHandler` 를 만들었으며 이는 네이버 예제를 활용해 만들었기 때문에 여기서는 다루지 않도록 하겠습니다.

## 4. 배포

애플리케이션의 배포는 AWS의 EC2를 통해 완료하였습니다. 처음에는 Netlify라는 애플리케이션을 통해 배포할 생각이었는데, 서버에서 데이터를 받는 동적 사이트 배포는 불가능하다고 하여 EC2로 변경하였습니다.

배포 과정에서 겪은 가장 큰 난관은 터미널에서 프로젝트 빌드시 DB와 연결되지 못하던 오류였습니다. 이는 위에서도 다뤘듯이 Docker 컨테이너 내부로 외부 IP접속 불가 문제였기에 RDS로 변경하는 것으로 해결할 수 있었습니다.

EC2 사용에도 어려움이 많았지만, 여러 EC2 기술 자료들을 공부하여 기본적인 설정부터 시작하여 백그라운드 실행을 통해 터미널 종료시에도 계속 동작하도록 만들기까지 성공적인 배포를 마칠 수 있었습니다.

### 시연장면 (웹, 모바일)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/76a0e07f-a158-40bc-a7a6-652f989ceeac/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/95e56df9-b844-4c14-81ee-fef6eab384fe/Untitled.png)

## 5. 마치며

이번 프로젝트는 기존에 배운 지식들을 나의 것으로 만들기위해 무모하게 부딪친 프로젝트이다보니 예상했던 것 보다 개발 기간이 길어지게 되었습니다. 하지만, 정말 많은 오류들을 직접 경험해보고 해결함으로써 프로젝트 진행 이전과는 비교도 안 될 정도로 큰 성장을 이루어 낸 것 같아 만족스러운 경험이었습니다. 배포 이후에 기대 이상으로 많은 사람들의 격려와 응원 그리고 피드백 등을 받을 수 있었던 것도 개발자란 꿈을 향해 달려가는 저에게 큰 동기 부여가 되었습니다.

아직 수정해야 할 부분도 많고 프로젝트를 계획했을 시에 구상했던 기능들 (검색, 공감하기 등) 이 모두 완성된 것은 아니지만, 표면적인 이용에는 문제가 없을 정도의 구현은 끝냈다고 생각하기 때문에 이번 프로젝트는 여기서 잠시 쉬어가도록 하겠습니다.

이제는 프로젝트를 진행하며 느꼈던 부족한 점들 서버와의 통신 방법, 네트워크, JPA 등을 제대로 공부해 보는 시간을 갖도록 하겠습니다.

2023-01-13

프로젝트 대대적인 수정작업 진행중

ㄴ 리뷰가 아닌 포트폴리오로 최종 진행

------

# 프로젝트 re:review

## Repsitory 수정?

- 현재는 JpaMapRepository가 MapRepository를 상속 (implements) 하는 형태로 코드를 작성하였지만, MapRepository가 JpaRpository를 상속 (extends) 하는 형태로 코드를 짜는 편이 좋지 않았을까 생각한다

## `spring.jpa.hibernate.ddl-auto` 사용시 주의 사항

- create 옵션은 기존의 테이블을 drop하고 새로 create하기 때문에 중요한 데이터를 날릴 수도 있다
- update : 기존의 DB에 새로운 컬럼을 추가해야 하는 상황이 올 수 있는데, 이때 update를 사용하면 alter문이 실행돼서 db가 업데이트 되는 것은 맞지만, update 옵션 유지한 채로 다른 작업을 하다가 기존에 존재하던 컬럼이 삭제되는 경우도 있기 때문에 `운영`단에서는 절대 사용하지 말것

## @NoArgsConstructor, @RequiredArgsConstructor, @AllArgsConstructor

- @NoArgsConstructor = 파라미터가 없는 기본 생성자를 만들어 준다

  - 즉, @NoArgsConstructor가 붙어있는 객체의 인스턴스를 만들 때, 아래처럼 argument를 하나도 넘기지 않아도 생성자를 호출할 수 있다

  ```java
  @NoArgsConstructor
  public class Customer {
  		private Long id;
  		private String name;
  		private int age;
  }
  Customer cus = new Customer();
  ```

  하지만, 만약 항상 초기화가 필요한 `final`이 붙은 field가 있는데 @NoArgsConstructor를 사용한다면 Compile Error가 발생할 것이다.

  대신, @NoArgsConstructor(force=true)처럼 force라는 옵션에 true 값을 주면, 모든 final fields는 `0 / false / null` 로 초기화된다

  그런데, field에 final이 아닌 @NonNull 같은 제약이 있는 애노테이션이 붙어있다면, force = true를 주어도 생성자에 들어가지 않기 때문에 나중에 프로그래머가 할당해 주어야 한다

  hibernate나 Service Provider Interface 같은 특정 Java 구성에서 필요로 하고, 주로 @Data나 애노테이션을 생성하는 생성자 등과 함께 사용된다

- @RequiredArgsConstructor = 특별한 처리가 필요한 field 마다 하나의 parameter를 갖는 생성자를 생성해 준다

  - 초기화되지 않은 모든 final fields와, 선언될 때 초기화지 않은 @NonNull로 표시된 filed까지 parameter를 가진다
  - 특히 @NonNull이 달려있는 field 의 경우, 생성되는 생성자 내부에서 명시적인 null 체크 로직 또한 생성된다
  - 그래서 만약 @Nonnull이 붙어 있는 field 중 어떠한 것이라도 null 값을 포함한다면 NullPointerException이 발생하게 된다
  - 생성자의 Parameter의 순서는 클래스 내부에서 선언된 filed의 순서로 매칭 된다

  ```java
  @RequiredArgsConstructor
  public class Customer {
      private final Long id;
      private String name;
      private int age;
  }
  
  Customer customer = new Customer(3L);
  ```

- @AllArgsConstructor = 클래스 내부에 선언된 모든 filed 마다 하나의 parameter를 가진 생성자를 생성한다

  - @NonNull이 붙어 있는 field의 경우, 역시나 생성되는 생성자 내부에 해당 parameter에 null check 로직이 생성된다
  - @RequiredArgsConstructor와 마찬가지로, 생성자의 parameter의 순서는 내부 클래스에 선언된 filed의 순서로 매칭 된다

  ```java
  @AllArgsConstructor
  public class Customer {
      private final Long id;
      private String name;
      private int age;
  }
  
  Customer customer = new Customer(2L, "김철수", 23);
  ```

- **`주의사항 및 단점`**

  - @RequiredArgsConstructor, @AllArgsConstructor 는 심각한 버그를 발생할 수 있어서 사용 시에 주의하거나, 아예 사용을 권하지 않는 경우도 있다

  ```java
  @AllArgsConstructor
  public class Order {
      private int cancelAmount;
      private int orderAmount;
  }
  
  // 취소수량 4개, 주문수량 5개
  Order order = new Order(4, 5);
  ```

  위에서 언급했던 것처럼 @AllArgsConstructor 는 생성자를 생성할 때, class 내부에 선언된 field의 순서로 생성자 파라미터를 생성한다.

  그런데 만약 프로그래머가 선언된 순서를 임의로 바꾸게 된다면? 이 경우 ,**IDE가 제공해주는 리팩토링은 작동하지 않게 되고, Lombok도 변화를 알아채지 못한다.** 그렇기에 필드의 순서가 변경되어도 `Order order = new Order(4, 5)`는 에러없이 잘 작동하지만, 실제로 입력되는 값은 취소수량과 주문수량이 뒤바뀌어 들어가는 심각한 비즈니스 로직 에러를 발생시킨다

  그렇기에 IDE 자동 생성 기능 등으로 생성자를 직접 만들거나, 생성자에 `@Builder` 애노테이션을 붙이는 것을 권장하기도 한다

  ```java
  public class Order {
      private int cancelAmount;
      private int orderAmount;
      
      @Builder
      private Order(int cancelAmount, int orderAmount) {
          this.cancelAmount = cancelAmount;
          this.orderAmount = orderAmount;
      }
  }
  
  // field 순서를 변경해도 에러가 없다.
  Order order = Order.builder().orderAmount(5).cancelAmount(4).build();
  ```

## @RestController (출처 : https://dncjf64.tistory.com/288, https://milkye.tistory.com/283)

### **1.개요**

Spring MVC의 @RestController은 @Controller와 @ResponseBody의 조합입니다.

Spring 프레임 워크에서 RESTful 웹 서비스를 보다 쉽게 개발할 수 있도록 Spring 4.0에서 추가되었습니다.

근본적인 차이점은 @Controller의 역할은 Model 객체를 만들어 데이터를 담고 View를 찾는 것이지만, @RestController는 단순히 객체만을 반환하고 객체 데이터는 JSON 또는 XML 형식으로 HTTP 응답에 담아서 전송합니다. 물론 @Controller와 @ResponseBody를 사용하여 만들 수 있지만 이러한 방식은 RESTful 웹서비스의 기본 동작이기 때문에 Spring은 @Controller와 @ResponseBody의 동작을 조합한 @RestController을 도입했습니다.

다음 두 코드는 Spring MVC에서 동일한 동작을 합니다.

```java
@Controller
@ResponseBody
public class MVCController{
	logic...
}

@RestController
public class ReftFulController{
	logic...
}
```

### **2. Spring에서 @Controller와 @RestController은 무엇인가?**

@Controller은 뷰에 표시될 데이터가 있는 Model 객체를 만들고 올바른 뷰를 선택하는 일을 담당합니다. 또한, @ResponseBody를 사용하여 HTTP Response Body에 데이터를 담아 요청을 완료할 수 있습니다.

HTTP Response Body에 데이터를 담는 것은 RESTful 웹 서비스에 대한 응답에 매우 유용합니다. 왜냐하면 뷰를 반환하는 대신 데이터를 반환하기 때문입니다.

Spring4 이전에 RESTful 웹 서비스를 개발했다면 @Controller와 @ResponseBody의 조합 사용에 익숙했을 것입니다. 하지만 @RestController을 사용하여 동일한 기능을 제공할 수 있습니다. 요컨대 @Controller와 @ResponsBody의 동작을 하나로 결합한 편의 컨트롤러라 보시면 됩니다.

> 일반적인 Spring MVC 처리과정
>
> https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbXvA4D%2FbtqW4gE9bMH%2FTzOqxMdEnRXTAVqaLre5TK%2Fimg.png

### 쉬운 예제

RestController를 사용하면 어떠한 결과물이 나오는지 보도록 하자.

```java
@RestConroller
public class Demo {
	
	@GetMapping("/test")
	public String root() {
			return "Hello_World";
	}
}
```

기본적으로 스프링에서 @Controller 애노테이션을 사용해 return Hello_World라고 한다면, 기본적으로 Hello_World.(jsp or html 등) 이 출력이 된다. 하지만 RestController를 사용한다면 다음과 같이 VIEW가 호출 되는 것이 아니라 return Data가 그대로 화면에 출력 된다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/86ea71ff-c320-4a5f-973b-5cd034ce3be0/Untitled.png)

### 전역 예외처리 (Global Exception Handling)

- 사용 이유

  - 프로젝트 내에서 게시판 구현을 JPA와 Rest API 기반의 비동기 방식으로 구현하기로 했다
  - 즉, 페이지를 처리하는 Controller API를 처리하는 Controller를 따로 구성하게 된다
  - API를 처리하는 RstController 전역에서 공통된 예외 처리를 적용하기 위함

- @RestControllerAdvie

  - 스프링은 예외 처리를 위해 @ControllerAdive 와 @ExceptionHandler 등의 기능을 지원해 준다
  - @ControllerAdvie는 컨트롤러 전역에서 발생할 수 있는 예외를잡아 Throw 해주고, @ExceptionHandler는 특정 클래스에서 발생할 수 있는 예외를잡아 Thorw 해준다
  - 일반적으로 @ExecptionHandler는 @ControllerAdive가 선언된 클래스에 포함된 메서드에 선언한다

  > 예시
  >
  > ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/bc17cf09-b690-4626-a8f6-036ae9a26a38/Untitled.png)

  - 이번 프로젝트에서는 페이지에 대한 예외 처리는 의미가 없기 때문에 API 통신을 하는 Controller에만 적용하면 되어 @RestControllerAdive를 선언했다 (@RestController와 마찬가지로 @ResponseBody가 적용된 형태)

  > **비동기 방식에 대한 에러를 지정한 이유?**
  >
  > **Enum class ErrorCode에서 AllArgsConstructor 사용 이유?**

### Stream

- 자바의 스트림은 컬렉션에 저장되어 있는 요소들을 하나씩 참조하여 람다식으로 처리할 수 있도록 해주는 코드패턴이다
- 스트림은 람다식과 함께 사용하기 때문에 데이터에 대한 처리를 매우 간결하게 작성할 수 있다는 장점과 내부 반복자라는 것을 사용하기 때문에 병렬처리가 쉽다는 장점이 있다

```java
# 스트림 생성 - 컬렉션(list)
ArrayList<String> list = new ArrayList<String(Arrays.asList("a","b","c","d","e"));
Stream stream = list.stream();

# - 배열
Stringp[ arr = new String[] {"a","b","c","d","e"};
Stream<String> Stream1 = Arrays.stream(arr);

# map(): 요소들을 조건에 맞게 변환할 수 있다

public static void main(String args[]){
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("ball");
        list.add("car");
        list.add("daddy");
        list.add("ear");
        list.add("fox");

        // 요소들을 조건에 해당하는 값으로 변환 해준다.
        // map

        System.out.println("==========stream1=========");
        Stream stream1 = list.stream().map(s -> s.toUpperCase()); // 반복문이 코드상에 노출X
        stream1.forEach(System.out::println);

        System.out.println("=========stream2==========");

        Stream stream2 =list.stream().map(String::toUpperCase); // 반복문이 코드상에 노출X
        stream2.forEach(System.out::println);

        System.out.println("==========원본데이터=========");
        // 원본 데이터는 변경되지 않는다.
        // list.forEach(System.out::println);
        list.forEach(s -> System.out.println(s)); // "System.out::println"와 동일 = 람다 표현식

<결과>

==========stream1=========
APPLE
BALL
CAR
DADDY
EAR
FOX
=========stream2==========
APPLE
BALL
CAR
DADDY
EAR
FOX
==========원본데이터=========
apple
ball
car
daddy
ear
fox

# collect() : Stream 데이터를 원하는 자료형으로 변환 해준다.

public static void main(String args[]){
        List<String> list = new ArrayList<>(Arrays.asList("apple","ball","car","daddy","ear","fox"));

        System.out.println("===============counting()===============");
        Long count =  list.stream().filter(s -> s.length() > 3).collect(counting());
        System.out.println("count - " + count);

        System.out.println("================Collectors.toList==============");
        List nList =  list.stream().filter(s -> s.length() > 3).collect(Collectors.toList());
        System.out.println(nList);

        System.out.println("================Collectors.joining==============");

        // list의 아이템을 1개의 String으로 만들기 ( 구분자 "," )
        String longStr =  list.stream().filter(s -> s.length() > 3).collect(Collectors.joining(","));
        System.out.println(longStr);

        System.out.println("===============Collectors.toMap===============");

        Map map =  list.stream().filter(s -> s.length() > 3).collect(Collectors.toMap(s -> s, s-> s.length()));
        map.forEach((a, b) -> {
            System.out.println(a + " " + b);
        });

        System.out.println("===============toArray()===============");
        Object[] obj  =  list.stream().filter(s -> s.length() > 3).toArray();
        for (Object o : obj) {
            System.out.println((String) o);
        }
    }
 

<결과>

===============counting()===============
count - 3
================Collectors.toList==============
[apple, ball, daddy]
================Collectors.joining==============
apple,ball,daddy
===============Collectors.toMap===============
ball 4
apple 5
daddy 5
===============toArray()===============
apple
ball
daddy
```

d