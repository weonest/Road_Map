# MVC와 템플릿 엔진

- MVC : Model, View, Controller

Controller

```java
@Controller
public class HelloController {
 @GetMapping("hello-mvc")
 public String helloMvc(@RequestParam("name") String name, Model model) {
 model.addAttribute("name", name);
 return "hello-template";
 }
}
```

View

```
resources/templates/hello-template.html
<html xmlns:th="<http://www.thymeleaf.org>">
<body>
<p th:text="'hello ' + ${name}">hello! empty</p>
</body>
</html>
```

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b2ab0503-3af2-4a3d-aad3-11df89807b78/Untitled.png)

변환을 해서 던져 줌. Static과 다름