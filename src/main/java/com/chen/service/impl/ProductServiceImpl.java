package com.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.dao.MaterialDao;
import com.chen.dao.ProductDao;
import com.chen.dao.ProductMaterialDao;
import com.chen.pojo.Product;
import com.chen.pojo.ProductMaterial;
import com.chen.service.MaterialService;
import com.chen.service.ProductMaterialService;
import com.chen.service.ProductService;
import com.chen.vo.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Classname ProductServiceImpl
 * @Description TODO
 * @Date 2024/4/8 15:28
 * @Created by Eveerme
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductMaterialService productMaterialService;
    @Autowired
    private ProductMaterialDao productMaterialDao;
    @Autowired
    private MaterialService materialService;
    @Override
    public List<ProductResponse> getProductList() {
        List<ProductResponse> productResponses = new ArrayList<>();
        List<Product> products = list();
        for (Product product : products) {
            ProductResponse productResponse = new ProductResponse();
            String productId = product.getId();
            LambdaQueryWrapper<ProductMaterial> lqw = new LambdaQueryWrapper<>();
            lqw.eq(ProductMaterial::getProductId, productId).select(ProductMaterial::getMaterialId);
            List<String> materialIds = productMaterialService.getMaterialIdsByProductId(productId);
            List<String> names = materialService.getNamesByIds(materialIds);
            Map<String, String> materialMaps = IntStream.range(0, Math.min(materialIds.size(), names.size()))
                    .boxed()
                    .collect(Collectors.toMap(materialIds::get, names::get));
            productResponse.setProductId(productId);
            productResponse.setProductName(product.getName());
            productResponse.setImagePath("");
            productResponse.setMaterials(materialMaps);
            productResponses.add(productResponse);
        }
        return productResponses;
    }
}
