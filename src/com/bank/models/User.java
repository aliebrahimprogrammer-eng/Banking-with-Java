package com.bank.models;

import java.time.LocalDateTime;

public abstract class User {

    private String userId;
    private String name;
    private String password;
    private int failedLoginAttempts;
    private LocalDateTime lockedUntil;

    public User(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        failedLoginAttempts = 0;
        lockedUntil = null;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public LocalDateTime getLockedUntil() {
        return lockedUntil;
    }

    public void setLockedUntil(LocalDateTime lockedUntil) {
        this.lockedUntil = lockedUntil;
    }

    public void increaseFailedLoginAttempt(){
        failedLoginAttempts++;
    }

    public void resetFailedLoginAttempt(){
        failedLoginAttempts = 0;
    }


}


