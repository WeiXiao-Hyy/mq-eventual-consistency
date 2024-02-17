package com.alipay.orderservice.assemble;

import com.alipay.cloudcommon.dto.PayRecordDTO;
import com.alipay.orderservice.domain.PayRecord;

/**
 * @author hyy
 * @Description
 * @create 2024-02-15 16:21
 */
public class PayRecordAssemble {
    public static PayRecordDTO assemble(PayRecord payRecord) {
        return PayRecordDTO.builder()
                .orderNo(payRecord.getOrderNo())
                .accountCode(payRecord.getAccountCode())
                .productCode(payRecord.getProductCode())
                .count(payRecord.getCount())
                .amount(payRecord.getAmount())
                .build();
    }
}