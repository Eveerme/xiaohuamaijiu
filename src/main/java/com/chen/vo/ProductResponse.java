package com.chen.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @Classname ProductResponse
 * @Description 封面参数
 * @Date 2024/4/8 15:34
 * @Created by Eveerme
 */@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductResponse {
    private String productId;
    private String productName;
    private String imagePath;
    // 原料列表
    private Map<String, String> materials;
}
