package com.example.springboothtml.Mapper;

import com.example.springboothtml.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface UserMapper {
    //接口中放抽象方法

    //根据用户名查询数据库中有没有该用户
    Users loginByusername(String username);

    //查询到的数据返回用户类型  ----mapper.xml中有结果映射
    Users loginByusernameAndpassword(@Param("username") String username, @Param("password") String password);

    //修改登录状态
    int LoginState(@Param("isLogin") String isLogin,@Param("username") String username );

//    @Update("update users set loginTime = Now() where username = #{username}")
    int loginTime(@Param("loginTime") String loginTime,@Param("username") String username);

    //向用户表中插入新用户
    void registerzhuce(Users users);

    //修改密码----更新影响的是行数所以返回值要用int
    int XiuGaiPassword(@Param("username") String username,@Param("password") String password);

    //修改用户信息
    int XGUserinfo(@Param("name") String name,
                   @Param("sex") String sex,
                   @Param("birth") String birth,
                   @Param("number") String number,
                   @Param("QMing") String QMing,
                   @Param("username") String username);

    //修改头像
    int XGTX(@Param("userTX") String userTX , @Param("username") String username);

    int updateById(Users user);
}









