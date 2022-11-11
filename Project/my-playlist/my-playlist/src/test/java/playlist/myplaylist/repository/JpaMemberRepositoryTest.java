package playlist.myplaylist.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import playlist.myplaylist.domain.Member;
import playlist.myplaylist.service.MemberService;

import javax.persistence.EntityManager;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class JpaMemberRepositoryTest {

    @Autowired
            private MemberService memberService;
    private EntityManager em;
    JpaMemberRepository jpa = new JpaMemberRepository(em);

    @Test
    public void save() {
        Member member = new Member();
        member.setId("geonhee");
        member.setName("건희");
        member.setPassword("1234");
        jpa.save(member);

        List<Member> all = jpa.findAll();

        System.out.println(all);

    }
}
