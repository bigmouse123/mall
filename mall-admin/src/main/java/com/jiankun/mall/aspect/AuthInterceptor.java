package com.jiankun.mall.aspect;

import com.jiankun.mall.annotation.AuthCheck;
import com.jiankun.mall.pojo.enums.AdminRoleEnum;
import com.jiankun.mall.pojo.Admin;
import com.jiankun.mall.pojo.enums.AdminRoleEnum;
import com.jiankun.mall.service.IAdminService;
import com.jiankun.mall.util.Result;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/29 14:47
 */
@Aspect
@Component
public class AuthInterceptor {
    @Resource
    private IAdminService adminService;

    /**
     * @param joinPoint 切入点
     * @param authCheck 权限校验注解
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        //获取当前登录用户
        Admin loginAdmin = adminService.getLoginAdmin(request);
        AdminRoleEnum mustRoleEnum = AdminRoleEnum.getEnumByValue(Byte.valueOf(mustRole));
        //如果不需要权限,放行
        if (mustRoleEnum == null) {
            return joinPoint.proceed();
        }
        //以下代码如果有权限才会通过
        AdminRoleEnum userRoleEnum = AdminRoleEnum.getEnumByValue(loginAdmin.getRole());
        if (userRoleEnum == null) {
            return Result.error("权限不足");
        }
        //如果需要管理员权限但是用户没有管理员权限
        if (AdminRoleEnum.SUPER_ADMIN.equals(mustRoleEnum) && !AdminRoleEnum.SUPER_ADMIN.equals(userRoleEnum)) {
            return Result.error("权限不足");
        }
        //通过权限校验，放行
        System.out.println("放行");
        return joinPoint.proceed();
    }
}