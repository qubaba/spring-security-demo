package com.example.part1.support;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncode implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        System.out.println(rawPassword);
        System.out.println(encodedPassword);
        return true;
    }
}
