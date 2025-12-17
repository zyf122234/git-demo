package com.example.springboothtml.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

//自动生成get和set方法
@Data
public class Users {
    private int id;
    private String username;
    private String password;
    private String userTX;
    private String sex;
    private String number;
    private String QMing;
    private String birth;
    private String name;
    private String isLogin;
    private String loginTime;
}
