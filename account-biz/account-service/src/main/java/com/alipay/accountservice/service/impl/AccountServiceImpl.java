package com.alipay.accountservice.service.impl;

import com.alipay.accountservice.domain.Account;
import com.alipay.accountservice.dto.AccountDTO;
import com.alipay.accountservice.mapper.AccountMapper;
import com.alipay.accountservice.service.AccountService;
import com.alipay.cloudcommon.err.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hyy
 * @Description
 * @create 2024-02-14 10:02
 */
@Service
@Slf4j
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
        if (ans <= 0) {
            log.error("insert account failed");
            throw new BizException("账户插入失败");
        }
        return "insert success";
    }
}