package com.example.part2imagecode.service;

import com.example.part2imagecode.entity.User;
import com.example.part2imagecode.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

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
        List<String> roleIdByUserId = userMapper.findRoleIdByUserId(user.getId());
        user.setAuthorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        String.join(",", roleIdByUserId
                        )
                )
        );
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            System.out.println(authority.getAuthority());
        }
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
