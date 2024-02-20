package com.alipay.orderservice.service;

import com.alipay.cloudcommon.dto.PayRecordDTO;
import com.alipay.orderservice.domain.PayRecord;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * PayRecordService.
 *
 * @author hyy
 * @date crated at 2024/2/15 16:08
 * @see com.alipay.orderservice.service
 */
public interface PayRecordService {

    /**
     * 新增订单
     *
     * @param payRecordDTO
     * @return
     */
    void createPayRecord(PayRecordDTO payRecordDTO, String transactionId);


    /**
     * 前端调用，只用向RocketMQ发送事务消息
     *
     * @param payRecordDTO
     */
    void createPayRecord(PayRecordDTO payRecordDTO) throws MQClientException;
}
