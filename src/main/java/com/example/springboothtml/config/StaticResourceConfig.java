package com.example.springboothtml.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 必须加这个注解，标记为配置类
public class StaticResourceConfig implements WebMvcConfigurer { // 必须实现WebMvcConfigurer

    // 1. 本地图片保存路径（和你的实际保存路径完全一致，用正斜杠）
    private static final String IMAGE_LOCAL_PATH = "file:D:/零碎/code/SpringBootCode/springboot-image/";
    // 2. 前端访问URL前缀（和数据库中的URL一致，即 /images/）
    private static final String IMAGE_URL_PREFIX = "/images/**";

    // 重写addResourceHandlers方法，注册映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 关键：将URL前缀 /images/** 映射到本地路径
        registry.addResourceHandler(IMAGE_URL_PREFIX)
                .addResourceLocations(IMAGE_LOCAL_PATH);
        // （可选）如果有其他静态资源（如CSS、JS），可加在这里，当前只需要图片映射
    }
}
