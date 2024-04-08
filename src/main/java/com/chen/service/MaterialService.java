package com.chen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.pojo.Material;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname MaterialService
 * @Description TODO
 * @Date 2024/4/8 16:08
 * @Created by Eveerme
 */
public interface MaterialService extends IService<Material> {
    List<String> getNamesByIds(List<String> ids);
}
