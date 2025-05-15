<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%
    // 오늘 날짜 기준
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);  // 0부터 시작하므로 실제 월은 +1 필요
    cal.set(Calendar.DAY_OF_MONTH, 1);

    int startDay = cal.get(Calendar.DAY_OF_WEEK);  // 1=일요일 ~ 7=토요일
    int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
%>

<h3><%= year %>년 <%= month + 1 %>월</h3>

<table border="1" cellpadding="10">
    <tr>
        <th style="color:red">일</th>
        <th>월</th>
        <th>화</th>
        <th>수</th>
        <th>목</th>
        <th>금</th>
        <th style="color:blue">토</th>
    </tr>

<%
    int day = 1;
    boolean started = false;

    for (int i = 0; i < 6; i++) {  // 최대 6주
        out.print("<tr>");
        for (int j = 1; j <= 7; j++) {
            if (!started && j == startDay) started = true;

            if (started && day <= lastDate) {
%>
                <td>
                    <form action="todo/listByDate.jsp" method="get">
                        <input type="hidden" name="year" value="<%= year %>">
                        <input type="hidden" name="month" value="<%= month + 1 %>">
                        <input type="hidden" name="day" value="<%= day %>">
                        <button style="background:none; border:none; cursor:pointer;" type="submit"><%= day %></button>
                    </form>
                </td>
<%
                day++;
            } else {
                out.print("<td></td>");
            }
        }
        out.print("</tr>");
        if (day > lastDate) break;
    }
%>
</table>
