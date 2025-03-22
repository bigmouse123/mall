package com.jiankun.mall.pattern.observer2;

import java.util.Observable;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/21 11:13
 */
public class NewsPaper extends Observable {
    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        setChanged();
        notifyObservers(content);
    }
}
