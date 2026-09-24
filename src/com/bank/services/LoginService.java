package com.bank.services;

import com.bank.models.User;

import java.time.LocalDateTime;
import java.util.List;

public class LoginService {

    private PasswordService passwordService;
    private List<User> userList;




    public LoginService(List<User> userList) {
        passwordService = new PasswordService();
        this.userList = userList;
    }


    public User login (String customerId, String password){
        String hashedPassword = passwordService.hashPassword(password);
        for (User user : userList) {
            if (user.getUserId().equals(customerId)) {
                if (user.getLockedUntil() != null) {
                    if (LocalDateTime.now().isBefore(user.getLockedUntil())) {
                        System.out.println("Account is temporary locked until " + user.getLockedUntil() + ", Please try again later");
                        return null;
                    } else {
                        user.resetFailedLoginAttempt();
                        user.setLockedUntil(null);
                    }
                }
                if (user.getPassword().equals(hashedPassword)) {
                    user.resetFailedLoginAttempt();
                    user.setLockedUntil(null);
                    return user;
                } else {
                    user.increaseFailedLoginAttempt();
                    System.out.println("Invalid Password");
                    if (user.getFailedLoginAttempts() >= 3) {
                        user.setLockedUntil(LocalDateTime.now().plusMinutes(1));
                        System.out.println("Account is locked for 1 minute until " + user.getLockedUntil());
                    }
                    return null;
                }
            }
        }
        System.out.println("Customer not found.");
        return null;
    }

    public void addUser(User user) {
        userList.add(user);
    }

}
