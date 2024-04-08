package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.dao.ProductMaterialDao;
import com.chen.pojo.ProductMaterial;
import com.chen.service.ProductMaterialService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname ProductMaterialServiceImpl
 * @Description TODO
 * @Date 2024/4/8 15:55
 * @Created by Eveerme
 */
@Service
public class ProductMaterialServiceImpl extends ServiceImpl<ProductMaterialDao, ProductMaterial> implements ProductMaterialService {

    @Override
    public List<String> getMaterialIdsByProductId(String id) {
        LambdaQueryWrapper<ProductMaterial> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ProductMaterial::getProductId, id).select(ProductMaterial::getMaterialId);
        List<String> materials = list(lqw).parallelStream().map(ProductMaterial::getMaterialId).collect(Collectors.toList());
        return materials;
    }
}
