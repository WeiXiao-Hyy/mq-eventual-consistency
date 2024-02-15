package com.alipay.orderservice.assemble;

import com.alipay.orderservice.domain.Order;
import com.alipay.orderservice.dto.OrderDTO;

/**
 * @author hyy
 * @Description
 * @create 2024-02-15 16:21
 */
public class OrderAssemble {
    public static OrderDTO assemble(Order order) {
        return OrderDTO.builder()
                .orderNo(order.getOrderNo())
                .accountCode(order.getAccountCode())
                .productCode(order.getProductCode())
                .count(order.getCount())
                .amount(order.getAmount())
                .build();
    }
}