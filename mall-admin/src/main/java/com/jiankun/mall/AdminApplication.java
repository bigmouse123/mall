package com.jiankun.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

// 声明该类是一个SpringBoot的引导类
@SpringBootApplication
// @MapperScan注解，扫描MyBatis Mapper接口类
@MapperScan("com.jiankun.mall.mapper")
//@ComponentScan({"com.jiankun.mall.util"}) // 添加工具类所在包
// quartz定时任务
@EnableScheduling
// 开启缓存
@EnableCaching
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
