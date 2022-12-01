package naver.map.repository;

import naver.map.domain.Map;

import java.util.List;
import java.util.Optional;

public interface MapRepository {
    Map save(Map map);

    Optional<Map> findByName(String name);
    Optional<Map> findByX(double x);
    Optional<Map> findByY(double y);
    Optional<Map> findByAb(long ab);

    List<Map> findAll();

}
