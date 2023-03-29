# Java Bean

------

## 자바 빈이란?

특정한 정보 (id, password, date …) 등을 가지고 있는 클래스를 표현하는 하나의 규칙, 데이터를 표현하기 위한 목적을 지니고 있다. 이 규칙을 지닌 클래스를 Java Bean 이라고 함

### Java Bean 의 규약

- 반드시 클래스는 패키지화 되어야 함
- 멤버 변수는 property 라고 함
- 멤버 변수는 private로 지정하고, 외부 접근을 위한 getter, setter 메소드를 정의
- getter, setter 메소드는 public으로 지정

```java
public class JavaBean_Test {

    private String id;
    private String password;
    private String email;
    private String name;
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
```

ㅇ