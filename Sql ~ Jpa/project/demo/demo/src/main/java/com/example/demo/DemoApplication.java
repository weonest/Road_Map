package com.example.demo;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.repository.RoleDao;
import com.example.demo.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    UserDao userDao;
//    RoleDao roleDao;

    @Override
    public void run(String... args) throws Exception {
//        Role role = new Role();
//        role.setRoleId(3);
//        role.setName("Role_TEST");
//        roleDao.addRole(role);

//        boolean flag = roleDao.deleteRole(3);
//        System.out.println("flag : " + flag);

//        Role role = roleDao.getRole(1);
//        if(role != null) {
//            System.out.println("엥?");
//            System.out.println(role.getRoleId() + ", " + role.getName());
//        }

//        List<Role> list = roleDao.getRoles();
//        for (Role role : list) {
//            System.out.println(role.getRoleId() + ", " + role.getName());
//        }


//        User user = new User();
//        user.setUserId(3);
//        user.setName("홍길동");
//        user.setEmail("gildong2@naver.com");
//        user.setPassword("1234");
//        user.setRegdate(LocalDateTime.now()); 없어도 가능
//        userDao.addUser(user);

//        User user = new User();
//        user.setUserId(5);
//        user.setPassword("1234");
//        user.setName("smith");
//        user.setEmail("naver.com");
//        userDao.addUser(user);

//        boolean flag = userDao.delete(2);
//        System.out.println(flag);

//        User user = userDao.getUser(3);
//        if(user != null) {
//            System.out.println("엥?");
//            System.out.println(user.getUserId()+ ", " + user.getName());
//        }

//        List<User> list = userDao.getUsers();
//        for (User user : list) {
//            System.out.println(user.getUserId() + ", " + user.getName()+ ", " + user.getRegdate()+ ", " + user.getEmail());
//
//        }

        User user = userDao.getUser(3);
        user.setName("바밤바");
        user.setEmail("gildong33@naver.com");
        user.setPassword("133");
        userDao.updateUser(user);

    }
}
