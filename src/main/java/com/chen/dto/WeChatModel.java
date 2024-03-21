package com.chen.dto;

import lombok.Data;

/**
 * Date:2023/6/24
 * author:zmh
 * description: 接收小程序传入参数
 **/

@Data
public class WeChatModel {

    /**
     * 临时登录凭证
     */
    private String code;

    /**
     * 微信头像
     */
    private String avatarUrl;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 微信服务器上的唯一id
     */
    private String openId;
}
