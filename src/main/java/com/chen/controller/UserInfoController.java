package com.chen.controller;

import com.chen.dto.R;
import com.chen.dto.WeChatModel;
import com.chen.pojo.UserInfo;
import com.chen.service.UserInfoService;
import com.chen.utils.JWTUtils;
import com.chen.vo.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description TODO
 * @Date 2024/3/21 16:48
 * @Created by yx310
 */

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/userInfos")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 微信登录
     * @param weChatModel 获取临时凭证code
     * @param response ·
     * @return 返回执行结果
     */
    @PostMapping("/login")
    public R<LoginResponse> loginCheck(@RequestBody WeChatModel weChatModel, HttpServletResponse response){
        // 登录检查，失败返回的map中只有一个数据（错误信息），成功的返回有两个数据（用户信息和token）
        Map<String, Object> resultMap = userInfoService.checkLogin(weChatModel.getCode());
        // 检查返回的map的长度，判断是否登录操作成功
        if (resultMap.size() > 1){
            // 登录成功，将用户信息放到响应体返回，将token放到响应头返回
            log.info("创建的token为=>{}", resultMap.get("token"));
            // 将token添加入响应头以及返回用户信息
            response.setHeader(JWTUtils.header, (String) resultMap.get("token"));
            LoginResponse loginResponse = new LoginResponse();
            UserInfo user = (UserInfo) resultMap.get("user");
            loginResponse.setUserName(user.getUserName());
            loginResponse.setAvatar(user.getAvatar());
            return R.success(loginResponse); // 注意，返回的用户信息是字符串，前端需要解析一下
        }else{
            // 反之，失败返回错误信息
            return R.error(resultMap.get("errmsg").toString());
        }
    }

}
