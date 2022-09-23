package hello.hellospring.domain;

import javax.persistence.*;

@Entity //JPA를 쓰기위해 엔티티 설정
public class Member {

    // ID와 아이덴티티 전략으로 밸류 생성
    // 아이덴티티 : DB가 알아서 자동으로 생성해주는 것
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
