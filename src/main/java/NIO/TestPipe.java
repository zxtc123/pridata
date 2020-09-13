package NIO;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * 单向管道
 */
public class TestPipe {

    @Test
    public void test() throws IOException {
        //1获取管道
        Pipe pipe = Pipe.open();

        //2将缓冲区中数据写入管道
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        Pipe.SinkChannel sink = pipe.sink();//写入的部分
        byteBuffer.put("写入单向管道".getBytes());
        byteBuffer.flip();
        sink.write(byteBuffer);

        //这2部分可以写入多个线程

        //3读取缓冲区中的数据
        Pipe.SourceChannel source = pipe.source();//读取的部分
        byteBuffer.flip();
        int len = source.read(byteBuffer);
        System.out.println(new String(byteBuffer.array(), 0, len));

        sink.close();
        source.close();
    }
}
