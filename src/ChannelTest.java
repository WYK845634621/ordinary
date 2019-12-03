import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

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
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/3 14:26
 */
public class ChannelTest {

    @Test
    public void test(){
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        FileChannel inchannel = null;
        FileChannel outchannel = null;
        try {
            inputStream = new FileInputStream("C:\\Users\\yikai.wang\\Documents\\身后空无一人.txt");
            outputStream = new FileOutputStream("C:\\Users\\yikai.wang\\Pictures\\copy.txt");

            inchannel = inputStream.getChannel();
            outchannel = outputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (inchannel.read(buffer) != -1){
                buffer.flip();
                outchannel.write(buffer);
                buffer.clear();
            }

        }catch (Exception e){
            if (inchannel != null){
                try {
                    inchannel.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (outchannel != null){
                try {
                    outchannel.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
