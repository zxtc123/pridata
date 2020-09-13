package rabbitmq.simple;

import com.rabbitmq.client.*;
import rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者消费消息
 */
public class Recevice {

    private static final String QUEUE_NAME="test_simple_queue";


    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            //获取到达的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("new API recevice msg:"+msg);
            }
        };
        channel.basicConsume(QUEUE_NAME,true,defaultConsumer);
    }

    @SuppressWarnings("deprecation")
    private static void oldConsume()throws IOException, TimeoutException, InterruptedException{
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        //定义队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);
        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("recevice msg:"+msg);
        }
    }
}
