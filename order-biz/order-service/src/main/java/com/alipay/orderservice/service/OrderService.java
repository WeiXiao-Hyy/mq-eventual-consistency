package com.alipay.orderservice.service;

import com.alipay.orderservice.dto.OrderDTO;

/**
 * File Description.
 *
 * @author arron
 * @date crated at 2024/2/15 16:08
 * @see com.alipay.orderservice.service
 */
public interface OrderService {

    /**
     * 新增订单
     *
     * @param orderDTO
     * @return
     */
    String insertOrder(OrderDTO orderDTO);


    /**
     * 更新订单
     *
     * @param orderDTO
     * @return
     */
    String updateOrder(OrderDTO orderDTO);


    /**
     * 删除订单
     *
     * @param orderNo
     * @return
     */
    String deleteOrder(String orderNo);


    /**
     * 根据订单No查找
     *
     * @param orderNo
     * @return
     */
    OrderDTO selectByOrderNo(String orderNo);
}
