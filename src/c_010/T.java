package c_010;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 是可重入锁
 * 子类调用父类的同步方法，是否也是可重入的？
 * 答：可重入的
 * 这里猜测持有子类对象的锁时，同时就持有父类对象的锁
 * 具体的原理猜测与对象的mark word有关
 */
public class T {

    synchronized void m() {
        System.out.println("m start ");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m end ");
    }

    public static void main(String[] args) {
        TT tt = new TT();
        TU tu = new TU();
        new Thread(() -> tt.m()).start();
        new Thread(() -> tu.m()).start();
    }
}

class TT extends T {
    @Override 
    synchronized void m() {
        System.out.println(" child m1 start ");
        super.m();
        System.out.println(" child m1 end ");
    }
}

class TU extends T {
    @Override
    synchronized void m() {
        System.out.println(" child m2 start ");
        super.m();
        System.out.println(" child m2 end ");
    }
}

