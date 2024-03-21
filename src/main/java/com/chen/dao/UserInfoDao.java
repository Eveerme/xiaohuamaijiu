package com.chen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description (UserInfo)表数据库访问层
 * @Date 2024/3/21 16:48
 * @Created by yx310
 */
@Mapper
public interface UserInfoDao extends BaseMapper<UserInfo> {

}

