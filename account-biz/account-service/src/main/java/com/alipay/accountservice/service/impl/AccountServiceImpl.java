package com.alipay.accountservice.service.impl;

import com.alipay.accountservice.assemble.AccountAssemble;
import com.alipay.accountservice.domain.Account;
import com.alipay.accountservice.domain.AccountExample;
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
            throw new BizException("insert account failed");
        }
        return "insert account success";
    }

    @Override
    public String updateAccount(AccountDTO accountDTO) {
        Account account = Account.builder()
                .accountCode(accountDTO.getAccountCode())
                .accountName(accountDTO.getAccountName())
                .amount(accountDTO.getAmount())
                .build();

        int ans = accountMapper.updateByPrimaryKeySelective(account);
        if (ans <= 0) {
            log.error("update account failed");
            throw new BizException("update account failed");
        }
        return "update account success";
    }

    @Override
    public String deleteAccount(String accountCode) {
        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();

        criteria.andAccountCodeEqualTo(accountCode);

        int ans = accountMapper.deleteByExample(example);

        if (ans <= 0) {
            log.error("delete account failed");
            throw new BizException("delete account failed");
        }
        return "delete account success";
    }

    @Override
    public AccountDTO selectByCode(String accountCode) {
        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();

        criteria.andAccountCodeEqualTo(accountCode);

        Account account = (Account) accountMapper.selectByExample(example);

        return AccountAssemble.assemble(account);
    }


}