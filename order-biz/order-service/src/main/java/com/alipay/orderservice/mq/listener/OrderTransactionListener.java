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
        log.info("开始执行本地事务...");
        LocalTransactionState state;

        try {
            String body = new String(msg.getBody());
            PayRecordDTO payRecordDTO = JSONObject.parseObject(body, PayRecordDTO.class);
            log.debug("获取消息队列中message: {}", payRecordDTO);
            payRecordService.createPayRecord(payRecordDTO, msg.getTransactionId());
            state = LocalTransactionState.COMMIT_MESSAGE;
            log.info("本地事务已提交: {}", msg.getTransactionId());
        } catch (Exception e) {
            log.info("执行本地事务失败: {}", e.getMessage());
            state = LocalTransactionState.ROLLBACK_MESSAGE;
        }

        return state;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {

        String transactionId = messageExt.getTransactionId();
        log.info("开始回查本地事务状态: transactionId = {}", transactionId);

        LocalTransactionState state;
        TransactionLog transactionLog = transactionLogService.getById(transactionId);

        if (Objects.nonNull(transactionLog)) {
            state = LocalTransactionState.COMMIT_MESSAGE;
        } else {
            state = LocalTransactionState.UNKNOW;
        }

        log.info("结束本地事务状态查询: {}", state);
        return state;
    }
}