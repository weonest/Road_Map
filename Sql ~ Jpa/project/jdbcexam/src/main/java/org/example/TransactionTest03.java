package org.example;

import java.sql.*;

public class TransactionTest03 {
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
            // 오토 커밋 끄기
            // begin 할 필요 없이 쿼리문이 실행되면 트랜잭션이 작동
            conn.setAutoCommit(false);
            
            
            // Do something with the Connection
            if (conn != null) {
                System.out.println("DBMS 연결 성공!");
                System.out.println(conn.getClass().getName()); // 자바 리콜렉션
            }


            // 만일 이 위에서 게시판 뷰 증가와 같이 또 다른 ps를 선언하고 쿼리문을 작성한다면,
            // ps.clearParameters(); 를 써서 같은 Transaction 내부에 ps가 여러개 도는 것을 방지?
            ps = conn.prepareStatement("select empno, ename, job, mgr, hiredate, sal, comm, deptno from emp where empno = ?");

            ps.setInt(1, 7369);   // 각 파라미터의 앞 부분이 몇 번째 ?인지, 뒤가 값



            rs = ps.executeQuery();

            if (rs.next()) {
                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                int mgr = rs.getInt("mgr");
                Date hiredate = rs.getDate("hiredate");
                double sal = rs.getDouble("sal");
                double comm = rs.getDouble("comm");
                int deptno = rs.getInt("deptno");
                System.out.println(empno + ", " + ename + ", " + job + ", " + mgr + ", " + hiredate + ", " + sal + ", " + comm + ", " + deptno);

            }else {
                throw new RuntimeException("empno에 해당하는 자료가 없습니다");
            }
            conn.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                System.out.println("롤백합니다!");
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } finally {
            try {
                // ps 자원도 해제를 해줘야 함
                if (ps != null) {
                    ps.close();
                }
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
