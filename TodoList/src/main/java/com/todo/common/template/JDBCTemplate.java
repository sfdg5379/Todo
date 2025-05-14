package com.todo.common.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTemplate {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // DB 주소
    private static final String USER = "C##TODO";        // 사용자 계정
    private static final String PASSWORD = "TODO"; // 비밀번호

    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
            e.printStackTrace();
        }
    }

    // 연결 메서드
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("DB 연결 실패", e);
        }
    }

    // 자원 정리용 닫기 메서드들 (선택)
    public static void close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
