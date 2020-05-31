package jmm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description voltile关键字的使用
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/31 12:26
 */

class MyData {
    volatile int n = 0;

    public void addTo() {
        this.n = 40;
    }

    public void countPlus() {
        n++;
    }

    AtomicInteger a = new AtomicInteger();

    public void atoPlus(){
        a.getAndIncrement();
    }
}

public class JmmVoltile {



    public static void main(String[] args) {

    }

    /**
     *     volatile并不能保证原子性,因此需要配合Atomic包下的类来进行搭配使用已达到Syncronize的效果
     */
    private static void atomicCheck() {
        MyData d = new MyData();
        for (int i = 0; i < 40; i++) {
            new Thread(){
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        d.countPlus();
                        d.atoPlus();
                    }
                }
            }.start();
        }

        //大于2是因为默认有一个main和一个gc
        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println(d.n);
        System.out.println(d.a);
    }

    /**
     * 验证volatile的可见性
     */
    private static void visiable() {
        MyData d = new MyData();

        new Thread("工作线程") {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "  ==进来了");
                try {
                    TimeUnit.SECONDS.sleep(2);
                    d.addTo();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "  ==完成了");
            }
        }.start();

        new Thread("额外线程") {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "  ==进来了");
                while (d.n == 0) {
                }

                System.out.println(Thread.currentThread().getName() + "  ==" + d.n);
                System.out.println(Thread.currentThread().getName() + "  ==完成了");
            }
        }.start();


        while (d.n == 0) {

        }

        System.out.println(Thread.currentThread().getName() + "  ==" + d.n);
    }
}


