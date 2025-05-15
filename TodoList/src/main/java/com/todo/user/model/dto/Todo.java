package com.todo.user.model.dto;

import java.sql.Date;

public class Todo {
    private long todoId;
    private long userId;
    private String title;
    private String content;
    private char isDone;
    private Date dueDate;
    private Date createdAt;

    public Todo() {}

    // 생성자
    public Todo(long todoId, long userId, String title, String content,
                char isDone, Date dueDate, Date createdAt) {
        this.todoId = todoId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.isDone = isDone;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
    }

    // Getter / Setter

    public long getTodoId() {
        return todoId;
    }

    public void setTodoId(long todoId) {
        this.todoId = todoId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public char getIsDone() {
        return isDone;
    }

    public void setIsDone(char isDone) {
        this.isDone = isDone;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
