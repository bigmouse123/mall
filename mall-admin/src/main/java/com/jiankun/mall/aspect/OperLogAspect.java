package com.jiankun.mall.aspect;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiankun.mall.annotation.MyLog;
import com.jiankun.mall.pojo.Admin;
import com.jiankun.mall.pojo.OperLog;
import com.jiankun.mall.service.IOperLogService;
import com.jiankun.mall.util.PageResult;
import com.jiankun.mall.util.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/18 20:29
 */
@Log4j2
@Aspect
@Component
public class OperLogAspect {
    /**
     * 日志级别-INFO
     */
    String LOG_INFO = "INFO";

    /**
     * 日志级别-DEBUG
     */
    String LOG_DEBUG = "DEBUG";

    /**
     * 日志级别-ERROR
     */
    String LOG_ERROR = "ERROR";


    //ThreadLocal是线程隔离的
    private ThreadLocal<OperLog> operLogThreadLocal = new ThreadLocal<>();

    @Autowired
    private IOperLogService operLogService;


    /**
     * 日志切点
     */
//    @Pointcut("execution(public * com.jiankun.springboot.controller.*.*(..))")
    @Pointcut("@annotation(com.jiankun.mall.annotation.MyLog)")
    public void operLogAspect() {

    }

    /**
     * 前置通知
     *
     * @param joinPoint
     * @throws Throwable
     */
//    @Before(value = "operLogAspect()")
    @Before("@annotation(myLog)")
    public void doBefore(JoinPoint joinPoint, MyLog myLog) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        // 获取 @MyLog 注解的参数
        String module = myLog.module();
        // 构建 OperLog 对象
        OperLog operLog = new OperLog();
        operLog.setModule(module);
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            operLog.setAdminId(admin.getId());
            operLog.setAdminName(admin.getName());
        }
        operLog.setStartTime(new Date());
        operLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
        operLog.setRequestParams(formatParams(request.getParameterMap()));
        operLog.setRequestMethod(request.getMethod());
        operLog.setRequestIp(JakartaServletUtil.getClientIP(request));
        operLog.setServerAddress(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
        String userAgentStr = request.getHeader("User-Agent");
        operLog.setUserAgent(userAgentStr);
        UserAgent userAgent = UserAgentUtil.parse(userAgentStr);
        operLog.setDeviceName(userAgent.getOs().getName());
        operLog.setBrowserName(userAgent.getBrowser().getName());

        operLogThreadLocal.set(operLog);

        log.info("开始计时: {}  URI: {}  IP: {}", operLog.getStartTime(), operLog.getRequestUri(), operLog.getRequestIp());
    }

    /**
     * 返回通知
     *
     * @param ret
     */
//    @AfterReturning(pointcut = "operLogAspect()", returning = "ret")
    @AfterReturning(value = "@annotation(myLog)", returning = "ret")
    public void doAfterReturning(Object ret, MyLog myLog) {
        // 获取 @MyLog 注解的参数
        String module = myLog.module();
        OperLog operLog = operLogThreadLocal.get();
        operLog.setModule(module);
        operLog.setLogType(LOG_INFO);
        operLog.setEndTime(new Date());
        operLog.setExecuteTime(Long.valueOf(ChronoUnit.MILLIS.between(LocalDateTime.ofInstant(operLog.getStartTime().toInstant(), ZoneId.systemDefault()),
                LocalDateTime.ofInstant(operLog.getEndTime().toInstant(), ZoneId.systemDefault()))));
        operLog.setException(0);
        if (ret instanceof Result) {
            Result r = Convert.convert(Result.class, ret);
            try {
                operLog.setResponseParams(new ObjectMapper().writeValueAsString(r));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        } else if (ret instanceof PageResult) {
            PageResult r = Convert.convert(PageResult.class, ret);
            try {
                operLog.setResponseParams(new ObjectMapper().writeValueAsString(r));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        } else if (ret instanceof String) {//转发界面
            operLog.setResponseParams((String) ret);
        }
        operLogService.add(operLog);
        operLogThreadLocal.remove();

        Runtime runtime = Runtime.getRuntime();
        log.info("计时结束: {}  用时: {}ms  URI: {} ", operLog.getEndTime(), operLog.getExecuteTime(),
                operLog.getRequestUri());
    }

    /**
     * 异常通知
     *
     * @param e
     */
//    @AfterThrowing(pointcut = "operLogAspect()", throwing = "e")
    @AfterThrowing(value = "@annotation(myLog)", throwing = "e")
    public void doAfterThrowable(Throwable e, MyLog myLog) {
        // 获取 @MyLog 注解的参数
        String module = myLog.module();
        OperLog operLog = operLogThreadLocal.get();
        operLog.setModule(module);
        operLog.setLogType(LOG_ERROR);
        operLog.setEndTime(new Date());
        //operLog.setExecuteTime(Long.valueOf(ChronoUnit.MINUTES.between(operLog.getStartTime(), operLog.getEndTime())));
        operLog.setExecuteTime(Long.valueOf(ChronoUnit.MILLIS.between(LocalDateTime.ofInstant(operLog.getStartTime().toInstant(), ZoneId.systemDefault()),
                LocalDateTime.ofInstant(operLog.getEndTime().toInstant(), ZoneId.systemDefault()))));
        operLog.setException(1);
        operLog.setExceptionMsg(e.getMessage());
        operLogService.add(operLog);
        operLogThreadLocal.remove();

        log.info("计时结束: {}  用时: {}ms  URI: {} ", operLog.getEndTime(), operLog.getExecuteTime(),
                operLog.getRequestUri());
    }

    /**
     * 格式化参数
     *
     * @param parameterMap
     * @return
     */
    private String formatParams(Map<String, String[]> parameterMap) {
        if (parameterMap == null) {
            return null;
        }
        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String[]> param : (parameterMap).entrySet()) {
            if (params.length() != 0) {
                params.append("&");
            }
            params.append(param.getKey() + "=");
            if (StrUtil.endWithIgnoreCase(param.getKey(), "password")) {
                params.append("*");
            } else if (param.getValue() != null) {
                params.append(ArrayUtil.join(param.getValue(), ","));
            }
        }
        return params.toString();
    }

}
