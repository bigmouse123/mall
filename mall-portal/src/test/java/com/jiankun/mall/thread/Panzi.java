package com.jiankun.mall.thread;

import java.util.LinkedList;
import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/4/1 10:46
 */
public class Panzi {
    private LinkedList<Cake> list = new LinkedList<>();

    public synchronized void putCake(Cake cake) {
        if (list.size() == 5) {
            try {
                System.out.println(Thread.currentThread().getName() + " putCake wait");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        list.add(cake);
        System.out.println(Thread.currentThread().getName() + " putCake: " + cake);
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " putCake notify");
    }

    public synchronized Cake getCake() {
        if (list.size() == 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " getCake wait");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Cake cake = list.removeFirst();
        System.out.println(Thread.currentThread().getName() + " getCake: " + cake);
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " getCake notify");
        return cake;
    }
}
