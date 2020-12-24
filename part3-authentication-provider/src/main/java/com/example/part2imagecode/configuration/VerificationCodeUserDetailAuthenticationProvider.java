package com.example.part2imagecode.configuration;

import com.example.part2imagecode.entity.User;
import com.example.part2imagecode.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class VerificationCodeUserDetailAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Resource
    private UserService userService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails
            , UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        System.out.println(authentication.getCredentials() +"000000");
    }

    @Override
    protected UserDetails retrieveUser(String username
            , UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        return userService.loadUserByUsername(username);
    }
}
