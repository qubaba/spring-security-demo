package com.example.part2imagecode.support;

import org.apache.commons.lang3.StringUtils;
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
        return StringUtils.equals(rawPassword,encodedPassword);
    }
}
