package com.cryptotal.service.core.domain.TransactionService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoTradePairDataDetail implements java.io.Serializable {
    private Integer accountId;
    private String cryptoAsset;
    private String tradePairAsset;
    private Long latestTransactionId;
    private Long tradeId;
}