package com.jiankun.mall.pattern.observer1;

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
        newsPaper.attach(reader1);
        newsPaper.attach(reader2);
        newsPaper.attach(reader3);
        newsPaper.setContent("本期内容:观察者模式");
    }
}
