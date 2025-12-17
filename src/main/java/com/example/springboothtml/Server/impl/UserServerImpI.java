package com.example.springboothtml.Server.impl;

import com.example.springboothtml.Mapper.UserMapper;
import com.example.springboothtml.Server.UserServer;
import com.example.springboothtml.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServerImpI implements UserServer {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String loginByusername(String username) {
        Users Byusername =  userMapper.loginByusername(username);
        if (Byusername != null){
            return "用户名已存在";
        }
        return "无用户";
    }

    @Override
    public String LoginState(Users users) {
        int count = userMapper.LoginState(users.getIsLogin(),users.getUsername());
        if(count>0){
            return "已登录";
        }
        return "未登录";
    }


    @Override
    public int loginTime(String loginTime,String username) {
        int i = userMapper.loginTime(loginTime,username);
        return i;
    }


    //根据用户名和密码查询用户信息
    @Override
    public Users loginByusernameAndpassword(String username,String password) {
        System.out.println(userMapper.loginByusernameAndpassword(username,password));
        return userMapper.loginByusernameAndpassword(username,password);
    }

    @Override
    public String registerzhuce(Users users) {
        userMapper.registerzhuce(users);
        return "注册成功";
    }

    @Override
    public int XiuGaiPassword(String username, String password) {
//        System.out.println(userMapper.XiuGaiPassword(username,password));
           return userMapper.XiuGaiPassword(username,password);
    }

    @Override
    public int XGUserinfo(String name, String sex, String birth, String number, String QMing,String username) {
        boolean hasUpdateField = false;
        if ( name != null && !name.isEmpty()) hasUpdateField = true;
        if (sex != null) hasUpdateField = true;
        if (birth != null) hasUpdateField = true;
        if (number != null && !number.isEmpty()) hasUpdateField = true;
        if (QMing != null && !QMing.isEmpty()) hasUpdateField = true;

        // 若没有需要更新的字段，直接返回，避免执行错误SQL
        if (!hasUpdateField) {
            throw new RuntimeException("至少需要传入一个非空字段进行更新");
        }
        return userMapper.XGUserinfo(name,sex,birth,number,QMing,username);
    }

    //修改头像
    @Override
    public int XGtouxiang(String userTX, String username) {
        return userMapper.XGTX(userTX,username);
    }


}
