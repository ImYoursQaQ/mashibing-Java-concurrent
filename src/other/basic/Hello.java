package other.basic;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 一个普通的hello world会创建几个线程？
 * main ：主程序线程
 * Reference Handler : 处理对象引用本身的垃圾回收
 * Finalizer ： 处理用户的finalizer方法
 * Signal Dispatcher ：外部jvm命令转发器
 * 另外，在run模式下会额外一个线程
 * Monitor Ctrl-Break线程
 */
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello Word!");
         //获取threadMXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] allThreadIds = threadMXBean.getAllThreadIds();
         //根据ThreadId获取线程信息
        ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(allThreadIds);
        for (ThreadInfo threadInfo:threadInfos) {
            //打印线程id和线程名称 
            System.out.println(threadInfo.getThreadId()+":"+threadInfo.getThreadName());
        }
    }
}