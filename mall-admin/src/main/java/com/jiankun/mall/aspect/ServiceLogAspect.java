package com.jiankun.mall.aspect;

import com.jiankun.mall.service.impl.AdminServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/18 20:55
 */
@Aspect
@Component
public class ServiceLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Pointcut(value = "execution(* com.jiankun.mall.service.impl.*.*(..))")
    public void point() {
    }

    @Before(value = "point()")
    public void before() {
        System.out.println("transaction begin");
    }

    // 切面=通知+切入点pointcut
    @Around("point()")
    public Object runTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("=====开始执行 {}.{}========", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());

        long start = System.currentTimeMillis();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        //调用目标对象中的方法，就是service中方法
        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();
        long time = end - start;
        if (time >= 3000) {
            logger.error("==========执行结束，耗时{}毫秒==========", time);
        } else if (time >= 2000) {
            logger.warn("==========执行结束，耗时{}毫秒==========", time);
        } else {
            logger.info("==========执行结束，耗时{}毫秒==========", time);
        }

        return result;
    }
}
