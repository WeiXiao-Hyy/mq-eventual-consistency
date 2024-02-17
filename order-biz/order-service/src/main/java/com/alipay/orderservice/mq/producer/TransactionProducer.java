package com.alipay.orderservice.mq.producer;

import com.alipay.orderservice.mq.listener.OrderTransactionListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2024-02-16 22:12
 */
@Component
public class TransactionProducer {

    private String producerGroup = "order_trans_group";

    private TransactionMQProducer producer;

    // 用于执行本地事务和事务状态回查的监听器
    @Autowired
    OrderTransactionListener orderTransactionListener;

    // 执行任务的线程池
    ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50));

    @PostConstruct
    public void init() {
        producer = new TransactionMQProducer(producerGroup);
        producer.setNamesrvAddr("172.16.7.1:9876");
        producer.setSendMsgTimeout(Integer.MAX_VALUE);
        producer.setExecutorService(executor);
        producer.setTransactionListener(orderTransactionListener);
        this.start();
    }

    private void start() {
        try {
            this.producer.start();
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }
    }

    // 事务消息发送
    public TransactionSendResult send(String data, String topic) throws MQClientException {
        Message message = new Message(topic, data.getBytes());
        message.setKeys("pay_record");
        return this.producer.sendMessageInTransaction(message, null);
    }
}