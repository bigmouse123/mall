package com.jiankun.mall.thread;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/4/1 11:35
 */
public class ThreadDemo {
    public static void main(String[] args) {
        Panzi panzi = new Panzi();
        new ProductThread("生产者线程", panzi).start();
        new ConsumerThread("消费者线程", panzi).start();
    }
}
