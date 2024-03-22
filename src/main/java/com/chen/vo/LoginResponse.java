package com.chen.vo;

import com.chen.pojo.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname LoginResponse
 * @Description TODO
 * @Date 2024/3/22 13:41
 * @Created by yx310
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    // 用户名
    private String userName;
    // 头像
    private String avatar;
}
