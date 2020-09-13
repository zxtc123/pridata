package rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import rabbitmq.util.ConnectionUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * 异步模式
 */
public class Send3 {
    private static final String QUEUE_NAME = "test_queue_confirm1";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.confirmSelect();

        //未确认消息的标识
        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());
        //信道添加监听
        channel.addConfirmListener(new ConfirmListener() {
            //回执没问题的
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                if(b){
                    System.out.println("---handleAck---multiple");
                    confirmSet.headSet(l+1).clear();
                }else{
                    System.out.println("---handleAck---multiple false");
                    confirmSet.remove(l);
                }
            }
            //回执有问题的
            @Override
            public void handleNack(long l, boolean b) throws IOException {
                if(b){
                    System.out.println("---handleNack---multiple");
                    confirmSet.headSet(l+1).clear();
                }else{
                    System.out.println("---handleNack---multiple false");
                    confirmSet.remove(l);
                }
            }
        });

        String msg = "sssss";
        while(true){
            long seqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            confirmSet.add(seqNo);
        }

    }

}
