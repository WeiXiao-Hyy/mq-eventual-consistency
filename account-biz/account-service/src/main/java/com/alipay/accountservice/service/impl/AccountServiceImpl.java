package com.alipay.accountservice.service.impl;

import com.alipay.accountservice.domain.Account;
import com.alipay.accountservice.mapper.AccountMapper;
import com.alipay.accountservice.service.AccountService;
import com.alipay.cloudcommon.dto.PayRecordDTO;
import com.alipay.cloudcommon.err.BizException;
import java.math.BigDecimal;
import java.util.Objects;
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

    // TODO: 消费端需要接口幂等
    @Override
    public void increaseAmount(PayRecordDTO payRecordDTO) {
        if (Objects.isNull(payRecordDTO)) {
            throw new BizException("Increase Amount payRecordDTO is null");
        }

        String accountCode = payRecordDTO.getAccountCode();
        Account account = accountMapper.selectByAccountCode(accountCode);

        if (Objects.isNull(account)) {
            throw new BizException("Account not exist, accountCode: {}", accountCode);
        }

        // 增加余额
        BigDecimal amount = payRecordDTO.getAmount().add(account.getAmount());
        account.setAmount(amount);

        // 更新操作
        accountMapper.updateByPrimaryKeySelective(account);

        log.info("Increase accountCode: {}, amount: {} success!", accountCode, amount);
    }
}