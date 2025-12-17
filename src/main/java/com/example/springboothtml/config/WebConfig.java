package com.example.springboothtml.config;
import com.example.springboothtml.interceptors.LoginInterceptor;
import com.example.springboothtml.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Autowired
//    private LoginInterceptor interceptors;
//    @Override
//    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
//        registry.addInterceptor(interceptors)
//                .addPathPatterns("/my")
//                .excludePathPatterns("/login",
//                        "/register",
//                        "/register/yanzheng",
//                        "/XiuGaipassword",
//                        "/XiugaiUserInfo",
//                        "/XGtouxiang");
//    }
//}
