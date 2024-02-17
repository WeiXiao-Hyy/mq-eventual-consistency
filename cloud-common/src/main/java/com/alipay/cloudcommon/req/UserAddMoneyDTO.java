package com.alipay.cloudcommon.req;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hyy
 * @Description
 * @create 2024-02-16 20:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddMoneyDTO {

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 金额
     */
    private BigDecimal amount;
}