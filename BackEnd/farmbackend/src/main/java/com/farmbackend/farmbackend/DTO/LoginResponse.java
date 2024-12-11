package com.farmbackend.farmbackend.DTO;


public class LoginResponse<T> {
    private T account;
    private String role;

    public T getAccount() {
        return account;
    }

    public void setAccount(T account) {
        this.account = account;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
