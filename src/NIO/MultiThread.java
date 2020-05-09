package NIO;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Description 通过JMX来看一个普通的java程序有哪些线程
 * @Tips
 * @Author yikai.wang
 * @Date 2020/4/21 10:56
 */
public class MultiThread {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        }).start();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        //遍历线程
        for (ThreadInfo info : threadInfos){
            System.out.println(info.getThreadId() + "===" + info.getThreadName());
        }
    }
}
