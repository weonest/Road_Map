package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	//CommandLineRunner는 이곳에서 코드를 작동시킬 수 있게 해주는 Spring Boot 기능

    // main 메소드는 Spring이 관리하지 않음
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // DataSource Bean(Spring이 관리하는 객체)
    @Autowired
    DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("스프링 부트가 관리하는 빈을 사용할 수 있다.");

        Connection conn = dataSource.getConnection();

        PreparedStatement ps = conn.prepareStatement("select grade, losal, hisal from salgrade");
        ResultSet rs =ps.executeQuery();

        while (rs.next()) {
            int grade = rs.getInt("grade");
            int losal = rs.getInt("losal");
            int hisal = rs.getInt("hisal");
            System.out.println(grade + ", " + losal + ", " + hisal);
        }
		rs.close();
		ps.close();
        conn.close();
    }
}
