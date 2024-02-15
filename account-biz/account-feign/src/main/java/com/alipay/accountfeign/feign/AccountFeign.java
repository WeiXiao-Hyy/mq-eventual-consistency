package com.alipay.accountfeign.feign;

import com.alipay.accountservice.dto.AccountDTO;
import com.alipay.cloudcommon.anno.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author hyy
 * @Description
 * @create 2024-02-15 10:19
 */
@Service
@FeignClient(name = "account-service")
public interface AccountFeign {

    @PostMapping("/account/create")
    @ResponseResult
    String insert(@RequestBody AccountDTO accountDTO);
}