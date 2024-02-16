package com.alipay.orderservice.service.impl;

import com.alipay.cloudcommon.err.BizException;
import com.alipay.orderservice.assemble.OrderAssemble;
import com.alipay.orderservice.domain.Order;
import com.alipay.orderservice.domain.OrderExample;
import com.alipay.orderservice.dto.OrderDTO;
import com.alipay.orderservice.mapper.OrderMapper;
import com.alipay.orderservice.service.OrderService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author hyy
 * @Description
 * @create 2024-02-15 16:08
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public String insertOrder(OrderDTO orderDTO) {
        Order order = Order.builder()
                .accountCode(orderDTO.getAccountCode())
                .productCode(orderDTO.getProductCode())
                .orderNo(orderDTO.getOrderNo())
                .count(orderDTO.getCount())
                .amount(orderDTO.getAmount())
                .build();

        int ans = orderMapper.insertSelective(order);

        if (ans <= 0) {
            log.error("insert order failed");
            throw new BizException("insert order failed");
        }

        return "insert order success";
    }

    @Override
    public String updateOrder(OrderDTO orderDTO) {
        Order order = Order.builder()
                .accountCode(orderDTO.getAccountCode())
                .productCode(orderDTO.getProductCode())
                .orderNo(orderDTO.getOrderNo())
                .count(orderDTO.getCount())
                .amount(orderDTO.getAmount())
                .build();

        int ans = orderMapper.updateByPrimaryKeySelective(order);

        if (ans <= 0) {
            log.error("update order failed");
            throw new BizException("update order failed");
        }

        return "update order failed";
    }

    @Override
    public String deleteOrder(String orderNo) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();

        criteria.andOrderNoEqualTo(orderNo);

        int ans = orderMapper.deleteByExample(example);

        if (ans <= 0) {
            log.error("delete order failed");
            throw new BizException("delete order failed");
        }

        return "delete order failed";
    }

    @Override
    public OrderDTO selectByOrderNo(String orderNo) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();

        criteria.andOrderNoEqualTo(orderNo);

        List<Order> orderList = orderMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(orderList)) {
            return null;
        }

        return orderList.stream().map(OrderAssemble::assemble).collect(Collectors.toList()).get(0);
    }
}