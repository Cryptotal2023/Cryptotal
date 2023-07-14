package com.cryptotal.service.core.domain.AccountService;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class PutAccountSyncRequest {
    private SyncStatus syncStatus;

    private Integer batchNumber;
}
