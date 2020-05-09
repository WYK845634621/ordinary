package distributedlock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/4/13 10:36
 */
public class ZkTest implements Runnable {

    private static int inventory = 1;
    private static final int NUM = 10;
    private static CountDownLatch cdl = new CountDownLatch(NUM);

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        for (int i = 0; i < NUM; i++) {
            new Thread(new ZkTest()).start();
            cdl.countDown();
        }
    }

    @Override
    public void run() {
        lock.lock();
        try {
            cdl.await();
            if (inventory > 0){
                TimeUnit.SECONDS.sleep(5);
                inventory--;
            }
            System.out.println("库存: " + inventory);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
