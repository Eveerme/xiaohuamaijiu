package com.chen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.pojo.ProductMaterial;

import java.util.List;

/**
 * @Classname ProductMaterialService
 * @Description TODO
 * @Date 2024/4/8 15:54
 * @Created by Eveerme
 */
public interface ProductMaterialService extends IService<ProductMaterial> {
    public List<String> getMaterialIdsByProductId(String id);
}
