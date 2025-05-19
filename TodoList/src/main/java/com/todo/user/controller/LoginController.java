package com.todo.user.controller;

import com.todo.user.model.dao.UserDAO;
import com.todo.user.model.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // username, password가 일치하는지 확인
        User loginUser = userDAO.findByUsernameAndPassword(username, password);

        if (loginUser != null) {
            // 세션 생성 후 사용자 정보 저장
            HttpSession session = request.getSession();
            session.setAttribute("user", loginUser.getUsername());
            session.setAttribute("userId", loginUser.getUserId());  // 사용자 ID도 저장

            // 로그인 성공 → 메인 페이지로 이동
            response.sendRedirect("main.jsp");
        } else {
            // 로그인 실패 → 로그인 페이지로 다시 이동 (에러 표시)
            response.sendRedirect("login.jsp?error=1");
        }
    }
}
