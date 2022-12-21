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
  - MySQL(MyBatis)
- OS
  - Windows 10 & Mac
- API
  - Naver MAP API

홈페이지 :[경기요 - 경기대 맛집리스트](http://3.39.29.226:8080/map)

GitHub Repo : https://github.com/weonest/KguM.git

## 프로젝트를 만든 이유

프로젝트를 시작한 주요 목적은 지금까지 독학으로 공부한 개발 지식들을 실전으로 끌어내 활용하는 것이 가능한가를 확인하는 것이었습니다. HTML부터 시작하여 JAVA, Spring, Docker 등에 이르기까지 기존에 **배운 지식들을 직접** 써보지 않고서는 나의 것으로 만들었다고 할 수 없기에 알고 있는 모든 기술들을 최대한 활용해보고자 하였습니다.

또한, 이전까지 진행했던 프로젝트들은 배포단계까지는 이루어지지 않는 연습용 프로젝트였기 때문에 실제로 배포까지 가능한 나름의 기능을 갖춘 프로젝트를 만들어 보고 싶었습니다. 이를 만들어가는 과정에서 겪는 시행착오를 통해 더욱 성장할 수 있을 거란 확신도 있었습니다.



## 개발 환경 구성

본격적인 프로젝트를 진행하기에 앞서 Window 10과 Mac 두 가지 OS를 모두 사용하며 개발 공부를 하고 있었기 때문에 두 가지 환경을 넘나들며 개발할 수 있는 환경을 만드는 것이 중요하다고 생각하였습니다. 이를 위해 이전에 공부했었던 Docker를 활용하면 좋지 않을까? 라는 생각을 하여 Docker를 통해 Mysql DB를 관리하는 환경을 만들었습니다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5a361d33-f91d-4f3c-be20-04ef2cffbbf8/Untitled.png)

지금 생각해보면 굳이 Docker를 이용할 필요 없이 DB의 Dump를 만들어 옮겨가며 작업을 했었어도 됐겠지만, 당시에는 Dump를 만든다는 개념 자체를 몰랐으며 아직 개발에 대한 이해도가 낮았기 때문에 알고 있는 지식들로 직접 부딪쳐 가며 배워보자! 라는 다소 무식한? 방법으로 프로젝트를 진행했습니다.

시간이 흘러 프로젝트 후반부에서 배포 단계를 공부하면서 RDS (Relational Database Service)를 알게 돼어 Docker가 아닌 RDS를 적용하여 DB를 관리할 수 있도록 하였습니다.



## 1. DB

맛집 정보는 학교 게시판에서 설문조사를 통해 수집하였으며 프로젝트에서 사용된 DB 정보는 다음과 같습니다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/cb258435-ad58-4151-8dcf-c65174f4eeb1/Untitled.png)

좌표는 Google Map을 통해 수집하였으며, 컬럼명 camp는 수원캠퍼스(0), 서울캠퍼스(1)을 나타냅니다. 좌표 적용에 있어서 Mysql 상의 좌표값이 float의 유효숫자 10자리를 초과하여 반올림이 적용돼고 이때문에 의도치 않은 곳에 좌표 마커가 찍히는 문제점이 있었습니다. 이는 double을 사용하거나 float 표현 범위를 직접 지정해주는 방법으로 해결이 가능했습니다.



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

배포는 AWS의 EC2를 통해 완료하였습니다. 처음에는 Netlify라는 애플리케이션을 통해 배포할 생각이었는데, 서버에서 데이터를 받는 동적 사이트 배포는 불가능하다고 하여 EC2로 변경하였습니다.