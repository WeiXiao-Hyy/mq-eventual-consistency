package com.alipay.orderservice.mq.listener;

import com.alibaba.fastjson.JSONObject;
import com.alipay.cloudcommon.dto.PayRecordDTO;
import com.alipay.orderservice.domain.TransactionLog;
import com.alipay.orderservice.service.PayRecordService;
import com.alipay.orderservice.service.TransactionLogService;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hyy
 * @Description
 * @create 2024-02-16 22:14
 */
@Component
@Slf4j
public class OrderTransactionListener implements TransactionListener {

    @Autowired
    private PayRecordService payRecordService;

    @Autowired
    TransactionLogService transactionLogService;

    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        log.info("Start executing local transaction...");
        LocalTransactionState state;

        try {
            String body = new String(msg.getBody());
            PayRecordDTO payRecordDTO = JSONObject.parseObject(body, PayRecordDTO.class);
            log.debug("Get the message in the message queue: {}", payRecordDTO);
            payRecordService.createPayRecord(payRecordDTO, msg.getTransactionId());

            state = LocalTransactionState.COMMIT_MESSAGE;
            log.info("Local transaction submitted: {}", msg.getTransactionId());

        } catch (Exception e) {

            state = LocalTransactionState.ROLLBACK_MESSAGE;
            log.info("Failed to execute local transaction: {}", e.getMessage());
        }

        return state;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {

        String transactionId = messageExt.getTransactionId();
        log.info("Start checking local transaction status: transactionId = {}", transactionId);

        LocalTransactionState state;
        TransactionLog transactionLog = transactionLogService.getById(transactionId);

        if (Objects.nonNull(transactionLog)) {
            state = LocalTransactionState.COMMIT_MESSAGE;
        } else {
            state = LocalTransactionState.UNKNOW;
        }

        log.info("End local transaction status query: {}", state);
        return state;
    }
}