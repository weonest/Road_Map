# API

@ResponseBody 문자 반환

```java
@Controller
public class HelloController {
	
	@GetMapping("hello-String")
	@ResponseBody
	public String helloString(@RequestParam("name") String name) {
		return "hello " + name;
 }
}
```

- `@ResponseBody` 를 사용하면 뷰 리졸버(`viewResolver`)를 사용하지 않음
- 대신에 HTTP의 BODY에 문자 내용을 직접 반환(HTML BODY TAG를 말하는 것이 아님)

@ResponseBody 객체 반환

```java
@Controller
public class HelloController {
	
	@GetMapping("hello-api")
	@ResponseBody
	public Hello helloApi(@RequestParam("name") String name) {
		Hello hello = new Hello();
		hello.setName(name);
		return hellol
	}

	static class Hello {
		private String name;

		public String getName(){
				return name;
		}

		public void setName(String name) {
				this.name = namel
		}
	}
}
```

@`ResponsBody`를 사용하고, 객체를 변환하면 객체가 JSON으로 변환됨