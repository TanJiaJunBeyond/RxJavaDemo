package com.tanjiajun.rxjavademo;

/**
 * Created by TanJiaJun on 2019-11-14.
 */
public class Singleton {

    // mInstance用volatile修饰，保证指令执行的顺序
    private static volatile Singleton mInstance;

    // 私有构造函数
    private Singleton() {
        // 防止通过反射调用构造函数造成单例失效
        if (mInstance != null) {
            throw new RuntimeException("Cannot construct a singleton more than once.");
        }
    }

    // 获取单例的方法
    public static Singleton getInstance() {
        // 第一次判断mInstance是否为null，判断是否需要同步，提高性能和效率
        if (mInstance == null) {
            synchronized (Singleton.class) {
                // 第二次判断mInstance是否为null，判断是否已经创建实例
                if (mInstance == null) {
                    mInstance = new Singleton();
                }
            }
        }
        // 返回mInstance
        return mInstance;
    }

}
