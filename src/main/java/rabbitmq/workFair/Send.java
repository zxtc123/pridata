package rabbitmq.workFair;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 公平队列
 */
public class Send {
    private static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //获取MQ连接
        Connection connection = ConnectionUtils.getConnection();
        //从连接中获取通道
        Channel channel = connection.createChannel();
        //创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //每个消费者在发送确认消息（反馈消息）之前，消息队列不发送消息给消费者
        //消费者一次处理一个消息
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        for (int i = 0; i < 50; i++) {
            String msg = "hello !" + i;
            System.out.println("[mq] send" + msg);
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            Thread.sleep(50);
        }

        channel.close();
        connection.close();
    }
}
