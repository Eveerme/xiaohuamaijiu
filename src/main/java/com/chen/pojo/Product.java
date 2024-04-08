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
 * @Classname Product
 * @Description TODO
 * @Date 2024/4/8 14:45
 * @Created by Eveerme
 */
@SuppressWarnings("serial") //忽略与序列化相关的警告
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("product")
public class Product {
    //ID
    @TableId
    private String id;
    private String name;
    //描述
    private String description;
    // 状态
    private int status;
    //注册时间
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
