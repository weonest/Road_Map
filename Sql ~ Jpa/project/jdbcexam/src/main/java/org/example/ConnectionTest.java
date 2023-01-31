package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionTest {
    public static void main(String[] args) {
        // DB 연결을 위한 인터페이스
        Connection conn = null;
        PreparedStatement ps = null;
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
            ps = conn.prepareStatement("insert into salgrade(grade, losal, hisal) values(?, ?, ?)");

            // 물음표에 값을 채워주는 것을 바인딩이라고 함
            ps.setInt(1, 6);   // 각 파라미터의 앞 부분이 몇 번째 ?인지, 뒤가 값
            ps.setInt(2, 4001);
            ps.setInt(3, 9999);

            // SQL 실행 . executeUpdate(); - insert, update, delete 할 때 사용. 건 수를 반환
            int updateCnt = ps.executeUpdate();
            System.out.println("수정된 건수 : " + updateCnt);

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
                // 연결 끊기
                if(conn != null)
                conn.close(); // Connection을 사용하면 반드시 close를 해줘야 함
            } catch (SQLException e) {
                System.out.println("SQLException = " + e.getMessage());;
            }
        }

    }
}
