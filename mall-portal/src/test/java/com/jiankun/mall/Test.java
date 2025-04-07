package com.jiankun.mall;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/4/7 15:26
 */
public class Test {
    public static void main(String[] args) {
//        String str = "/page/user/list";
        String str = "/page";
        String[] split = str.split("/");
        for (String s : split) {
            System.out.println(s);
        }
        System.out.println(split.length);
    }
}
