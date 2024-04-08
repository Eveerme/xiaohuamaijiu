package com.chen.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Classname ProductCategroy
 * @Description TODO
 * @Date 2024/4/8 14:42
 * @Created by Eveerme
 */
@SuppressWarnings("serial") //忽略与序列化相关的警告
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("product_material")
public class ProductMaterial {
    //ID
    @TableId
    private String id;
    // 状态
    private int status;
    // 产品id
    private String productId;
    // 材料ID
    private String materialId;
    //注册时间
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
