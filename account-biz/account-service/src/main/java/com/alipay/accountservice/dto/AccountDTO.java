package com.alipay.accountservice.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hyy
 * @Description
 * @create 2024-02-14 09:22
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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