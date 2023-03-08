# Thymeleaf (게시판 만들기)

- 문법

```java
<p th:text = "#{home.welcome}">
# = 메세지를 정의하는 표시

<th:href = "@{css/gtvg.css}">
@ = 파일의 주소를 나타내는 표시

<th:utext>
공부

$ = 전달값 (변수)

+

<https://yeonyeon.tistory.com/153>
```

## fragment의 사용

```java
#####**index.html**######

<!doctype html>
<html xmlns:th="<http://www.thymeleaf.org>">
<head th:replace="fragment/common::head('home')">

</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top" 
th:replace="fragment/common::menu('home')"> 

</nav>

<main role="main" class="container">

    <div class="starter-template">
        <h1>Bootstrap starter template</h1>
        <p class="lead">Use this document as a way to quickly start any new project.<br> All you get is this text and a mostly barebones HTML document.</p>
    </div>

</main><!-- /.container -->
<script src="<https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js>" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
<script src="<https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js>" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="<https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js>" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
</body>
</html>
```

- fragment 를 사용할 때 `<head th:replace="fragment/common::head('home')">`와 같이 파라미터를 넘겨줄 수 있다.
- 이러한 파라미터는 다음과 같이 사용된다

```java
#######**common.html**#########

<!DOCTYPE html>
<html xmlns:th="<http://www.thymeleaf.org>">
<head th:fragment="head(title)">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css>" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link href="starter-template.css" th:href="@{/starter-template.css}" rel="stylesheet">

    <title th:text="${title}">Demo</title>

</head>
<body>
     <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top" th:fragment="menu(menu)">
         <a class="navbar-brand" href="#">Spring Boot Tutorial</a>
         <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
             <span class="navbar-toggler-icon"></span>
         </button>

         <div class="collapse navbar-collapse" id="navbarsExampleDefault">
             <ul class="navbar-nav mr-auto">
                 <li class="nav-item" th:classappend="${menu} == 'home'? 'active' : '' ">
                     <a class="nav-link" href="#" th:href="@{/}"> 지도 <span class="sr-only" th:if="${menu} == 'home'"></span></a>
                 </li>
                 <li class="nav-item" th:classappend="${menu} == 'board' ? 'active' : '' ">
                     <a class="nav-link" href="#" th:href="@{/board/list}"> 게시판 <span class="sr-only" th:if="${menu} == 'board'"></span></a>
                 </li>
             </ul>
         </div>
     </nav>
</body>
</html>
```

- `/fragment/common` 속 `menu`를 `list.html`이나 `form.html`에서 `th:replace`를 통해 사용할 때 파라미터를 입력하게 되고 입력받은 파라미터를 위에서 강조 표시한 삼항연산자에서 쓰임

## @Size 사용과 Validation (유효성 검사)

```java
package com.example.god.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "board")

public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max=30, message = "제목은 2자 이상 30자 이하입니다.")
    private String title;
    private String content;
    private String writer;
    private int hits;
    private String password;
    private LocalDateTime createdDate = LocalDateTime.now();

}
```

- 위 애노테이션을 사용하기 위해서는 `implementation 'javax.validation:validation-api:2.0.1.Final'`를 넣어줘야 한다

```java
**########form.html########**
<form action="#" th:action="@{/board/form}" th:object="${board}" method="post">
        <div class="form-group">
            <label for="title" >제목</label>
            <input type="text" class="form-control"
                   th:classappend="${#fields.hasErrors('title')} ? 'is-invalid' : ''"
                   id="title" th:field="*{title}">
            <div class="invalid-feedback" th:if="${#fields.hasErrors('title')}"
                 th:errors="*{title}">
                제목 에러 메시지
            </div>
        </div>
        <div class="form-group">
            <label for="content" class="form-label">내용</label>
            <textarea class="form-control" id="content" th:field="*{content}"
                      th:classappend="${#fields.hasErrors('content')} ? 'is-invalid' : ''"></textarea>
            <div class="invalid-feedback" th:if="${#fields.hasErrors('content')}"
                 th:errors="*{content}">
                내용 에러 메시지
            </div>
        </div>
package com.example.god.validator;

import com.example.god.domain.Board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class BoardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        Board b = (Board) obj;
        if (StringUtils.isEmpty(b.getContent())) {
            errors.rejectValue("content", "key", "내용을 입력하세요");

        }

    }
}
```

- 위의 form.html과 BoardValidator 클래스를 통해 유효성 검사를 실시할 수 있었는데, form.html에서 타임리프 문법을 통해 진행된 유효성 검사는 제목에만 해당되며 BoardValidator 클래스를 통해 진행된 유효성 검사는 내용에만 해당된다
- 두 가지 경우를 모두 능숙하게 다룬다면 좋겠지만, 하나만 다룬다면 Validator 클래스를 통해 다루는 것이 좋다고 한다

## 제목 클릭

```java
<tr th:each="board : ${boards}">
            <td th:text="${board.id}"></td>
            <td><a th:text="${board.title}" th:href="@{/board/form(id=${board.id})}"></a></td>
            <td th:text="${board.writer}"></td>
            <td th:text="${board.hits}"></td>
        </tr>
@GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {

        if (id == null) {
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }

        return "board/form";
    }
```

- `th:href="@{/board/form(id=${board.id})}"` 이 문법을 통해 `/board/form`에 i`d=?` 와 같은 형태로 파라미터를 전달할 수 있었고, 컨트롤러에서 받은 파라미터를 통해 해당 id를 찾아 반환한다

## Spring Data JPA

https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation

### 총 건수 표시 (Pageable)

```java
#기존
<div>총 건수 : <span th:text="${#lists.size(boards)}"></span></div>

#변경
<div>총 건수 : <span th:text="${boards.totalElements}"></span></div>
```

- 기존은 타임리프 문법인 `#lists.size`를 통해 총 건수를 표시
- 변경은 컨트롤러에서 전달받은 객체의 메소드를 사용. (`getTotalElements`인데 get을 빼고 사용해도 맵핑시켜 줌)

### 페이징 처리

```java
@GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 10) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String keyword) {
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(keyword, keyword, pageable);

        int startPage = 1;
        int endPage = boards.getTotalPages();

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "board/list";
    }
<nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item" th:classappend="${1 == boards.pageable.pageNumber + 1} ? 'disabled' : ''">
                <a class="page-link" th:href="@{/board/list(page=${boards.pageable.pageNumber - 1}, keyword=${param.keyword})}">Previous</a>
            </li>
            <li class="page-item" th:classappend="${i == boards.pageable.pageNumber + 1} ? 'disabled' : ''" th:each="i : ${#numbers.sequence(startPage, endPage)}">
                <a class="page-link" href="#"  th:href="@{/board/list(page=${i - 1}, keyword=${param.keyword})}" th:text="${i}"></a></li>

            <li class="page-item" th:classappend="${boards.totalPages == boards.pageable.pageNumber + 1} ? 'disabled' : ''">
                <a class="page-link" href="#" th:href="@{/board/list(page=${boards.pageable.pageNumber + 1}, keyword=${param.keyword})}">Next</a>
            </li>
        </ul>
    </nav>
```

- `th:each="i : ${#numbers.sequence(startPage, endPage)}` 타임리프에서의 반복문 사용법

### 검색 기능

```java
# BoardRepository
Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

# BoardController
@GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 10) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String keyword) {
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(keyword, keyword, pageable);

        int startPage = 1;
        int endPage = boards.getTotalPages();

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "board/list";
<input type="text" class="form-control" id="keyword" name="keyword" 
th:value="${param.keyword}">
```

- `${param.x}` 변수는 전달받은 객체의 변수사용을 가능케 한다

### 데이터 관계 (중요!)

@OneToOne (fetch 기본값 EAGER)

ex) user - user_detail

@OneTo**Many** (fetch 기본값 **LAZY**)

ex) user - board

@ManyToOne (fetch 기본값 EAGER)

ex) board - user

@ManyTo**Many** (fetch 기본값 **LAZY**)

ex) user - role

- One으로 끝나는 데이터 관계는 하나의 데이터만 조회하기 때문에 EAGER가 기본값이여도 성능상에 큰 무리가 없음

### FK 키 추가하기

```java
alter table board add column user_id int;

alter table board add foreign key(user_id) references user(id);

select * from information_schema.table_constraints where constraint_schema = 'map';
```

- 주로 Many에 해당하는 자료쪽에서 @JoinColumn 조건을 가져준다
- One쪽에 해당하는 쪽에서는 @OneToMany(mappedBy = “user”)와 같이 양방향 맾핑을 해준다
  - 여기서 “user”는 Board쪽에서 ManyToOne 조건을 통해 만든 `private User user`의 user이다

## 권한 설정

```java
<button type="button" class="btn btn-danger" th:onclick="|deleteBoard(*{id})|"
                    sec:authorize="hasRole('ROLE_ADMIN')">삭제

<script>
    function deleteBoard(id) {
        //DELETE /api/boards/{id}
        $.ajax({
            url: '/api/boards/' + id,
            type: 'DELETE',
            success: function (result) {
                console.log("result", result);
                alert("삭제 완료");
                window.location.href = '/board/list';
            },
        });
    }
</script>
```

- 다음과 같이 권한에 따라 삭제 버튼을 숨김처리 하여도 API 통신 클라이언트를 통해 권한이 없는 사람이 DELTE 요청을 보낼 수 있다는 보안 취약점이 있다

```java
package com.example.god.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

}

#### BoardApiController ####
@Secured("ROLE_ADMIN")
    @DeleteMapping("/boards/{id}")
    void deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
    }
}
```

- 다음과 같이 MethodSpringSecurity 설정을 통해 해결이 가능했다

## 삭제 버튼 권한

```java
<div class="text-end">
            <a class="btn btn-primary" th:href="@{/board/list}">뒤로</a>
            <span th:if="${#authentication.name} == *{user.username} or ${#authorization.expression('hasRole(''ROLE_ADMIN'')')}">
            <button type="button" class="btn btn-danger" th:onclick="|deleteBoard(*{id})|">삭제</button></span>
            <button type="submit" class="btn btn-primary">확인</button>
        </div>
```

- `<span th:if="${#authentication.name} == *{user.username} or ${#authorization.expression('hasRole(''ROLE_ADMIN'')')}">` 다음의 조건이 권한에 따른 삭제 버튼을 표시하는 데에 핵심이었다.

### @PutMapping, @PatchMapping의 차이

- Put : 리소스의 모든 것을 업데이트 한다
- Patch : 리소스의 일부를 업데이트 한다

### 주소 리터럴 표기

```java
th:href="@{|/board/view/${board.id}|} 

| <- 를 {}안에 넣어준다
```

### Content type 'application/x-www-form-urlencoded;charset=UTF-8' not supported 오류 해결

```java
contentType: 'application/json', 
위 문장을 ajax 통신단에 넣어주면 해결
```

### 게시판 수정

```java
var data = {
            "title": $("#title").val().trim(),
            "content": $("#content").val().trim()
        }
```

- `val()` : input, select,textarea 등의 form elements에서 값을 구한다.
- `val(value)` : form elements에 값을 지정한다.
- 값을 변경 할 때 : $(변경할 부분 선택).val(값);
- 값을 가져 올 때 : $(변경할 부분 선택).val(값);

### 시간 포맷

```java
<td th:text="${#temporals.format(board.createdDate, 'yyyy-MM-dd HH:mm')}"></td>
```

### [Entity 코드 수정](https://anywaydevlog.tistory.com/106#Entity%25--%EC%BD%25--%EB%25--%25-C%25--%EC%25--%25--%EC%25A-%25--)

Spring Data Jpa 는 기본적으로 JPA 영속성 컨텍스트 유지를 제공한다. 이 상태에서 해당 데이터의 값을 변경하면 자동으로 변경사항이 DB에 반영한다. 즉, 별도로 Update 쿼리를 날리지 않아도, 데이터만 변경하면 알아서 변경되므로 수정기능은 아래처럼 Entity 에 update 메소드를 만들어줘 구현한다.

### [src/main/java/com.study.springbootaws/domain/posts/Posts](https://anywaydevlog.tistory.com/106#src%-Fmain%-Fjava%-Fcom-study-springbootaws%-Fdomain%-Fposts%-FPosts)

```java
...

public class Posts {

...

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
```

### Setter 없이 맵핑이 되지 않았던 이유

https://nirudiru.tistory.com/110

https://okky.kr/articles/1330484

### DTO 매핑과 User 정보https://kingpiggylab.tistory.com/329

연관관계를 채워주기 위해 고민했던 방법이 2가지입니다.

1. DTO의 연관관계를 어디서 갖게 해야할까 (클라이언트단, Controller단, Service단)
2. 클라이언트는 User 정보를 갖고있어도 될까

클라이언트에서 User라는 민감한 정보를 갖고있으면 안될 것 같고, Controller에서 엔티티에 직접 접근하거나 생성하는 일이 없도록 생각하니 **서비스단에서 UserService를 호출하고 DTO에 채워줌으로 Service단에서 처리**하였다.

### **[최종 Data Flow](https://kingpiggylab.tistory.com/329#%EC%25B-%25-C%EC%25A-%25--%25--Data%25--Flow)**

1. 클라이언트에서 서버로 저장 요청 (이때 서버가 받은 SaveRequestDto는 사용자 정보가 없는 상태, 저장할 값만을 담고있음)
2. Controller는 Service를 호출하여 StoreSaveRequestDto와 UserRequestDto를 넘겨줌 (Session에서 유저 정보를 불러온 것)
3. Service에서 UserRequestDto를 이용하여 UserRepository에서 User 엔티티를 가져와서 StoreSaveRequestDto에 값을 채워줌
4. StoreSaveRequestDto를 Entity로 변환하고 저장