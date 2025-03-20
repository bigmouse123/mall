package com.jiankun.mall.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/20 9:53
 */
@RestController
public class HelloController {

    @Autowired
    private RedissonClient redissonClient;


    @GetMapping("/hello")
    public String hello() {
        //1、获取一把锁，只要锁的名字一样，就是同一把锁
        RLock lock = redissonClient.getLock("my-lock");
        //2、加锁  阻塞式等待。默认加的锁都是30s,lockWatchdogTimeout = 30 * 1000 【看门狗默认时间】
        lock.lock();
        //1）、锁的自动续期，如果业务超长，运行期间自动锁上新的30s。不用担心业务时间长，锁自动过期被删掉
        //只要占锁成功，就会启动一个定时任务【重新给锁设置过期时间，新的过期时间就是看门狗的默认时间】,每隔10秒都会自动的再次续期，续成30秒
        // internalLockLeaseTime 【看门狗时间】 / 3， 10s
        //2）、加锁的业务只要运行完成，就不会给当前锁续期，即使不手动解锁，锁默认会在30s内自动过期，不会产生死锁问题
        try {
            System.out.println("加锁成功，执行业务..." + Thread.currentThread().getId());
            try {
                TimeUnit.SECONDS.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //3、解锁  假设解锁代码没有运行，Redisson会不会出现死锁
            System.out.println("释放锁..." + Thread.currentThread().getId());
            lock.unlock();
        }

        return "hello";
    }
}
