package com.cryptotal.service.core.service.remote;

import com.cryptotal.service.core.domain.AccountService.PutAccountSyncRequest;
import com.cryptotal.service.core.domain.AccountService.SyncStatus;
import com.cryptotal.service.core.domain.response.GeneralResponse;
import com.cryptotal.service.core.entity.AccountService.AccountDetail;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "account-core")
public interface RemoteAccountService {
    @GetMapping(value = "account-core/{accountId}")
    @Headers("Content-Type: application/json")
    public GeneralResponse<AccountDetail> getAccount(@PathVariable Integer accountId);

    @PutMapping(value ="account-core/{accountId}/sync")
    @Headers("Content-Type: application/json")
    public Object updateSyncStatus(@PathVariable Integer accountId, @RequestBody PutAccountSyncRequest request);
}
