package com.alipay.accountapi.controller;

import com.alipay.accountservice.service.AccountService;
import com.alipay.cloudcommon.anno.ResponseResult;
import com.alipay.cloudcommon.dto.PayRecordDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/amount/increase")
    @ResponseResult
    public void increaseAmount(@RequestBody PayRecordDTO payRecordDTO) {
        log.info("increase amount params payRecordDTO : {}", payRecordDTO);
        accountService.increaseAmount(payRecordDTO);
    }
}