package com.xuecheng.search;

/**
 * @Author: 98050
 * @Time: 2019-06-11 19:35
 * @Feature:
 */
public class Singleton {
    private static volatile Singleton singleton;

    public static Singleton getInstance(){
        if (singleton == null){
            synchronized (Singleton.class){
                if (singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
