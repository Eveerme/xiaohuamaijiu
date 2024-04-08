package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.dao.MaterialDao;
import com.chen.pojo.Material;
import com.chen.service.MaterialService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname MaterialServiceImpl
 * @Description TODO
 * @Date 2024/4/8 16:08
 * @Created by Eveerme
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialDao, Material> implements MaterialService {

    @Override
    public List<String> getNamesByIds(List<String> ids) {
        LambdaQueryWrapper<Material> lqw = new LambdaQueryWrapper<>();
        lqw.in(Material::getId, ids).select(Material::getName);
        List<String> names = list(lqw).parallelStream().map(Material::getName).collect(Collectors.toList());
        return names;
    }
}
