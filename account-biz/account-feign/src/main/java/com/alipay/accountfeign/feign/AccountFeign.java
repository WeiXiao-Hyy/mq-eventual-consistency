package com.alipay.accountfeign.feign;

import com.alipay.cloudcommon.dto.PayRecordDTO;
import com.alipay.cloudcommon.res.ResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author hyy
 * @Description
 * @create 2024-02-15 10:19
 */
@FeignClient(value = "account-api", path = "/account")
public interface AccountFeign {

    @PostMapping("/amount/increase")
    ResultResponse<Void> increaseAmount(@RequestBody PayRecordDTO payRecordDTO);
}