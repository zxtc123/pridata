package NIO;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 一、缓冲区（buffer）在java NIO中负责数据的存取。缓冲区就是数组，用于存储不同数据类型的数据
 * 根据数据类型不同，提供了相应类型的缓冲区
 * <p>
 * 二、缓冲区存取数据的核心方法：
 * put() 存入数据到缓冲区
 * get() 获取缓冲区数据
 * <p>
 * 三、缓冲区中核心属性
 * Invariants: mark <= position <= limit <= capacity
 * private int mark = -1;    标记，记录当前position位置，可以reset()恢复到mark的位置
 * private int position = 0;   位置，缓冲区中正在操作数据的位置
 * private int limit;    界限，缓冲区可以操作数据的大小
 * private int capacity;   容量，缓冲区最大存储容量，一旦声明不可改变
 * <p>
 * 四、直接缓冲区与非直接缓冲区
 * 非直接缓冲区：allocate()方法分配缓冲区，将缓冲区建在JVM内存中
 * 直接缓冲区：allocateDirect(),将缓冲区建在物理内存中，提高效率
 */
public class TestBuffer {
    @Test
    public void test1() {
        String str = "abcde";

        //1.分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println("------------allocate---------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //2.存放数据
        buf.put(str.getBytes());
        System.out.println("------------put---------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //3.切换读写模式
        buf.flip();
        System.out.println("------------flip---------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //4.读取数据

        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst, 0, dst.length));
        System.out.println("------------get---------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
//        while (buf.hasRemaining()){
//            System.out.println((char)buf.get());
//        }

        //5.可重复读数据
        buf.rewind();
        System.out.println("------------rewind---------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //6.清空数据,但数据其实没有被清除
        buf.clear();
        System.out.println("------------clear---------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        System.out.println((char) buf.get());
    }
}
