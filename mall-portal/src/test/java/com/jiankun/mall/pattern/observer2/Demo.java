package com.jiankun.mall.pattern.observer2;


/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/21 10:49
 */
public class Demo {
    public static void main(String[] args) {
        NewsPaper newsPaper = new NewsPaper();
        Reader reader1 = new Reader("张三");
        Reader reader2 = new Reader("李四");
        Reader reader3 = new Reader("王五");
        newsPaper.addObserver(reader1);
        newsPaper.addObserver(reader2);
        newsPaper.addObserver(reader3);
        newsPaper.setContent("本期内容:观察者模式");
    }
}
