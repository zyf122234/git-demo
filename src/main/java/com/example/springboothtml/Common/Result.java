package com.example.springboothtml.Common;

import org.springframework.stereotype.Component;

//统一响应体
//@Component
public class Result<T> {
    private boolean success;
    private T data;
    private String msg;

    // 成功响应
    public static <T> Result<T> success(T data, String msg) {
        Result<T> r = new Result<>();
        r.success = true;
        r.data = data;
        r.msg = msg;
        return r;
    }

    // 失败响应
    public static <T> Result<T> fail(String msg) {
        Result<T> r = new Result<>();
        r.success = false;
        r.msg = msg;
        return r;
    }
    // Getter 方法（必须有，否则 JSON 序列化失败）
    public boolean isSuccess() { return success; }
    public T getData() { return data; }
    public String getMsg() { return msg; }
}
