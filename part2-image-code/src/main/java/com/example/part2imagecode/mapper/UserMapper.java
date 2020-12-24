package com.example.part2imagecode.mapper;

import com.example.part2imagecode.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserMapper extends Mapper<User> {

    @Select("select role_id from user_role where user_id = #{userId}")
    List<String> findRoleIdByUserId(@Param("userId") String userId);


    @Select("select * from user where username = #{username}")
    User findByUsername(@Param("username") String username);
}
