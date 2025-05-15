<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, java.sql.*, com.todo.user.model.dao.TodoDAO, com.todo.user.model.dto.Todo" %>

<%
    Long userId = (Long) session.getAttribute("userId");
    if (userId == null) {
        response.sendRedirect("../login.jsp");
        return;
    }

    String yearStr = request.getParameter("year");
    String monthStr = request.getParameter("month");
    String dayStr = request.getParameter("day");

    // 날짜 문자열 → java.sql.Date로 변환
    int year = Integer.parseInt(yearStr);
    int month = Integer.parseInt(monthStr);
    int day = Integer.parseInt(dayStr);

    Calendar cal = Calendar.getInstance();
    cal.set(year, month - 1, day);  // Calendar는 month가 0부터 시작
    Date dueDate = new Date(cal.getTimeInMillis());

    // DAO로 할 일 가져오기
    TodoDAO dao = new TodoDAO();
    List<Todo> list = dao.findByDate(userId, dueDate);
%>

<h2>📅 <%= year %>년 <%= month %>월 <%= day %>일의 할 일 목록</h2>

<% if (list.isEmpty()) { %>
    <p>할 일이 없습니다.</p>
<% } else { %>
    <ul>
        <% for (Todo t : list) { %>
            <li>
                <b><%= t.getTitle() %></b> - <%= t.getContent() %>
                (<%= t.getIsDone() == 'Y' ? "완료" : "미완료" %>)
            </li>
        <% } %>
    </ul>
<% } %>

<a href="../main.jsp">← 메인으로 돌아가기</a>
