package com.todo.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todo.user.model.dao.TodoDAO;

@WebServlet("/todo/delete")
public class TodoDeleteController extends HttpServlet {
    private final TodoDAO dao = new TodoDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long userId = (Long) req.getSession().getAttribute("userId");
        if (userId == null) {
            resp.sendRedirect("../login.jsp");
            return;
        }

        long todoId = Long.parseLong(req.getParameter("todoId"));
        String year = req.getParameter("year");
        String month = req.getParameter("month");
        String day = req.getParameter("day");

        try {
            dao.delete(todoId);
            resp.sendRedirect("listByDate.jsp?year=" + year + "&month=" + month + "&day=" + day);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
