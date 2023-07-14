package com.cryptotal.service.core.service;

import com.cryptotal.service.core.domain.AccountService.PutAccountSyncRequest;
import com.cryptotal.service.core.domain.AccountService.SyncStatus;
import com.cryptotal.service.core.domain.response.GeneralResponse;
import com.cryptotal.service.core.entity.AccountService.AccountDetail;
import com.cryptotal.service.core.exception.AccountNotFoundException;
import com.cryptotal.service.core.service.remote.RemoteAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private RemoteAccountService remoteAccountService;

    @Autowired
    public void setRemoteAccountService(RemoteAccountService remoteAccountService) {
        this.remoteAccountService = remoteAccountService;
    }

    public AccountDetail getAccount(Integer accountId) throws AccountNotFoundException {
        GeneralResponse<AccountDetail> response = remoteAccountService.getAccount(accountId);
        if (response.getStatus().isSuccess()) {
            AccountDetail account = response.getBody();
            return account;
        } else {
            throw new AccountNotFoundException();
        }
    }

    public void updateAccountBatchNumber(Integer accountId, Integer newBatchNumber) {
        updateSyncStatusAndBatchNumber(accountId, null, newBatchNumber);

    }

    public void updateSyncStatus(Integer accountId, SyncStatus syncStatus) {
        updateSyncStatusAndBatchNumber(accountId, syncStatus, null);

    }

    public void updateSyncStatusAndBatchNumber(Integer accountId, SyncStatus syncStatus, Integer newBatchNumber) {
        PutAccountSyncRequest request = PutAccountSyncRequest.builder()
                .batchNumber(newBatchNumber)
                .syncStatus(syncStatus)
                .build();
        Object response = remoteAccountService.updateSyncStatus(accountId, request);

    }

}
