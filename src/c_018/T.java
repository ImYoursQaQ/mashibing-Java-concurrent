package c_018;

import java.util.concurrent.TimeUnit;

/**
 * 不要以字符串常量作为锁定对象
 * 在下面的例子中， m1和m2其实是锁定的同一对象
 * 这种情况下，还会可能与其他类库发生死锁，比如某类库中也锁定了字符串 "Hello"
 * 但是无法确认源码的具体位置，所以两个 "Hello" 将会造成死锁
 * 因为你的程序和你用的类库无意间使用了同一把锁
 */
public class T {

    String s1 = "Hello1";
    String s2 = "Hello2";
    
    void m1() {
        synchronized (s1) {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"持有"+s1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (s2){

            }
            
        }
    }

    void m2() {
        synchronized (s2) {
            System.out.println(Thread.currentThread().getName()+"持有"+s2);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (s1){

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t1 = new T();
        Thread thread1 = new Thread(() -> t1.m1(),"thread1");
        Thread thread2 = new Thread(() -> t1.m2(),"thread2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
