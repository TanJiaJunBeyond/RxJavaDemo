package com.tanjiajun.rxjavademo;

/**
 * Created by TanJiaJun on 2020/8/23.
 */
public class Singleton {

    // 用关键字volatile修饰变量sInstance，禁止指令重排序优化
    private static volatile Singleton sInstance;

    // 私有构造方法
    private Singleton() {
        // 防止通过反射调用构造方法导致单例失效
        if (sInstance != null)
            throw new RuntimeException("Cannot construct a singleton more than once.");
    }

    // 获取单例的方法
    public static Singleton getInstance() {
        // 第一次判断sInstance是否为空，用于判断是否需要同步，提高性能和效率
        if (sInstance == null) {
            // 使用synchronized修饰代码块，取Singleton的Class对象来作为锁对象
            synchronized (Singleton.class) {
                // 第二次判断sInstance是否为空，用于判断是否已经创建实例
                if (sInstance == null) {
                    // 创建Singleton对象
                    sInstance = new Singleton();
                }
            }
        }
        // 返回sInstance
        return sInstance;
    }

}