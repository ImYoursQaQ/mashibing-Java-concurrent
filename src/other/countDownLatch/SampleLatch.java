package other.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 使用countdownLatch实现程序计数退出功能
 */
public class SampleLatch {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i <100 ; i++) {
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                    //另一种写法：Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            },"thread"+i).start();
        }

        try {
            latch.await();
            System.out.println("主线程执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
