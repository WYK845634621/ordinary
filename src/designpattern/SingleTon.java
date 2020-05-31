package designpattern;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/31 16:27
 */
public class SingleTon {
    private static volatile SingleTon singleTon;

    private SingleTon(){
        System.out.println(Thread.currentThread().getName() + "构造");
    }

    public static SingleTon getInstance(){
        if (singleTon == null){
            synchronized (SingleTon.class){
                if (singleTon == null){

                    singleTon = new SingleTon();
                }
            }
        }
        return singleTon;
    }


    public static void main(String[] args) {
        for (int i = 1; i <= 300; i++) {
            new Thread(() -> {
                SingleTon.getInstance();
            },i+"").start();
        }
    }

}
