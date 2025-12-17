package com.example.springboothtml.Server;

import com.example.springboothtml.entity.Users;

import java.time.LocalDateTime;

//服务层接口处理mapper层实现的方法
public interface UserServer {

    //根据用户名查看有没有这个用户
    String loginByusername(String username);

    //获取登录状态
    String LoginState(Users users);
    //添加登录时间
    int loginTime(String loginTime,String username);

//    int loginTime(String username);

    //返回用户类型(根据用户名和密码查看有没有和用户)
    Users loginByusernameAndpassword(String username,String password);

    //注册用户
    String registerzhuce(Users users);

    int XiuGaiPassword(String username,String password);

    //修改用户信息
    int XGUserinfo(String name, String sex, String birth, String number, String QMing, String username);

    //修改用户头像
    int XGtouxiang (String userTX ,String username);
}
