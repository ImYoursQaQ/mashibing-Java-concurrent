package c_025;

import java.sql.Time;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 使用阻塞有界同步队列 ArrayBlockingQueue 完成生产者消费者模式
 */
public class T06_ArrayBlockingQueue {


    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(20);

        //使用一个线程生产
//        new Thread(()->{
//            for (int i = 0; i < 100; i++) {
//                try {
//                    queue.put("元素"+i);
//                    System.out.println("生产了:元素"+i);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        //使用5个线程生产
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                int index = 0;
                while (true){
                    try {
                        queue.put("元素："+Thread.currentThread().getName()+":序号"+index);
                        index++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"thread"+i).start();
        }
        TimeUnit.MILLISECONDS.sleep(1000);
        for (int j = 0; j < 100; j++) {
            new Thread(()->{
                try {
                    System.out.println("消费了:"+queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }
//        for (int i = 0; i < 10; i++) {
//            queue.put("a" + i);
//        }
//
//        //queue.put("a11"); // 满了会阻塞
//        //queue.add("a11"); // 满了会抛出异常
//        //System.out.println(queue.offer("a11")); // 满了会返回false
//        System.out.println(queue.offer("a11", 1, TimeUnit.SECONDS)); // 会等待1s,返回false, 如果1s内有空闲,则添加成功后返回true

    }

}
