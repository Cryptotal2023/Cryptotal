package com.cryptotal.service.core.entity.AccountService;
import com.cryptotal.service.core.domain.AccountService.SyncStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetail implements java.io.Serializable {
    private Integer id;
    private String accountName;
    private String memo;
    private Integer userId;
    private String accountType;
    private String platformName;
    private LocalDateTime createAt;
    private LocalDateTime lastModificationAt;
    private LocalDateTime lastSyncAt;
    private SyncStatus syncStatus;
    private boolean activeFlag;
    private String apiKey;
    private String secretKey;
}