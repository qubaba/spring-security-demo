package com.example.part2imagecode.exception;

import org.springframework.security.core.AuthenticationException;

public class VerificationCodeException extends AuthenticationException {

    public VerificationCodeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public VerificationCodeException(String msg) {
        super(msg);
    }
}
