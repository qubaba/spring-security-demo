package com.example.part2imagecode.configuration;

import com.example.part2imagecode.filter.VerificationCodeFilter;
import com.example.part2imagecode.service.UserService;
import com.example.part2imagecode.support.MyPasswordEncode;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.io.PrintWriter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new MyPasswordEncode();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers("/app/user/**").permitAll()
                .antMatchers("/kaptcha.jpg").permitAll()
                .antMatchers("/admin/user/**").hasAuthority("ADMIN")
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
                .failureHandler(new VerificationCodeFailureHandler())
                .permitAll()
                .and()
                .csrf().disable();
        http.addFilterBefore(new VerificationCodeFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
