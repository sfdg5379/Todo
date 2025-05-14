<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = (String) session.getAttribute("user");
    if (username == null) {
        // 로그인이 안 된 상태라면 로그인 페이지로 이동
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>메인 페이지</title>
</head>
<body>
    <h2>환영합니다, <%= username %>님!</h2>

    <p><a href="logout.jsp">로그아웃</a></p>
</body>
</html>
