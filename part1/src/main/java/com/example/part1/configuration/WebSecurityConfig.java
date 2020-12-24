package com.example.part1.configuration;

import com.example.part1.service.UserService;
import com.example.part1.support.MyPasswordEncode;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.io.PrintWriter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserService userService;

    // @Bean
    PasswordEncoder passwordEncoder(){
        return new MyPasswordEncode();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/app/user/**").permitAll()
                .antMatchers("/admin/user/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .userDetailsService(userService)
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    writer.write("{\n" +
                            "    \"code\": \"00\",\n" +
                            "    \"msg\": \"登录成功\"\n" +
                            "}");
                })
                .failureHandler((request, response, authenticationException) -> {
                    authenticationException.printStackTrace();
                    response.setContentType("application/json;charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    writer.write("{\n" +
                            "    \"code\": \"01\",\n" +
                            "    \"msg\": \"登录失败\"\n" +
                            "}");
                })
                .permitAll()
                .and()
                .csrf().disable();
    }
}
