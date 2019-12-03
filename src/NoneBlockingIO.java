import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @Description new IO 案例
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/3 10:14
 */

/*
buffer 负责存取数据  缓冲区底层就是数组 用于存储不同数据类型的数据  除了boolean类型的
ByteBuffer  CharBuffer LongBuffer  ...


一. 最常用的还是ButeBuffer  通过allocate()方法获取对象
二. 存取数据的两个核心方法
    put()
    get()
三. 均继承于Buffer
    capacity: 容量, 表示缓冲区最大存储数据的容量,一旦声明不能修改
    limit: 界限, 表示缓冲区中可以操作数据的大小. 即limit后面的数据不能读写
    position: 位置, 表示缓冲区中正在操作数据的位置

    position <= limit <= capacity

五. 直接缓冲区与非直接缓冲区
    非直接缓冲区: 通过allocate()方法分配缓冲区,将缓冲区建立在JVM的内存中
    直接缓冲区:  通过allocateDirect()方法分配直接缓冲区,将缓冲区建立在物理内存中


 */
public class NoneBlockingIO {

    @Test
    public void test1(){
        //1. 分配一个指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);


        String text = "thisa";

        //2. 存放数据
        buffer.put(text.getBytes());    //放完后 position:5   limit:1024   capacity:1024

        //3. 开启读取数据模式
        buffer.flip();      //position:0   limit:5   capacity:1024

        //4. 读取数据   get方法获取缓冲区中的数据
        byte[] bytes = new byte[3];
        buffer.get(bytes);      //get完之后的  position:3   limit:5   capacity:1024
        System.out.println(new String(bytes,0,bytes.length));


        //5. 可重复读
        buffer.rewind();        //position:0   limit:5   capacity:1024


        //6. 清空缓冲区  但是缓冲区中的数据依然存在 只是处于被遗忘状态
        buffer.clear();         //position:0   limit:1024   capacity:1024
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        System.out.println((char)buffer.get(2));

    }

    @Test
    public void testMark(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("thisisatest".getBytes());
        buffer.flip();
        byte[] temp = new byte[buffer.limit()];
        buffer.get(temp,0,1);
        System.out.println(new String(temp,0,temp.length));
        buffer.mark();

        buffer.get(temp,1,3);
        System.out.println(new String(temp,0,temp.length));

    }


    @Test
    public void test2(){
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("我只是想撩妹呀".getBytes());
        buffer.flip();
        byte[] temp = new byte[buffer.limit()];
        buffer.get(temp);
        System.out.println(new String(temp,0,temp.length));
    }
}
