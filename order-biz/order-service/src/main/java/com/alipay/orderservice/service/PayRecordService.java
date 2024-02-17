package com.alipay.orderservice.service;

import com.alipay.cloudcommon.dto.PayRecordDTO;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * File Description.
 *
 * @author arron
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


    /**
     * 更新订单
     *
     * @param payRecordDTO
     * @return
     */
    String updatePayRecord(PayRecordDTO payRecordDTO);


    /**
     * 删除订单
     *
     * @param orderNo
     * @return
     */
    void deletePayRecord(String orderNo);


    /**
     * 根据订单No查找
     *
     * @param orderNo
     * @return
     */
    PayRecordDTO selectByOrderNo(String orderNo);
}
