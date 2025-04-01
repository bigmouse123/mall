package com.jiankun.mall.thread;

import com.jiankun.mall.pojo.Product;

import java.util.Random;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/4/1 10:46
 */
public class ProductThread extends Thread {
    private Panzi panzi;

    public ProductThread(String name, Panzi panzi) {
        super(name);
        this.panzi = panzi;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Cake cake = new Cake("no:" + i);
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            panzi.putCake(cake);
        }
    }
}
