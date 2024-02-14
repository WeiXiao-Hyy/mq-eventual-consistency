package com.alipay.accountservice.service.impl;

import com.alipay.accountservice.domain.Account;
import com.alipay.accountservice.dto.AccountDTO;
import com.alipay.accountservice.mapper.AccountMapper;
import com.alipay.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hyy
 * @Description
 * @create 2024-02-14 10:02
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public String insertAccount(AccountDTO accountDTO) {
        Account account = Account.builder()
                .accountName(accountDTO.getAccountName())
                .accountCode(accountDTO.getAccountCode())
                .amount(accountDTO.getAmount())
                .build();

        int ans = accountMapper.insertSelective(account);
        if (ans > 0) {
            return "insert success";
        }
        return "insert failed";
    }
}