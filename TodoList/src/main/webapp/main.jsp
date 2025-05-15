<%@ page contentType="text/html; charset=UTF-8" %>
<%
    // 로그인된 사용자 확인
    Long userId = (Long) session.getAttribute("userId");
    if (userId == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>메인 페이지</title>
    <style>
        .container {
            display: flex;
            justify-content: space-between;
        }
        .calendar-area {
            width: 70%;
            padding: 20px;
            border-right: 1px solid #ccc;
        }
        .memo-area {
            width: 30%;
            padding: 20px;
        }
    </style>
</head>
<body>
    <h2>📅 <%= session.getAttribute("user") %>님의 일정 관리 메인 페이지</h2>

    <div class="container">
        <!-- 🗓 달력 영역 -->
        <div class="calendar-area">
            <jsp:include page="calendar.jsp" />
        </div>

        <!-- 📝 메모장 영역 -->
        <div class="memo-area">
            <jsp:include page="memoList.jsp" />
        </div>
    </div>
</body>
</html>
