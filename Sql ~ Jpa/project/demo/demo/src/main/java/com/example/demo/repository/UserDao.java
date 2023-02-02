package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private SimpleJdbcInsertOperations insertOperations;


    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.insertOperations = new SimpleJdbcInsert(dataSource)
                .withTableName("user");
    }

    public boolean addUser(User user) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        int result = insertOperations.execute(params);
        return result == 1;
    }

    public void updateUser(User user) {
        String sql = "update user set email = :email, name = :name, password = :password where user_id = :userId";
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        jdbcTemplate.update(sql, params);
    }

    public boolean delete(int userId) {
        String sql ="delete from user where user_id = :userId";
        SqlParameterSource params = new MapSqlParameterSource("userId", userId);
        int result = jdbcTemplate.update(sql, params);
        return result == 1;
    }

    public User getUser(int userId) {
        String sql = "select user_id, name, email, regdate FROM user WHERE user_id = :userId";
        try {
            SqlParameterSource params = new MapSqlParameterSource("userId", userId);
            RowMapper<User> userRowMapper = BeanPropertyRowMapper.newInstance(User.class);
            return jdbcTemplate.queryForObject(sql, params, userRowMapper);
        } catch (Exception e) {
            return null;
        }
    }

    public List<User> getUsers() {
        String sql = "select user_id, email, name, regdate from user";
        RowMapper<User> userRowMapper = BeanPropertyRowMapper.newInstance(User.class);
        return jdbcTemplate.query(sql, userRowMapper);
    }


}
