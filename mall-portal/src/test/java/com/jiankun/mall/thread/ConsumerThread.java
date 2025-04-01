package com.jiankun.mall.thread;

import java.util.Random;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/4/1 11:33
 */
public class ConsumerThread extends Thread {
    private Panzi panzi;

    public ConsumerThread(String name,Panzi panzi) {
        super(name);
        this.panzi = panzi;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Cake cake = panzi.getCake();
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
