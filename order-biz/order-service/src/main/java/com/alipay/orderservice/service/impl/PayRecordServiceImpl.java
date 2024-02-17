package com.alipay.orderservice.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.alibaba.fastjson.JSON;
import com.alipay.cloudcommon.dto.PayRecordDTO;
import com.alipay.cloudcommon.err.BizException;
import com.alipay.orderservice.assemble.PayRecordAssemble;
import com.alipay.orderservice.domain.PayRecord;
import com.alipay.orderservice.domain.PayRecordExample;
import com.alipay.orderservice.domain.TransactionLog;
import com.alipay.orderservice.mapper.PayRecordMapper;
import com.alipay.orderservice.mapper.TransactionLogMapper;
import com.alipay.orderservice.mq.producer.TransactionProducer;
import com.alipay.orderservice.service.PayRecordService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @author hyy
 * @Description
 * @create 2024-02-15 16:08
 */
@Service
@Slf4j
public class PayRecordServiceImpl implements PayRecordService {

    @Autowired
    private PayRecordMapper payRecordMapper;

    @Autowired
    private TransactionLogMapper transactionLogMapper;

    @Autowired
    private TransactionProducer transactionProducer;

    private Snowflake snowflake = new Snowflake(1, 1);

    @Transactional
    @Override
    public void createPayRecord(PayRecordDTO payRecordDTO, String transactionId) {

        // 创建订单
        PayRecord payRecord = PayRecord.builder()
                .accountCode(payRecordDTO.getAccountCode())
                .productCode(payRecordDTO.getProductCode())
                .orderNo(payRecordDTO.getOrderNo())
                .count(payRecordDTO.getCount())
                .amount(payRecordDTO.getAmount())
                .build();

        payRecordMapper.insertSelective(payRecord);

        // 写入事务日志
        TransactionLog transactionLog = TransactionLog.builder()
                .id(transactionId)
                .business("order")
                .foreignKey(String.valueOf(payRecord.getId()))
                .build();

        transactionLogMapper.insert(transactionLog);

        log.info("订单创建完成: {}", payRecordDTO);
    }

    @Override
    public void createPayRecord(PayRecordDTO payRecordDTO) throws MQClientException {
        payRecordDTO.setId(snowflake.nextId());
        payRecordDTO.setOrderNo(snowflake.nextIdStr());
        transactionProducer.send(JSON.toJSONString(payRecordDTO), "order");
    }
}