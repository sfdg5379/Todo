package com.todo.user.model.dto;

public class User {
    private long userId;
    private String username;
    private String password; // 선택사항 (비워도 됨)

    public User() {}

    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
