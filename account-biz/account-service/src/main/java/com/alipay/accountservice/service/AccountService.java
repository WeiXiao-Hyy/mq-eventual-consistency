package com.alipay.accountservice.service;

import com.alipay.accountservice.domain.Account;
import com.alipay.cloudcommon.dto.AccountDTO;
import com.alipay.cloudcommon.dto.PayRecordDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author hyy
 * @Description
 * @create 2024-02-14 09:16
 */
public interface AccountService extends IService<Account> {

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


    /**
     * 删除账号
     *
     * @param accountCode
     * @return
     */
    String deleteAccount(String accountCode);


    /**
     * 根据account获取账号
     *
     * @param accountCode
     * @return
     */
    AccountDTO selectByCode(String accountCode);


    /**
     * 增加用户账户余额
     *
     * @param payRecordDTO {@link PayRecordDTO}
     */
    void increaseAmount(PayRecordDTO payRecordDTO);
}