package com.alipay.accountservice.assemble;

import com.alipay.accountservice.domain.Account;
import com.alipay.cloudcommon.dto.AccountDTO;

/**
 * @author hyy
 * @Description
 * @create 2024-02-14 20:12
 */
public class AccountAssemble {

    public static AccountDTO assemble(Account account) {
        return AccountDTO.builder()
                .accountCode(account.getAccountCode())
                .accountName(account.getAccountName())
                .amount(account.getAmount())
                .build();
    }
}