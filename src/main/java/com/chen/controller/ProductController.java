package com.chen.controller;

import com.chen.dto.R;
import com.chen.service.ProductService;
import com.chen.vo.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname ProductController
 * @Description TODO
 * @Date 2024/4/8 15:27
 * @Created by Eveerme
 */
@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("list")
    public R<List<ProductResponse>> getProductList() {
        List<ProductResponse> productList = productService.getProductList();
        return R.success(productList);
    }

}
