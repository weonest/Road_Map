package playlist.myplaylist.repository;

import playlist.myplaylist.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(String id);
    Optional<Member> findByPwd(String password);
    Optional<Member> findByName(String name);

    List<Member> findAll();

}
