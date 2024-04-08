package com.chen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname ProductDao
 * @Description TODO
 * @Date 2024/4/8 15:29
 * @Created by Eveerme
 */
@Mapper
public interface ProductDao extends BaseMapper<Product> {
}
