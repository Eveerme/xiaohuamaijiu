package com.chen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.pojo.Product;
import com.chen.vo.ProductResponse;

import java.util.List;

/**
 * @Classname ProductService
 * @Description TODO
 * @Date 2024/4/8 15:28
 * @Created by Eveerme
 */
public interface ProductService extends IService<Product> {
    List<ProductResponse> getProductList();
}
