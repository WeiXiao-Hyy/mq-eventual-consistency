package com.alipay.orderapi.controller;

import com.alipay.accountfeign.feign.AccountFeign;
import com.alipay.cloudcommon.anno.ResponseResult;
import com.alipay.cloudcommon.dto.PayRecordDTO;
import com.alipay.orderservice.service.PayRecordService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyy
 * @Description
 * @create 2024-02-15 16:25
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private PayRecordService payRecordService;

    @PostMapping("/create")
    @ResponseResult
    public void create(@RequestBody PayRecordDTO payRecordDTO) throws MQClientException {
        log.info("接受到订单数据: {}", payRecordDTO);
        payRecordService.createPayRecord(payRecordDTO);
    }
}