package code.base;
import java.util.Date;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * rocketmq 发送消息
 * @author liuwen
 * @version 1.0
 * @date 2018年6月5日
 */
public class ProducterDemo{

    public static void main(String[] args) throws MQClientException, InterruptedException {  
        DefaultMQProducer producer = new DefaultMQProducer("rmq-group");  
        producer.setNamesrvAddr("127.0.0.1:9876");  
        producer.setInstanceName("rmq-instance");  
        producer.setVipChannelEnabled(false);// // 必须设为false否则连接broker10909端口  
        producer.start();  
        System.out.println("开始发送数据");  
        try {  
            for (int i = 0; i < 3; i++) {  
                Message msg = new Message("test1",// topic  
                        "TagA",// tag  
                        (new Date() + "这里是一条消息" + i).getBytes()// body  
                );  
                SendResult sendResult = producer.send(msg);  
                System.out.println("发送成功");  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        producer.shutdown();  
    }  
}
