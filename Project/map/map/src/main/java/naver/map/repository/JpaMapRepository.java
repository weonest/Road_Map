package naver.map.repository;

import naver.map.domain.Map;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMapRepository implements MapRepository {

    private final EntityManager em;

    public JpaMapRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Map save(Map map) {
        em.persist(map);
        return map;
    }

    @Override
    public Optional<Map> findByName(String name) {
        List<Map> result = em.createQuery("select m from Map m where m.name = :name", Map.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Map> findByX(double x) {
        List<Map> result = em.createQuery("select m from Map m where m.x = :x", Map.class)
                .setParameter("x", x)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Map> findByY(double y) {
        List<Map> result = em.createQuery("select m from Map m where m.y = :y", Map.class)
                .setParameter("y", y)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Map> findByAb(long ab) {
        List<Map> result = em.createQuery("select m from Map m where m.ab = :ab", Map.class)
                .setParameter("ab", ab)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Map> findAll() {   // 객체 그 자체(m)을 조사함
        return em.createQuery("select m from Map m", Map.class)
                .getResultList();
    }
}
