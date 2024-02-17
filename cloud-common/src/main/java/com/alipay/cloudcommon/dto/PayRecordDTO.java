package com.alipay.cloudcommon.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hyy
 * @Description
 * @create 2024-02-15 16:08
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayRecordDTO {
    private Long id;

    private String orderNo;

    private String accountCode;

    private String productCode;

    private Integer count;

    private BigDecimal amount;
}