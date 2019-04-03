package c_022;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal 线程局部变量
 */
public class ThreadLocal1 {

    volatile static Person p = new Person();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);

        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "lisi";
        }).start();
    }
}

class Person {
    String name = "zhangsan";
}


/*
在加与不加volatile的情况下，这句话打印的值分别是？ 答：不写volatile有可能发生可见性问题
跟线程1的睡眠时间有关
如果想要共享变量不可见呢？  就需要使用ThreadLocal

 */