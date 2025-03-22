package com.jiankun.mall.pattern.observer1;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/21 10:46
 */
public class NewsPaper extends Subject {
    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        notifyObservers();
    }
}
