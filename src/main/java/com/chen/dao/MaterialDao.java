package com.chen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.pojo.Material;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname MaterialDao
 * @Description TODO
 * @Date 2024/4/8 15:46
 * @Created by Eveerme
 */
@Mapper
public interface MaterialDao extends BaseMapper<Material> {
}
