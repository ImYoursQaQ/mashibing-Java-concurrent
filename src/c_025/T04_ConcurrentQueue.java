package c_025;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 同步队列，ConcurrentQueue
 */
public class T04_ConcurrentQueue {


    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedQueue<>(); // LinkedQueue，无界队列

        for (int i = 0; i < 10; i++) {
            queue.offer("a" + i); // 有返回值，返回false代表没有加入成功，true 代表成功，并且此方法不会阻塞
            //区别于queue.add()方法，add方法在空间不足时抛出异常
        }

        System.out.println(queue);
        System.out.println(queue.size());

        System.out.println(queue.poll()); // 取出队头,若队列已空，则返回null
        System.out.println(queue.size());

        System.out.println(queue.peek()); // 取出队头，但是不删除队头，若队列已空，则返回null
        System.out.println(queue.size());
        System.out.println("test作者");
        
        // 双端队列 Deque 发音： dai ke
        //Deque<String> deque = new ConcurrentLinkedDeque<>();
        //deque.addFirst();
        //deque.addLast();
        //deque.pollFirst();
        //deque.pollLast();
        //deque.peekFirst();
        //deque.peekLast();
    }
    
}
