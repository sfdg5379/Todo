package com.todo.user.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


	@WebServlet("/enroll")
	public class EnrollController extends HttpServlet {
		
		private static final long serialVersionUID = 1L;


	    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	    private static final String DB_USER = "C##TODO";       // 오라클 계정
	    private static final String DB_PASSWORD = "TODO";   // 오라클 비번

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // 한글 깨짐 방지
	        request.setCharacterEncoding("UTF-8");

	        // 폼에서 보낸 데이터 받기
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String email = request.getParameter("email");

	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

	            String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, username);
	            pstmt.setString(2, password);
	            pstmt.setString(3, email);

	            int result = pstmt.executeUpdate();

	            pstmt.close();
	            conn.close();

	            if (result > 0) {
	                response.sendRedirect("enrollSuccess.jsp");
	            } else {
	                response.sendRedirect("enrollFail.jsp");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendRedirect("enrollFail.jsp");
	        }
	    }
}
