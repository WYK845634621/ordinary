package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/3 15:02
 */
public class CommonUtil {
    public static void release(FileInputStream inputStream, FileOutputStream outputStream,
                               FileChannel inchannel,FileChannel outchannel){
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



    public static void close(SocketChannel socketChannel, ServerSocketChannel serverSocketChannel){
        if (socketChannel != null){
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (serverSocketChannel != null){
            try {
                serverSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
