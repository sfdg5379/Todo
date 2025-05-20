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

    int year = Integer.parseInt(yearStr);
    int month = Integer.parseInt(monthStr);
    int day = Integer.parseInt(dayStr);

    Calendar cal = Calendar.getInstance();
    cal.set(year, month - 1, day);  // month는 0부터 시작
    java.sql.Date dueDate = new java.sql.Date(cal.getTimeInMillis());

    // DAO로 할 일 목록 가져오기
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
                <!-- ✅ 완료 토글 버튼 -->
                <form action="/TodoList/todo/toggle" method="post" style="display:inline;">
                    <input type="hidden" name="todoId" value="<%= t.getTodoId() %>">
                    <input type="hidden" name="isDone" value="<%= t.getIsDone() == 'Y' ? 'N' : 'Y' %>">
                    <input type="hidden" name="year" value="<%= year %>">
                    <input type="hidden" name="month" value="<%= month %>">
                    <input type="hidden" name="day" value="<%= day %>">
                    <button type="submit">
                        <%= t.getIsDone() == 'Y' ? "✅ 완료됨" : "⬜ 완료하기" %>
                    </button>
                </form>

                <!-- 할 일 내용 -->
                <b><%= t.getTitle() %></b> - <%= t.getContent() %>
            </li>
        <% } %>
    </ul>
<% } %>

<a href="../main.jsp">← 메인으로 돌아가기</a>

<hr>
<h3>➕ 할 일 추가</h3>
<form action="addByDate" method="post">
    <input type="hidden" name="year" value="<%= year %>">
    <input type="hidden" name="month" value="<%= month %>">
    <input type="hidden" name="day" value="<%= day %>">

    <label>제목: <input type="text" name="title" required></label><br><br>
    <label>내용: <input type="text" name="content" required></label><br><br>

    <button type="submit">추가</button>
</form>
