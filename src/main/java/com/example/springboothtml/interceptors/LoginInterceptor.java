package com.example.springboothtml.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

// 类名规范：首字母大写，建议改为 LoginInterceptor
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 修复1：优先从Session获取登录状态（最安全，避免参数伪造）
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser != null) {
            // 已登录，直接放行（不写任何响应，留给接口处理）
            return true;
        }

        // 兼容方案：从URL参数获取isLogin（兜底，先判空避免NPE）
        String isLogin = request.getParameter("isLogin");
        System.out.println("拦截器获取的isLogin：" + isLogin);
        if ("true".equals(isLogin)) { // 常量在前，避免空指针
            return true;
        }

        // 未登录：返回JSON格式响应（与前端统一），并拦截
        sendJsonResponse(response, "{\"code\":401,\"msg\":\"未登录，请先登录\",\"data\":null}");
        return false;
    }

    /**
     * 修复2：统一返回JSON格式响应（与前端解析逻辑匹配）
     */
    private void sendJsonResponse(HttpServletResponse response, String message) throws Exception {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401未授权，语义更规范
        response.setContentType("application/json;charset=UTF-8"); // 改为JSON格式
        response.getWriter().write(message);
        response.getWriter().flush();
    }
}