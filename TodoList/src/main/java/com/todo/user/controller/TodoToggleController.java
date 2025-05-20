package com.todo.user.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todo.user.model.dao.TodoDAO;
  
	@WebServlet("/todo/toggle")
	public class TodoToggleController extends HttpServlet {
		private static final long serialVersionUID = 1L;
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
	        char newStatus = req.getParameter("isDone").charAt(0); // 'Y' or 'N'

	        try {
	            dao.toggleDone(todoId, newStatus);

	            // 다시 날짜 페이지로 이동
	            String y = req.getParameter("year");
	            String m = req.getParameter("month");
	            String d = req.getParameter("day");
	            resp.sendRedirect("listByDate.jsp?year=" + y + "&month=" + m + "&day=" + d);
	        } catch (SQLException e) {
	            throw new ServletException(e);
	        }
	    }
	}


