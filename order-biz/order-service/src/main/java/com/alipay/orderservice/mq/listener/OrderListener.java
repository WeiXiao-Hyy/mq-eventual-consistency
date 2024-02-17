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
        log.info("消费者线程监听到消息");
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
            log.info("开始处理订单数据,准备增加余额,MQ消费者获得消息为:{}", payRecordDTO);
            accountFeign.increaseAmount(payRecordDTO);
            return true;
        } catch (Exception e) {
            if (message.getReconsumeTimes() >= 3) {
                log.error("消息重试已达到最大次数，将通知业务人员排查问题:{}", message.getMsgId());
                return true;
            }
            return false;
        }
    }
}