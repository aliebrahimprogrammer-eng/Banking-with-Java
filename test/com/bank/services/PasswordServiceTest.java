package com.bank.services;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordServiceTest {

    @Test
    public void hashPasswordShouldReturnHashedPassword() {

        PasswordService passwordService = new PasswordService();

        String password = "12345";

        String hashedPassword = passwordService.hashPassword(password);

        assertNotNull(hashedPassword);
        assertNotEquals(password, hashedPassword);
    }

    @Test
    public void samePasswordShouldProduceSameHash() {

        PasswordService passwordService = new PasswordService();

        String firstHash = passwordService.hashPassword("12345");
        String secondHash = passwordService.hashPassword("12345");

        assertEquals(firstHash, secondHash);
    }
}