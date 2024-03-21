package com.chen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.pojo.UserInfo;

import java.util.Map;

/**
 * 用户信息业务层接口
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 登录检查
     * @param code 临时登录凭证
     * @return 用户信息和token
     */
    public Map<String,Object> checkLogin(String code);

}
