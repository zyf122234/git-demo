package com.example.springboothtml.Controller;

import com.example.springboothtml.Server.UserServer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.springboothtml.entity.Users;
import com.example.springboothtml.Common.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@RestController
@RequestMapping("/")
//前端跨域处理
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
    // 图片存储根目录（需与application.properties中一致）
    private static final String IMAGE_SAVE_PATH = "D:\\零碎\\code\\SpringBootCode\\springboot-image\\";
    @Autowired
    private UserServer userServer;

    //处理登录逻辑
    // 登录接口：POST /login
    @PostMapping("/login")
    // 用实体类接收请求体（包含username和password）
    public Result<Users> login(@RequestBody Users user,HttpServletRequest request) {
        String Byusername = userServer.loginByusername(user.getUsername());
        if (Byusername.equals("无用户")) {
            //调用server层方法，并传入数据（根据用户名和密码进行验证）
            return Result.fail("无用户，请先注册");
        }else {
            Users loginUser = userServer.loginByusernameAndpassword(user.getUsername(), user.getPassword());
            if (loginUser != null) {

                //用户登录以后，将登陆状态存到session中
                request.getSession().setAttribute("loginUser", user.getUsername());
                return Result.success(loginUser, "存在该用户");
            } else {
                return Result.fail("密码错误");
            }
        }
    }

    //添加登录时间
    @PostMapping("/loginTime")
    public Result<Users> loginTime(@RequestParam String username){
        LocalDateTime loginTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String custom = loginTime.format(formatter);
        int i = userServer.loginTime(custom,username);
        System.out.println("111");
        return Result.success(null,"登录时间更新成功");
    }

    //修改登录状态
//    @PutMapping("/LoginState")
//    public Result<String> LoginState(@RequestBody Users users){
//        if (userServer.LoginState(users).equals("已登录")){
//            return Result.success(null,"已登录");
//        }
//        return Result.fail("未登录");
//    }

    //处理注册逻辑
    @PostMapping("/register")
    public Result<String> register(@RequestBody Users users){
        String result =  userServer.registerzhuce(users);
        if (result.equals("注册成功")){
            return Result.success(null,result);
        }else {
            return Result.fail(result);
        }
    }
    //验证用户是否存在
    @PostMapping("/register/yanzheng")
    public Result<Users> registeryz(@RequestBody Users user){
        String Byusername = userServer.loginByusername(user.getUsername());
        if (Byusername.equals("用户名已存在")){
            return Result.success(null ,"用户名已存在，请重新输入");
        }else {
            return Result.success(null ,"请创建用户");
        }
    }


    //修改密码
    @PutMapping("/XiuGaipassword")
    public Result<Users> UpdatePassword(@RequestBody Users users){
        if (userServer.loginByusername(users.getUsername()).equals("无用户")) {
            return Result.fail("修改失败，请先创建用户");
        }else {
            //当用户存在时，根据用户名修改
             int count =  userServer.XiuGaiPassword(users.getUsername(), users.getPassword());
             if (count != 0){
                 return Result.success(null,"修改成功");
              }else {
                  return Result.fail("修改失败");
             }
        }
    }

    //修改用户信息
    @PutMapping("/XiugaiUserInfo")
    public Result<Users> UpdateUserInfo(@RequestBody Users users){
        int count;
        try {
            count =  userServer.XGUserinfo(users.getName(),users.getSex(),users.getBirth(),users.getNumber(),users.getQMing(),users.getUsername());
        }catch (RuntimeException e){
            return Result.fail("未修改");
        }
        if (count != 0){
            return Result.success(null,"修改成功");
        }else {
            return Result.fail("修改失败");
        }
    }
    //修改用户头像
    @PostMapping("/XGtouxiang")
    public Result<String> UpdateTx(@RequestParam MultipartFile avatar, @RequestParam String username){
        // 1. 校验文件：非空 + 是图片
        if (avatar.isEmpty()) {
            return Result.fail("请选择图片文件");
        }
        String contentType = avatar.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return Result.fail("请上传图片类型文件");
        }

        // 2. 处理文件名：用UUID避免重复（如：a1b2c3d4.jpg）
        String originalFilename = avatar.getOriginalFilename();
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".")); // 获取后缀（.jpg/.png）
        String newFilename = UUID.randomUUID().toString() + fileSuffix; // 生成唯一文件名

        // 3. 存储文件到本地目录
        File saveFile = new File(IMAGE_SAVE_PATH + newFilename);
        try {
            avatar.transferTo(saveFile); // 保存文件
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("图片存储失败，请重试");
        }
        // 4. 生成永久访问URL（对应前端访问路径）
        //后端一旦运行，改路径就可用
        String permanentUrl = "http://localhost:8081/images/" + newFilename;
        userServer.XGtouxiang(permanentUrl,username);
        // 6. 返回永久URL给前端
        return Result.success(permanentUrl,"永久图片路径");
    }

    //跳转我的页面 拦截器
    @PostMapping("/my")
    public Result<Users> my(@RequestParam String isLogin){

        if (isLogin.equals("true")){
            return Result.success(null,"已登录");
        }
        return Result.fail("未登录");
    }
}

















