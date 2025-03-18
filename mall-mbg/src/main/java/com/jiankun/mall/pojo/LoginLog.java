package com.jiankun.mall.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/18 21:10
 */
@Data
public class LoginLog {
    private Integer id;          // 访问ID
    private Integer adminId;     // 用户id
    private String adminName;    // 用户名
    private String ip;           // 登录IP地址
    private String msg;          // 提示信息
    private Date accessTime;     // 访问时间
    private Integer status;      // 状态（1：正常 0：停用）
    private Integer deleted;   // 逻辑删除
    private Date createTime;     // 创建时间
    private Date updateTime;     // 更新时间
}
