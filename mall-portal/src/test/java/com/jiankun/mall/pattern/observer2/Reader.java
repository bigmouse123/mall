package com.jiankun.mall.pattern.observer2;

import java.util.Observable;
import java.util.Observer;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/21 11:14
 */
public class Reader implements Observer {
    String name;

    public Reader(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(name + "收到了报纸," + arg);
        NewsPaper newsPaper = (NewsPaper) o;
        System.out.println(name + "收到了报纸," + newsPaper.getContent());
    }
}
