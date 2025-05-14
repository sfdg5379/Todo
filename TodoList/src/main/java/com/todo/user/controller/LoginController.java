package com.todo.user.controller;

import com.todo.user.model.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")  // login.jsp에서 form action="login"과 연결됨
public class LoginController extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isValid = userDAO.validate(username, password);

        if (isValid) {
            // 세션 생성 후 사용자 정보 저장
            HttpSession session = request.getSession();
            session.setAttribute("user", username);

            // 로그인 성공 → 메인 화면으로 이동
            response.sendRedirect("index.jsp");
        } else {
            // 로그인 실패 → 에러 표시를 위해 다시 로그인 페이지로 이동
            response.sendRedirect("login.jsp?error=1");
        }
    }
}
