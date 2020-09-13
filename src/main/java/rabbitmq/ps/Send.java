package rabbitmq.ps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 分发模式
 */
public class Send {

    private static final String EXCHANGE_NAME="test_exchange_fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        //发送消息
        String msg ="hello ps!";
        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());
        System.out.println("send msg:"+msg);

        channel.close();
        connection.close();
    }
}
