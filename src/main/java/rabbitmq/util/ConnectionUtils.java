package rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtils {

    /**
     * 获取MQ的连接
     *
     * @return
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("127.0.0.1");
        //AMQP 5672
        factory.setPort(5672);
        //vhost
        //用户名/密码
        factory.setUsername("guest");
        factory.setPassword("guest");
        return factory.newConnection();
    }
}
