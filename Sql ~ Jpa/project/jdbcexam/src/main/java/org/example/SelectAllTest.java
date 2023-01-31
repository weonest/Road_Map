package org.example;

import java.sql.*;

public class SelectAllTest {
    public static void main(String[] args) {
        // DB 연결을 위한 인터페이스
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // DBMS 접속, JDBC URL은 DBMS에서 정한 방식으로 입력해야 함
            // DBMS와 연결을 하고 Connection을 구현하고 있는 객체를 반환
            conn =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/bubu?useUnicode=true&serverTimezone=Asia/Seoul",
                            "root",
                            "rjsgml33");

            // Do something with the Connection
            if (conn != null) {
                System.out.println("DBMS 연결 성공!");
                System.out.println(conn.getClass().getName()); // 자바 리콜렉션
            }

            // 위에서 연결이 되었으니 SQL을 작성하고, 실행하는 코드
            // 아래 구문은 conn이 지금 연결된 DBMS에 이 SQL을 준비해둬 대신 ? 부분은 남겨놔 라는 뜻
            // 준비한 것을 참조하도록 PreparedStatement를 반환
            ps = conn.prepareStatement("select grade, losal, hisal from salgrade");

            // select 문이 실행되면 실행된 결과는 DBMS에 있다.
            // 실행된 결과를 참조하여 rs에 담는다
            rs = ps.executeQuery(); // select 문을 실행
            
            // next()는 한 줄을 읽어오면 true를 반환
            while (rs.next()) {
                int grade = rs.getInt("grade");
                int losal = rs.getInt("losal");
                int hisal = rs.getInt("hisal");
                System.out.println(grade + ", " + losal + ", " + hisal);
            }


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            try {
                // ps 자원도 해제를 해줘야 함
                if (ps != null) {
                    ps.close();
                }
                // rs 자원 해제
                if (rs != null) {
                    rs.close();
                }
                // 연결 끊기
                if(conn != null)
                conn.close(); // Connection을 사용하면 반드시 close를 해줘야 함
            } catch (SQLException e) {
                System.out.println("SQLException = " + e.getMessage());;
            }
        }

    }
}
