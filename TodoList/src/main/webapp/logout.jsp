<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 세션 무효화 (로그아웃 처리)
    session.invalidate();

    // 로그인 페이지로 이동
    response.sendRedirect("login.jsp");
%>
