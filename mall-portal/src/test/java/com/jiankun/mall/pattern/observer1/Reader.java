package com.jiankun.mall.pattern.observer1;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/21 10:46
 */
public class Reader implements Observer {
    String name;

    public Reader(String name) {
        this.name = name;
    }

    @Override
    public void update(Subject subject) {
        NewsPaper newsPaper = (NewsPaper) subject;
        String content = newsPaper.getContent();
        System.out.println(name + "收到了报纸," + content);
    }
}
