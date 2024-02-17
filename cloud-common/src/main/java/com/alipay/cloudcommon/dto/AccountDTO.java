package com.alipay.cloudcommon.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hyy
 * @Description
 * @create 2024-02-14 09:22
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDTO {
    /**
     * 用户编码
     */
    private String accountCode;

    /**
     * 用户名称
     */
    private String accountName;

    /**
     * 账户余额
     */
    private BigDecimal amount;
}