package jmm;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2020/5/31 13:09
 */
public class MyD {
    volatile int n = 0;

    public void add(){
        n++;
    }
}
