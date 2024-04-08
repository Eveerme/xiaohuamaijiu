package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.chen.dao.UserInfoDao;
import com.chen.dto.WeChatSessionModel;
import com.chen.pojo.UserInfo;
import com.chen.service.UserInfoService;
import com.chen.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Date:2023/9/22
 * author:zmh
 * description: 用户信息业务层实现类
 **/


@Service
@Slf4j // 日志打印 ，调试用
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Value("${APPID}")
    private String appid;

    @Value("${APPSECRET}")
    private String appsecret;

    /**
     * 用于封装处理之后的结果（失败或成功的结果）
     */
    Map<String,Object> map = new HashMap<>();

    /**
     * 此对象在引导类（启动类）中：WechatLoginDemoApplication 中注入容器
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 登录验证
     * @param code 临时登录码
     * @return ·
     */
    @Override
    public Map<String, Object> checkLogin(String code) {
        // 根据传入code，调用微信服务器，获取唯一openid
        // 微信服务器接口地址
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+appid+ "&secret="+appsecret
                +"&js_code="+ code +"&grant_type=authorization_code";
        String errmsg = "";
        String errcode = "";
        String session_key = "";
        String openid = "";
        WeChatSessionModel weChatSessionModel;
        // 发送请求
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            String sessionData = responseEntity.getBody();
            Gson gson = new Gson();
            //将json字符串转化为实体类;
            weChatSessionModel = gson.fromJson(sessionData, WeChatSessionModel.class);
            log.info("返回的数据==>{}",weChatSessionModel);
            //获取用户的唯一标识
            openid = weChatSessionModel.getOpenid();
            //获取会话秘钥（具有时效性，用户登录的临时通行证）
            //登录后前端的每次接口请求都需带上session_key
            session_key = weChatSessionModel.getSession_key();
            //获取错误码
            errcode = weChatSessionModel.getErrcode();
            //获取错误信息
            errmsg = weChatSessionModel.getErrmsg();
        }else{
            log.info("出现错误，错误信息：{}",errmsg );
            // 登录失败，将错误信息放到map中返回，失败的map中只有一个数据
            map.put("errmsg",errmsg);
            return map;
        }
        // 判断是否成功获取到openid
        if ("".equals(openid) || openid == null){
            log.info("错误获取openid,错误信息:{}",errmsg);
            // 登录失败，将错误信息放到map中返回，失败的map中只有一个数据
            map.put("errmsg",errmsg);
            return map;
        }else{
            // 判断用户是否存在于我们的数据库中，目的是判断用户是否是第一次登录
            LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserInfo::getOpenid, openid);
            UserInfo userInfo = userInfoDao.selectOne(queryWrapper);
            // 不存在，将用户信息添加入数据库
            if (userInfo == null){
                // 填充初始信息
                UserInfo tempUserInfo = new UserInfo(UUID.randomUUID().toString(),openid,"微信用户","default.jpg");
                userInfoDao.insert(tempUserInfo);
                // 加入数据库成功，登录成功，返回两个数据：用户信息 和 token
                map.put("user",tempUserInfo);
                // 将用户id传入 生成token
                String token = JWTUtils.createToken(tempUserInfo.getId().toString());
                map.put("token",token);
                return map;
            }else{
                // 存在，将用户信息直接放到map中返回， 登录成功，返回两个数据：用户信息 和 token
                map.put("user",userInfo);
                String token = JWTUtils.createToken(userInfo.getId().toString());
                map.put("token",token);
                return map;
            }
        }
    }

}
