package NIO;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 *DatagramChannel UDP
 */
public class TestNonBlockingNIO2 {

    /**
     * 接收
     */
    @Test
    public void receive() throws IOException {
        DatagramChannel dChannel = DatagramChannel.open();
        dChannel.configureBlocking(false);
        dChannel.bind(new InetSocketAddress(9898));

        Selector selector = Selector.open();
        dChannel.register(selector, SelectionKey.OP_READ);
        while (selector.select() > 0){
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            SelectionKey selectionKey = iterator.next();

            if(selectionKey.isReadable()){
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                dChannel.receive(byteBuffer);
                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array(), 0 , byteBuffer.limit()));
                byteBuffer.clear();
            }

            iterator.remove();
        }
    }

    /**
     * 发送
     * @throws IOException
     */
    @Test
    public void send() throws IOException {
        DatagramChannel dChannel = DatagramChannel.open();
        dChannel.configureBlocking(false);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("我爱北京天安门!1".getBytes());
        byteBuffer.flip();
        dChannel.send(byteBuffer, new InetSocketAddress("127.0.0.1", 9898));
        byteBuffer.clear();
        dChannel.close();
    }
}
