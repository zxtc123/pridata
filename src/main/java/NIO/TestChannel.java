package NIO;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一、通道：用于源节点与目标节点的连接。在JAVA NIO负责缓冲区中数据的传输
 * Channel本身不储存数据，需配合缓冲区进行传输
 * <p>
 * 二、通道主要实现类
 * FileChannel
 * SocketChannel
 * ServerSocketChannel
 * DatagramChannel
 * <p>
 * 三、获取通道
 * 1.Java 针对通道提供了getChannel()方法
 * 本地IO
 * FileInputStream/FileOutputStream
 * RandomAccessFile
 * <p>
 * 网络IO
 * Socket
 * ServerSocket
 * DatagramSocket
 * 2.JDK1.7 针对通道提供了静态方法open()
 * 3.Files工具栏的 newByteChannel()
 * <p>
 * 四、通道之间数据传输
 * transferFrom()
 * transferTo()
 * <p>
 * 五、分散（Scatter）与聚集（Gather）
 * 分散读取（Scattering Reads）:将通道中数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）:将多个缓冲区中的数据聚集到通道中
 * <p>
 * 六、字符集 Charset
 * 编码  字符--》字节数组
 * 解码  字节数组--》字符
 */
public class TestChannel {

    /**
     * 字符集
     */
    @Test
    public void test5() throws CharacterCodingException {
        Charset cs1 = Charset.forName("GBK");
        //获取编码器
        CharsetEncoder encoder = cs1.newEncoder();
        //获取解码器
        CharsetDecoder decoder = cs1.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("我爱中国！");
        cBuf.flip();

        //编码
        ByteBuffer encode = encoder.encode(cBuf);
        for (int i = 0; i < 10; i++) {
            System.out.println(encode.get());
        }

        //解码
        encode.flip();
        CharBuffer decode = decoder.decode(encode);
        System.out.println(decode.toString());
    }

    /**
     * 分散（Scatter）与聚集（Gather）
     */
    @Test
    public void test4() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("1.txt", "rw");
        //1获取通道
        FileChannel channel1 = raf1.getChannel();
        //2分配缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);
        //3分散读取
        ByteBuffer[] bufs = {buf1, buf2};
        channel1.read(bufs);

        for (ByteBuffer byteBuffer : bufs) {
            byteBuffer.flip();
        }
        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("------------------------------------------");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        //4聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();
        channel2.write(bufs);

        channel1.close();
        channel2.close();
    }

    /**
     * 通道之间数据传输(直接缓冲区)
     */
    @Test
    public void test3() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("4.jpg"),
                StandardOpenOption.WRITE,
                StandardOpenOption.READ,
                StandardOpenOption.CREATE);

        inChannel.transferTo(0, inChannel.size(), outChannel);
        //outChannel.transferFrom(inChannel, 0, inChannel.size());

        inChannel.close();
        outChannel.close();
    }

    /**
     * 使用直接缓冲区完成文件的复制（内存映射文件）
     */
    @Test
    public void test2() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"),
                StandardOpenOption.WRITE,
                StandardOpenOption.READ,
                StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());//只有读写模式，因而通道也要加上读

        //直接对缓冲区进行数据的读写操作
        byte[] dts = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dts);
        outMappedBuf.put(dts);

        inChannel.close();
        outChannel.close();
    }

    /**
     * 对之前流的支持，利用通道完成文件复制（非直接缓冲区）
     *
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        FileInputStream fis = new FileInputStream("1.jpg");
        FileOutputStream fos = new FileOutputStream("2.jpg");
        //1获取通道
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();
        //2分配指定大小的缓存
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //3将通道中数据存入缓存区
        while (inChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            //4将缓存中数据写入通道
            outChannel.write(byteBuffer);
            byteBuffer.clear();//清理缓存
        }

        inChannel.close();
        outChannel.close();
        fis.close();
        fos.close();
    }


}
