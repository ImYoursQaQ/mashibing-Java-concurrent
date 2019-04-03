package c_025;

import java.util.concurrent.*;

/**
 * DelayQueue,
 * 出队有个时间限制, 每个元素有一个等待时间, 可以按照等待时间排序元素
 * DelayQueue元素必须为 Delayed类型的,即必须设置元素的等待时间
 * 
 * 用途，定时执行任务
 */
public class T07_DelayQueue {

    public static void main(String[] args) throws InterruptedException {
        long timestamp = System.currentTimeMillis();
        //System.out.println(timestamp);
        MyTask myTask1 = new MyTask("1",timestamp + 1000); // 1s后执行
        MyTask myTask2 = new MyTask("2",timestamp + 2000);
        MyTask myTask3 = new MyTask("3",timestamp + 1500);
        MyTask myTask4 = new MyTask("4",timestamp + 2500);
        MyTask myTask5 = new MyTask("5",timestamp + 500);

        DelayQueue<MyTask> tasks = new DelayQueue<>();
        tasks.put(myTask1);
        tasks.put(myTask2);
        tasks.put(myTask3);
        tasks.put(myTask4);
        tasks.put(myTask5);

        System.out.println(tasks);  // 确实按照我们拍的顺序执行的
        //System.out.println(tasks.size());
        for (int i = 0; i < 5; i++) {
            System.out.println(tasks.take().name);
        }
    }

    static class MyTask implements Delayed {
        private String name;
        private long runningTime;

        public MyTask(String name,long runTime) {
            this.name  = name;
            this.runningTime = runTime;
        }
        
        // 这是每个元素的等待时间, 越是后加入的元素,时间等待的越长
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        // 这是排序规律, 执行等待时间最短的排在上面 //
        @Override
        public int compareTo(Delayed o) {
            if(this.getDelay(TimeUnit.MILLISECONDS)>o.getDelay(TimeUnit.MILLISECONDS)){
                return 1;
            }else if(this.getDelay(TimeUnit.MILLISECONDS)<o.getDelay(TimeUnit.MILLISECONDS)){
                return -1;
            }else
                return 0;

        }
        
        @Override
        public String toString() {
            return runningTime + "";
        }
    }

}
