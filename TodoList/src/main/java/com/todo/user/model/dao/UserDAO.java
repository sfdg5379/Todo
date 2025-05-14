package com.todo.user.model.dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import com.todo.common.template.JDBCTemplate;  // ✅ 수정된 import

public class UserDAO {

    public boolean validate(String username, String rawPassword) {
        String sql = "SELECT password FROM USERS WHERE username = ?";
        try (Connection conn = JDBCTemplate.getConnection();  // ✅ 수정된 부분
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString(1);
                return hashedPassword.equals(sha256(rawPassword));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

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
