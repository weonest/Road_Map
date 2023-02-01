package com.example.demo.repository;

import com.example.demo.domain.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class RoleDao {
    private final JdbcTemplate jdbcTemplate; // 필드를 final로 선언하면 반드시 생성자에서 초기화

    // 생성자에 파라미터를 넣어주면 스프링 부트가 자동으로 주입한다. 생성자 주입
    public RoleDao(DataSource dataSource) {
        System.out.println("RoleDao 생성자 호출");
        System.out.println(dataSource.getClass().getName());
        jdbcTemplate = new JdbcTemplate(dataSource); // DataSource를 넣어줘야 한다
    }

    // Role 테이블에 한 건 저장
    public boolean addRole(Role role) {
        String sql = "insert into role(role_id, name) values(?, ?)";
        int result = jdbcTemplate.update(sql, role.getRoleId(), role.getName());
        return result == 1;
    }

    public boolean deleteRole(int roleId) {
        String sql = "delete from role where role_id = ?";
        int result = jdbcTemplate.update(sql, roleId);
        return result == 1;
    }

    public Role getRole(int roleId) {
        String sql = "select role_id, name from role where role_id = ?";

        // queryForObject는 1건 또는 0건을 읽어오는 메서드
        // queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
        

        try {
            return jdbcTemplate.queryForObject(sql, new RoleRowMapper(), roleId);
        } catch (Exception e) {
            return null;
        }
    }
//        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
//            Role role = new Role();
//            role.setRoleId(rs.getInt("role_id"));
//            role.setName(rs.getString("name"));
//            return role;
//        }, roleId);
//    }
}

// 위의 람다식을 풀어서 쓴 식
// 데이터를 한 건 읽어오는 것을 성공한 것을 가정하고, 한 건의 데이터를 Role 객체에 담아서 리턴하도록 코딩한 것
// 이 클래스가 다른 곳에서는 전혀 재사용될 일이 없을 경우 익명 함수로 처리
    class RoleRowMapper implements RowMapper<Role> {
        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            Role role = new Role();
            role.setRoleId(rs.getInt("role_id"));
            role.setName(rs.getString("name"));
            return role;
        }
}

