package naver.map.repository;

import map.kyonggiyo.domain.Map;


import java.util.List;
import java.util.Optional;

public interface MapRepository {
    Map save(Map map);

    Optional<Map> findByName(String name);
    Optional<Map> findByX(float x);
    Optional<Map> findByY(float y);
    Optional<Map> findByAb(long ab);

    List<Map> findAll();

}
