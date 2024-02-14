package com.alipay.accountservice.service;

import com.alipay.accountservice.dto.AccountDTO;

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