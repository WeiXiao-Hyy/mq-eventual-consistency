package com.alipay.accountfeign.feign;

import com.alipay.accountservice.dto.AccountDTO;
import com.alipay.cloudcommon.res.ResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author hyy
 * @Description
 * @create 2024-02-15 10:19
 */
@FeignClient(value = "account-api", path = "/account")
public interface AccountFeign {

    @GetMapping("/getByCode/{accountCode}")
    ResultResponse<AccountDTO> selectByCode(@PathVariable(value = "accountCode") String accountCode);
}