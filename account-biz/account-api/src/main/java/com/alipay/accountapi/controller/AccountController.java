package com.alipay.accountapi.controller;

import com.alipay.accountservice.dto.AccountDTO;
import com.alipay.accountservice.service.AccountService;
import com.alipay.cloudcommon.anno.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyy
 * @Description
 * @create 2024-02-14 09:09
 */
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    @ResponseResult
    public String insert(@RequestBody AccountDTO accountDTO) {
        log.info("insert account: {}", accountDTO);
        return accountService.insertAccount(accountDTO);
    }

    @PostMapping("/update")
    @ResponseResult
    public String update(@RequestBody AccountDTO accountDTO) {
        log.info("update account: {}", accountDTO);
        return accountService.updateAccount(accountDTO);
    }

    @DeleteMapping("/delete")
    @ResponseResult
    public String delete(@RequestParam String accountCode) {
        log.info("delete accountCode: {}", accountCode);
        return accountService.deleteAccount(accountCode);
    }

    @GetMapping("/getByCode/{accountCode}")
    @ResponseResult
    public AccountDTO getByCode(@PathVariable(value = "accountCode") String accountCode) {
        log.info("get account by code: {}", accountCode);
        AccountDTO accountDTO = accountService.selectByCode(accountCode);
        log.debug("get account dto: {}", accountDTO.toString());
        return accountDTO;
    }
}