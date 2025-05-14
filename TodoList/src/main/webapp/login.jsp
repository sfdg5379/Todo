<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
</head>
<body>
    <h2>로그인</h2>

    <%-- 로그인 실패 시 에러 메시지 표시 --%>
    <% if (request.getParameter("error") != null) { %>
    <p style="color:red;">아이디 또는 비밀번호가 올바르지 않습니다.</p>
    <% } %>
    

    <form action="login" method="post">
        <label>아이디: <input type="text" name="username" required></label><br><br>
        <label>비밀번호: <input type="password" name="password" required></label><br><br>
        <button type="submit">로그인</button>
    </form>

    <p>아직 회원이 아니라면? <a href="enroll.jsp">회원가입</a></p>
</body>
</html>
