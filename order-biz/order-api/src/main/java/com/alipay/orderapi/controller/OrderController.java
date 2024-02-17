package com.alipay.orderapi.controller;

import com.alipay.accountfeign.feign.AccountFeign;
import com.alipay.cloudcommon.anno.ResponseResult;
import com.alipay.cloudcommon.dto.AccountDTO;
import com.alipay.cloudcommon.dto.PayRecordDTO;
import com.alipay.cloudcommon.res.ResultResponse;
import com.alipay.orderservice.service.PayRecordService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Resource
    private AccountFeign accountFeign;

    @PostMapping("/create")
    @ResponseResult
    public void create(@RequestBody PayRecordDTO payRecordDTO) throws MQClientException {
        log.info("接受到订单数据: {}", payRecordDTO);
        payRecordService.createPayRecord(payRecordDTO);
    }

    @PostMapping("/update")
    @ResponseResult
    public String update(@RequestBody PayRecordDTO payRecordDTO) {
        log.info("update order: {}", payRecordDTO);
        return payRecordService.updatePayRecord(payRecordDTO);
    }

    @DeleteMapping("/delete")
    @ResponseResult
    public String delete(@RequestParam String orderNo) {
        log.info("delete order orderNo is: {}", orderNo);
        payRecordService.deletePayRecord(orderNo);
        return "delete success";
    }

    @GetMapping("/getByOrderNo/{orderNo}")
    @ResponseResult
    public PayRecordDTO getByOrderNo(@PathVariable(value = "orderNo") String orderNo) {
        log.info("get order by orderNo: {}", orderNo);
        return payRecordService.selectByOrderNo(orderNo);
    }

    @GetMapping("/getByAccountCode/{accountCode}")
    @ResponseResult
    public ResultResponse<AccountDTO> selectByAccountCode(@PathVariable(value = "accountCode") String accountCode) {
        log.info("select account by accountCode: {}", accountCode);
        return accountFeign.selectByCode(accountCode);
    }
}