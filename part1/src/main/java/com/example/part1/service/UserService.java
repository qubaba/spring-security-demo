package com.example.part1.service;

import com.example.part1.entity.User;
import com.example.part1.mapper.UserMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        user.setAuthorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        String.join(",", userMapper.findRoleIdByUserId(user.getId())
                        )
                )
        );
        user.setPassword(user.getPassword());
        return user;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123");
        System.out.println(
                encode
        );
    }


}
