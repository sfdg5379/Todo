package com.todo.user.model.dao;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.todo.common.template.JDBCTemplate;
import com.todo.user.model.dto.Todo;

public class TodoDAO {

	public List<Todo> findByDate(long userId, Date dueDate) throws SQLException {
	    String sql = "SELECT * FROM TODOS WHERE USER_ID = ? AND TRUNC(DUE_DATE) = TRUNC(TO_DATE(?, 'YYYY-MM-DD'))";
	    List<Todo> list = new ArrayList<>();

	    try (Connection conn = JDBCTemplate.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setLong(1, userId);
	        ps.setString(2, dueDate.toString()); // yyyy-MM-dd 형태 문자열

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Todo t = new Todo();
	                t.setTodoId(rs.getLong("TODO_ID"));
	                t.setUserId(rs.getLong("USER_ID"));
	                t.setTitle(rs.getString("TITLE"));
	                t.setContent(rs.getString("CONTENT"));
	                t.setIsDone(rs.getString("IS_DONE").charAt(0));
	                t.setCreatedAt(rs.getDate("CREATED_AT"));
	                t.setDueDate(rs.getDate("DUE_DATE"));
	                list.add(t);
	            }
	        }
	    }
	    return list;
	}


	public int insert(Todo todo) throws SQLException {
	    String sql = "INSERT INTO TODOS (USER_ID, TITLE, CONTENT, DUE_DATE, IS_DONE) VALUES (?, ?, ?, ?, ?)";
	    try (Connection conn = JDBCTemplate.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setLong(1, todo.getUserId());
	        ps.setString(2, todo.getTitle());
	        ps.setString(3, todo.getContent());
	        ps.setDate(4, todo.getDueDate());
	        ps.setString(5, String.valueOf(todo.getIsDone()));
	        return ps.executeUpdate();
	    }
	}


}
