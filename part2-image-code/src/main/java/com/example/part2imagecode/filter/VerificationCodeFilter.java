package com.example.part2imagecode.filter;

import com.example.part2imagecode.configuration.VerificationCodeFailureHandler;
import com.example.part2imagecode.exception.VerificationCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VerificationCodeFilter extends OncePerRequestFilter {

    VerificationCodeFailureHandler verificationCodeFailureHandler = new VerificationCodeFailureHandler();
    public static final String VERIFICATION_CODE_KEY = "verificationCode";

    @Override
    protected void doFilterInternal(HttpServletRequest request
            , HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 判断是不是登录请求
        if (!("/login".equals(request.getRequestURI()) && "POST".equalsIgnoreCase(request.getMethod()))) {
            filterChain.doFilter(request, response);
            System.out.println(request.getRequestURI());
        } else {
            try {
                verificationCode(request);
                filterChain.doFilter(request, response);
            } catch (VerificationCodeException e) {
                verificationCodeFailureHandler.onAuthenticationFailure(request, response, e);
            }

        }

    }

    private void verificationCode(HttpServletRequest request) {
        String userVerificationCode = request.getParameter(VERIFICATION_CODE_KEY);
        String verificationCode = (String) request.getSession().getAttribute(VERIFICATION_CODE_KEY);
        if (!StringUtils.isEmpty(userVerificationCode)) {
            request.getSession().removeAttribute(VERIFICATION_CODE_KEY);
        }

        if (StringUtils.isEmpty(userVerificationCode)
                || StringUtils.isEmpty(verificationCode)
                || !StringUtils.equalsIgnoreCase(userVerificationCode, verificationCode)) {
            throw new VerificationCodeException("图形验证码校验失败");
        }

    }
}
