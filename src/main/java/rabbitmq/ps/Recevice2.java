package rabbitmq.ps;

import com.rabbitmq.client.*;
import rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recevice2 {

    private static final String QUEUE_NAME="test_queue_fanout_sms";
    private static final String EXCHANGE_NAME="test_exchange_fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定队列到交换器
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        int prefetchCount=1;
        channel.basicQos(prefetchCount);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            //获取到达的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("[2] recevice msg:"+msg);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("[2] recevice done");
                    channel.basicAck(envelope.getDeliveryTag(),false);//处理完消息后，手动应当消息队列
                }
            }
        };
        //关闭自动应答，改为手动
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME,autoAck,defaultConsumer);
    }
}
