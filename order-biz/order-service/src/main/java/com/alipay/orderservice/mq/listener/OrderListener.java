package com.alipay.orderservice.mq.listener;

import com.alibaba.fastjson.JSONObject;
import com.alipay.accountfeign.feign.AccountFeign;
import com.alipay.cloudcommon.dto.PayRecordDTO;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2024-02-17 10:00
 */
@Component
@Slf4j
public class OrderListener implements MessageListenerConcurrently {

    @Autowired
    private AccountFeign accountFeign;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        log.info("Consume-thread start to consume message.");
        for (MessageExt message : msgs) {
            if (!process(message)) {
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    private boolean process(MessageExt message) {
        try {
            PayRecordDTO payRecordDTO = JSONObject.parseObject(message.getBody(), PayRecordDTO.class);

            log.info("Start processing order data and prepare to increase the balance. The MQ consumer gets the message:{}", payRecordDTO);
//            int k = 1 / 0;
            accountFeign.increaseAmount(payRecordDTO);
        } catch (Exception e) {
            if (message.getReconsumeTimes() >= 3) {
                log.error("The maximum number of message retries has been reached. " +
                        "Business personnel will be notified to troubleshoot the problem: {}", message.getMsgId());
                return true;
            }
            return false;
        }
        return true;
    }
}