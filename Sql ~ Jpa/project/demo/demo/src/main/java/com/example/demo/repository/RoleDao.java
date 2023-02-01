package com.example.demo.repository;

import com.example.demo.domain.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;


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
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Role role = new Role();
            role.setRoleId(rs.getInt("role_id"));
            role.setName(rs.getString("name"));
            return role;
        }, roleId);
    }
}
