package com.alipay.accountservice.service;

import com.alipay.cloudcommon.dto.PayRecordDTO;

/**
 * @author hyy
 * @Description
 * @create 2024-02-14 09:16
 */
public interface AccountService {

    /**
     * 增加用户账户余额
     *
     * @param payRecordDTO {@link PayRecordDTO}
     */
    void increaseAmount(PayRecordDTO payRecordDTO);
}