package NIO;

import org.junit.Test;
import util.CommonUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Description
 * 一. 用于源节点与目标节点之间的连接,在Java NIO中负责缓冲区数据的传输 Channel本身不操作任何数据,而是操作缓冲区
 * 二. 通道的主要实现类
 *      java.nio.channels.Channel接口
 *          FileChannel
 *          SocketChannel
 *          ServerSocketChannel
 *          DatagramChannel
 * 三. 获取通道的方法:
 *      1.java针对支持通道的类提供了getChannel()的方法
 *      2.在jdk1.7中NIO2针对通道提供了静态方法open()
 *      3.在jdk1.7中NIO2的Files工具类中的newByteChannel()
 *
 * 四. 通道之间的数据传输
 *      transferFrom()
 *      transferTo()
 *
 * 五. 分散读取Scatter和聚集写入Gather
 *      分散读取:将通道中的数据分散到多个缓冲区中
 *      聚集写入:
 *
 * 六. Charset解码编码
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/3 14:26
 */
public class ChannelTest {


    /**
     * 利用流及缓冲区实现对通道的操作
     */
    @Test
    public void test(){
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        FileChannel inchannel = null;
        FileChannel outchannel = null;
        try {
            inputStream = new FileInputStream("C:\\Users\\yikai.wang\\Pictures\\cc.txt");
            outputStream = new FileOutputStream("C:\\Users\\yikai.wang\\Pictures\\1209.txt");

            inchannel = inputStream.getChannel();
            outchannel = outputStream.getChannel();

            //这个时候需要一个buffer来暂存数据
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (inchannel.read(buffer) != -1){
                buffer.flip();
                outchannel.write(buffer);
                buffer.clear();
            }
            System.out.println("输出完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CommonUtil.release(inputStream,outputStream,inchannel,outchannel);
        }
    }


    /**
     * 使用直接缓冲区实现----物理内存文件映射
     */
    @Test
    public void test2(){
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        MappedByteBuffer inMapBuffer = null;
        MappedByteBuffer outMapBuffer = null;
        try {
            inChannel = FileChannel.open(Paths.get("C:\\Users\\yikai.wang\\Pictures\\cc.txt"),StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("C:\\Users\\yikai.wang\\Pictures\\人人.txt"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);

            inMapBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            outMapBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, outChannel.size());

            System.out.println(inChannel.size());

            byte[] temp = new byte[1024];
            inMapBuffer.get(temp);
            outMapBuffer.put(temp);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CommonUtil.release(null,null,inChannel,outChannel);
        }
    }


    /**
     * 通道之间的数据传输----直接缓冲区的方式
     */
    @Test
    public void test3(){
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = FileChannel.open(Paths.get("C:\\Users\\yikai.wang\\Pictures\\cc.txt"), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("C:\\Users\\yikai.wang\\Pictures\\ee.txt"),StandardOpenOption.CREATE,StandardOpenOption.WRITE);
//            inChannel.transferTo(0,inChannel.size(),outChannel);
            outChannel.transferFrom(inChannel,0,inChannel.size());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CommonUtil.release(null,null,inChannel,outChannel);
        }
    }

    //分散读取  聚集写入
    @Test
    public void test4(){
        long start = System.currentTimeMillis();
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        //分散读取  聚集写入
        try {
            RandomAccessFile inFile = new RandomAccessFile("C:\\Users\\yikai.wang\\Pictures\\聚集写入.txt","rw");
            inChannel = inFile.getChannel();

            ByteBuffer buffer1 = ByteBuffer.allocate(1024);
            ByteBuffer buffer2 = ByteBuffer.allocate(1024);

            ByteBuffer[] buffers = {buffer1,buffer2};
            inChannel.read(buffers);
            for (ByteBuffer t : buffers){
                t.flip();
            }

            RandomAccessFile outFile = new RandomAccessFile("C:\\Users\\yikai.wang\\Pictures\\写入2.txt","rw");
            outChannel = outFile.getChannel();
            outChannel.write(buffers);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CommonUtil.release(null,null,inChannel,outChannel);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

}
