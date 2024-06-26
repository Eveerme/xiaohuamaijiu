package com.chen.dto;

import lombok.Data;

/**
 * description: 前后端数据联调对象
 **/

@Data
public class R<T> {
    private Integer code; // 编码：1成功，0和其它数字为失败

    private String msg; // 错误信息

    private T data; // 数据

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 200;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }
}
