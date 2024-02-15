package com.alipay.orderapi.controller;

import com.alipay.cloudcommon.anno.ResponseResult;
import com.alipay.orderservice.dto.OrderDTO;
import com.alipay.orderservice.service.OrderService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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
    private OrderService orderService;


    @PostMapping("/create")
    @ResponseResult
    public String insert(@RequestBody OrderDTO orderDTO) {
        log.info("insert order: {}", orderDTO);
        return orderService.insertOrder(orderDTO);
    }

    @PostMapping("/update")
    @ResponseResult
    public String update(@RequestBody OrderDTO orderDTO) {
        log.info("update order: {}", orderDTO);
        return orderService.updateOrder(orderDTO);
    }

    @DeleteMapping("/delete")
    @ResponseResult
    public String delete(@RequestParam String orderNo) {
        log.info("delete order by orderNo: {}", orderNo);
        return orderService.deleteOrder(orderNo);
    }

    @GetMapping("/getByOrderNo/{orderNo}")
    public OrderDTO getByOrderNo(@PathVariable(value = "orderNo") String orderNo) {
        log.info("get order by orderNo: {}", orderNo);
        return orderService.selectByOrderNo(orderNo);
    }
}