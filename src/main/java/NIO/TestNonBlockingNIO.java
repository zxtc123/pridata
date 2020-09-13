package NIO;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;

public class TestNonBlockingNIO {

    /**
     * 服务端
     * @throws IOException
     */
    @Test
    public void server() throws IOException{
        //1获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        //2切换非阻塞模式
        ssChannel.configureBlocking(false);

        //3绑定连接
        ssChannel.bind(new InetSocketAddress(9898));

        //4.获取选择器 用选择器去监听通道
        Selector selector = Selector.open();

        //5.将通道注册到选择器上 并选择“监听接收事件”
        //ServerSocketChannel主要是用做接收连接
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        //6.轮询式的获取选择器上已经 准备就绪的事件
        while (selector.select() > 0){
            //7.获取当前选择器中所有注册的 选择键（已就绪的事件）
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while(iterator.hasNext()){
                //8.获取准备就绪的事件
                SelectionKey selectionKey = iterator.next();

                //9.判断具体是什么事件就绪
                if(selectionKey.isAcceptable()){//10.如果是接收就绪，获取客户端连接通道
                    SocketChannel socketChannel = ssChannel.accept();
                    //11.切换非阻塞模式
                    socketChannel.configureBlocking(false);
                    //12.将该通道注册到选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }

                if(selectionKey.isReadable()){
                    //13.获取选择器上 读就绪状态的通道
                    SocketChannel channel = (SocketChannel)selectionKey.channel();

                    //14.读取数据
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int len;
                    while ((len = channel.read(byteBuffer)) > 0){
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, len));
                        byteBuffer.clear();
                    }
                }

                //15.取消选择键selectionKey 用完要移除
                iterator.remove();
            }
        }
    }

    /**
     * 客户端
     */
    @Test
    public void client() throws IOException {
        //1获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        //2切换非阻塞模式
        socketChannel.configureBlocking(false);

        //3分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //4发送数据给服务端
//        Scanner scanner = new Scanner(System.in);
//        while(scanner.hasNext()){
//            String str = scanner.next();
//            byteBuffer.put((new Date().toString() + str).getBytes());
//            byteBuffer.flip();
//            socketChannel.write(byteBuffer);
//            byteBuffer.clear();
//        }

        byteBuffer.put(new Date().toString().getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        byteBuffer.clear();

        socketChannel.close();
    }
}
