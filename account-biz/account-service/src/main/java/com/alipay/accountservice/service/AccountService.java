package com.alipay.accountservice.service;

import com.alipay.accountservice.domain.Account;
import com.alipay.accountservice.dto.AccountDTO;
import com.alipay.accountservice.mapper.AccountMapper;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author hyy
 * @Description
 * @create 2024-02-14 09:16
 */
public interface AccountService {
    /**
     * 插入accountDTO
     *
     * @param accountDTO
     * @return
     */
    String insertAccount(AccountDTO accountDTO);
}