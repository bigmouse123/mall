package com.jiankun.mall.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class OperLog {
    private Integer id;
    private String module;
    private String logType;
    private Integer adminId;
    private String adminName;
    private String requestMethod;
    private String requestUri;
    private String requestParams;
    private String responseParams;
    private String requestIp;
    private String serverAddress;
    private Integer exception;
    private String exceptionMsg;
    private Date startTime;
    private Date endTime;
    private Long  executeTime;
    private String userAgent;
    private String deviceName;
    private String browserName;
    private Integer deleted;
    private Date createTime;
    private Date updateTime;

}