package com.example.demo.repository;

import com.example.demo.domain.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class RoleDao {
    private final NamedParameterJdbcTemplate jdbcTemplate; // 필드를 final로 선언하면 반드시 생성자에서 초기화
    private SimpleJdbcInsertOperations insertAction; // insert를 쉽게 하도록 도와주는 인터페이스

    // 생성자에 파라미터를 넣어주면 스프링 부트가 자동으로 주입한다. 생성자 주입
    public RoleDao(DataSource dataSource) {
        System.out.println("RoleDao 생성자 호출");
        System.out.println(dataSource.getClass().getName());
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource); // DataSource를 넣어줘야 한다

        insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("role");
    }

    // Role 테이블에 한 건 저장
    public boolean addRole(Role role) {
//        String sql = "insert into role(role_id, name) values(?, ?)";
//        int result = jdbcTemplate.update(sql, role.getRoleId(), role.getName());
//        return result == 1;

        // role은 프로퍼티 roleId, name
        // .withTableName("role") 구문을 분석해서 자동으로 쿼리문을 만들어 줌
        // Role 클래스의 프로퍼티 이름과 컬럼명의 규칙이 맞아야 한다. role_id = roleID
        // DB와 JAVA의 영문 표현식이 다르기 때문 (스네이크, 카멜)
        SqlParameterSource params = new BeanPropertySqlParameterSource(role); // role 객체의 필드명과 테이블의 컬럼명이 같아야 한다
        int result = insertAction.execute(params);
        return result == 1;
    }

    public boolean deleteRole(int roleId) {
//        String sql = "delete from role where role_id = ?";
//        int result = jdbcTemplate.update(sql, roleId);
//        return result == 1;
        String sql = "delete from role where role_id = :roleID";
        SqlParameterSource params = new MapSqlParameterSource("roleId", roleId);
        int result = jdbcTemplate.update(sql, params);
        return result == 1;
    }

    public Role getRole(int roleId) {
        String sql = "select role_id, name from role where role_id = :roleID";

        // queryForObject는 1건 또는 0건을 읽어오는 메서드
        // queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)

        try {
            SqlParameterSource params = new MapSqlParameterSource("roleId", roleId);
            RowMapper<Role> roleRowMapper = BeanPropertyRowMapper.newInstance(Role.class);
            return jdbcTemplate.queryForObject(sql, params, roleRowMapper); // 여기는 로우매퍼가 세 번째 파라미터로 들어감
        } catch (Exception e) {
            return null;
        }
    }

    // 위의 람다식을 풀어서 쓴 식
// 데이터를 한 건 읽어오는 것을 성공한 것을 가정하고, 한 건의 데이터를 Role 객체에 담아서 리턴하도록 코딩한 것
// 이 클래스가 다른 곳에서는 전혀 재사용될 일이 없을 경우 익명 함수로 처리
//        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
//            Role role = new Role();
//            role.setRoleId(rs.getInt("role_id"));
//            role.setName(rs.getString("name"));
//            return role;
//        }, roleId);
//    }

//    class RoleRowMapper implements RowMapper<Role> {
//        @Override
//        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Role role = new Role();
//            role.setRoleId(rs.getInt("role_id"));
//            role.setName(rs.getString("name"));
//            return role;
//        }
//
//    }

    public List<Role> getRoles() {
        String sql = "select role_id, name from role order by role_id";
        // query 메서드는 여러 건의 결과를 구할때 사용
        RowMapper<Role> roleRowMapper = BeanPropertyRowMapper.newInstance(Role.class);
        return jdbcTemplate.query(sql, roleRowMapper);
    }
}



