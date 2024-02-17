package com.alipay.accountservice.service.impl;

import com.alipay.accountservice.assemble.AccountAssemble;
import com.alipay.accountservice.domain.Account;
import com.alipay.accountservice.domain.AccountExample;
import com.alipay.accountservice.mapper.AccountMapper;
import com.alipay.accountservice.service.AccountService;
import com.alipay.cloudcommon.dto.AccountDTO;
import com.alipay.cloudcommon.dto.PayRecordDTO;
import com.alipay.cloudcommon.err.BizException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author hyy
 * @Description
 * @create 2024-02-14 10:02
 */
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public String insertAccount(AccountDTO accountDTO) {
        Account account = Account.builder().accountName(accountDTO.getAccountName()).accountCode(accountDTO.getAccountCode()).amount(accountDTO.getAmount()).build();

        int ans = accountMapper.insertSelective(account);
        if (ans <= 0) {
            log.error("insert account failed");
            throw new BizException("insert account failed");
        }
        return "insert account success";
    }

    @Override
    public String updateAccount(AccountDTO accountDTO) {
        Account account = Account.builder().accountCode(accountDTO.getAccountCode()).accountName(accountDTO.getAccountName()).amount(accountDTO.getAmount()).build();

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

        List<Account> accountList = accountMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(accountList)) {
            return null;
        }

        return accountList.stream().map(AccountAssemble::assemble).collect(Collectors.toList()).get(0);
    }

    // 需要实现消费幂等
    @Override
    public void increaseAmount(PayRecordDTO payRecordDTO) {

        Account account = accountMapper.selectByAccountCode(payRecordDTO.getAccountCode());

        if (Objects.isNull(account)) {
            log.error("用户不存在");
            return;
        }

        // 增加余额
        account.setAmount(payRecordDTO.getAmount().add(account.getAmount()));

        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();
        criteria.andAccountCodeEqualTo(payRecordDTO.getAccountCode());

        accountMapper.updateByExampleSelective(account, example);
    }


}