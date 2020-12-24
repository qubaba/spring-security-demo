package com.example.part2imagecode.configuration;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class VerificationCodeFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request
            , HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        exception.printStackTrace();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("{\n" +
                "    \"code\": \"01\",\n" +
                "    \"msg\": " + exception.getMessage() + "\n" +
                "}");
    }
}
