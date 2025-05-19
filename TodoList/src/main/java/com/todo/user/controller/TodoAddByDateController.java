package com.todo.user.controller;

import com.todo.user.model.dao.TodoDAO;
import com.todo.user.model.dto.Todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

@WebServlet("/todo/addByDate")
public class TodoAddByDateController extends HttpServlet {
    private final TodoDAO dao = new TodoDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Long userId = (Long) req.getSession().getAttribute("userId");
        if (userId == null) {
            resp.sendRedirect("../login.jsp");
            return;
        }

        String yearStr = req.getParameter("year");
        String monthStr = req.getParameter("month");
        String dayStr = req.getParameter("day");
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        int year = Integer.parseInt(yearStr);
        int month = Integer.parseInt(monthStr);
        int day = Integer.parseInt(dayStr);

        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        Date dueDate = new Date(cal.getTimeInMillis());

        Todo todo = new Todo();
        todo.setUserId(userId);
        todo.setTitle(title);
        todo.setContent(content);
        todo.setDueDate(dueDate);
        todo.setIsDone('N');  // 기본값: 미완료

        try {
            dao.insert(todo);
            // 다시 해당 날짜로 돌아가기
            String redirectUrl = String.format("listByDate.jsp?year=%s&month=%s&day=%s", yearStr, monthStr, dayStr);
            resp.sendRedirect(redirectUrl);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
