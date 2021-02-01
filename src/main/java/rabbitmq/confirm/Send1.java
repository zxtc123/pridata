package rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 普通模式
 */
public class Send1 {
    private static final String QUEUE_NAME = "test_queue_confirm1";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String msg = "hello confirm message";

        //将channel设置成confirm模式
        //注意：当channel之前设置为事务模式，不能在设置成confirm
        channel.confirmSelect();
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());

        if (!channel.waitForConfirms()) {
            System.out.println("send message failed");
        } else {
            System.out.println("send message ok");
        }

        channel.close();
        connection.close();
    }
}
