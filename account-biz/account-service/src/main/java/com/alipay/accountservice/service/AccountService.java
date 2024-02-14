package com.alipay.accountservice.service;

import com.alipay.accountservice.dto.AccountDTO;

/**
 * @author hyy
 * @Description
 * @create 2024-02-14 09:16
 */
public interface AccountService {

    /**
     * 新增账户
     *
     * @param accountDTO
     * @return
     */
    String insertAccount(AccountDTO accountDTO);

    /**
     * 更新账户
     *
     * @param accountDTO
     * @return
     */
    String updateAccount(AccountDTO accountDTO);
}