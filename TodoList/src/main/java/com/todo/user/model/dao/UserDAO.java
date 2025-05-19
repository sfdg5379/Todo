package com.todo.user.model.dao;

import java.sql.*;

import com.todo.user.model.dto.User;
import com.todo.common.template.JDBCTemplate;

public class UserDAO {

    // ✅ 로그인 사용자 조회 (ID, NAME 반환)
    public User findByUsernameAndPassword(String username, String rawPassword) {
        String sql = "SELECT USER_ID, USERNAME, PASSWORD FROM USERS WHERE USERNAME = ?";

        try (Connection conn = JDBCTemplate.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String savedPassword = rs.getString("PASSWORD");

                // ✅ 암호화 없이 평문 비교
                if (savedPassword.equals(rawPassword)) {
                    User user = new User();
                    user.setUserId(rs.getLong("USER_ID"));
                    user.setUsername(rs.getString("USERNAME"));
                    return user;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
