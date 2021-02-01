package NIO;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一、使用NIO完成网络通信的三个核心：
 * 1.通道（Channel）:负责连接
 * java.nio.channels.Channel
 * --SocketChannel
 * --ServerSocketChannel
 * --DatagramChannel
 * <p>
 * 2.缓冲区（Buffer）:负责数据的存取
 * <p>
 * 3.选择器（Selector）:是SelectableChannel的多路复用器。用于监控SelectableChannel的IO状况
 */
public class TestBlockingNIO {

    //客户端
    @Test
    public void client() throws IOException {
        //1获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        FileChannel fileChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);

        //2分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //读取本地文件，发送给服务端
        while (fileChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        socketChannel.shutdownOutput();//服务端无法知道是否发送完，要手动通知

        //接受服务端反馈,socketChannel读到数据，说明服务端有反馈
        int len;
        while ((len = socketChannel.read(byteBuffer)) != -1) {
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array(), 0, len));
            byteBuffer.clear();
        }

        socketChannel.close();
        fileChannel.close();
    }

    //服务端
    @Test
    public void server() throws IOException {
        //1获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        FileChannel outChannel = FileChannel.open(Paths.get("5.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //2绑定连接
        ssChannel.bind(new InetSocketAddress(9898));

        //3获取客户端连接的通道
        SocketChannel socketChannel = ssChannel.accept();

        //4分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //5接受客户端的数据并保存在本地
        while (socketChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        //发送反馈给客户端
        byteBuffer.put("服务端接受数据完毕".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);

        ssChannel.close();
        outChannel.close();
        socketChannel.close();
    }


}
