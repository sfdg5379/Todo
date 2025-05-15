package com.todo.user.model.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
                String hashedPassword = rs.getString("PASSWORD");

                if (hashedPassword.equals(sha256(rawPassword))) {
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

    // ✅ 비밀번호 SHA-256 암호화
    private String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] result = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
