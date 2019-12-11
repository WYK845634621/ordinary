package NIO;

import org.junit.Test;
import util.CommonUtil;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * @Description 非阻塞IO 即 使用选择器的
 * @Tips
 * @Author yikai.wang
 * @Date 2019/12/6 17:17
 */
public class BlockingNIO2 {


    @Test
    public void client(){
        SocketChannel socketChannel = null;
        FileChannel inChannel = null;
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9797));
            socketChannel.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(LocalDateTime.now().toString().getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CommonUtil.release(null,null,inChannel,null);
            CommonUtil.close(socketChannel,null);
        }
    }


    @Test
    public void server(){
        ServerSocketChannel serverSocketChannel = null;
        SocketChannel accept = null;
        FileChannel outChannel = null;
        Selector selector;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(9797));

            //这个时候需要获取选择器
            selector = Selector.open();
            //将通道注册到选择器上面
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            //轮询式的获取选择器上已经准备就绪的事件
            while (selector.select() > 0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    //获取准备就绪的事件
                    SelectionKey sk = iterator.next();
                    //判断具体是什么类型的事件准备就绪
                    if (sk.isAcceptable()){
                        //接收    就绪
                        accept = serverSocketChannel.accept();
                        //设置非阻塞
                        accept.configureBlocking(false);
                        //注册
                        accept.register(selector,SelectionKey.OP_READ);
                    }else if (sk.isReadable()){
                        //读  就绪
                        SocketChannel socketChannel = (SocketChannel) sk.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int len = 0;
                        while ((len = socketChannel.read(buffer)) > 0){
                            buffer.flip();
                            System.out.println(new String(buffer.array(),0,len));
                            buffer.clear();
                        }
                    }
                    //取消选择键 SelectionKey
                    iterator.remove();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CommonUtil.close(accept,serverSocketChannel);
            CommonUtil.release(null,null,null,outChannel);
        }
    }

}
