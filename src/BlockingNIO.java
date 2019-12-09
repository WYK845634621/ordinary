import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Description     模拟网路通信
 * 使用NIO完成网络通信的三个核心:
 * 1. 通道(channel): 负责连接
 * 2. 缓冲区(buffer): 负责数据的存取
 * 3. 选择器(selector): 是selectorableChannel的多路复用器
 *
 * @Tips    非阻塞是相对网络通信:tcp udp而言  fileChannel没有非阻塞
 * @Author yikai.wang
 * @Date 2019/12/4 14:04
 */
public class BlockingNIO {

    @Test
    public void client(){
        SocketChannel socketChannel = null;
        FileChannel inChannel = null;
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9898));
            inChannel = FileChannel.open(Paths.get("C:\\Users\\yikai.wang\\Pictures\\cc.txt"),StandardOpenOption.READ);

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (inChannel.read(buffer) != -1){
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();
            }
            System.out.println("输入完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CommonUtil.close(socketChannel,null);
            CommonUtil.release(null,null,inChannel,null);
        }
    }


    @Test
    public void server(){
        ServerSocketChannel serverSocketChannel = null;
        SocketChannel accept = null;
        FileChannel outChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(9898));
            accept = serverSocketChannel.accept();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            outChannel = FileChannel.open(Paths.get("C:\\Users\\yikai.wang\\Pictures\\server.txt"),StandardOpenOption.WRITE,StandardOpenOption.CREATE);

            while (accept.read(buffer) != -1){
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
            System.out.println("输出完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CommonUtil.close(accept,serverSocketChannel);
            CommonUtil.release(null,null,null,outChannel);
        }
    }
}

