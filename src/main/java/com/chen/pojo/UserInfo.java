package com.chen.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * (UserInfo)表实体类
 *
 * @author zmh
 * @since 2023-06-24 16:59:55
 */
@SuppressWarnings("serial") //忽略与序列化相关的警告
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_info")
public class UserInfo {
    //ID
    @TableId
    private String id;
    //微信唯一标识
    private String openid;
    //昵称
    private String userName;
    //注册时间
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    // 头像
    private String avatar;
    //更新时间
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    public UserInfo(String id, String openid, String userName, String avatar) {
        this.id = id;
        this.openid = openid;
        this.userName = userName;
        this.avatar = avatar;

    }
}

