package com.cryptotal.service.core.domain;

import lombok.*;

@Getter
@Setter
@ToString
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
public class CryptoTradePairDataDetail implements java.io.Serializable {
    private Integer accountId;
    private String cryptoAsset;
    private String tradePairAsset;
    private Long latestTransactionId;
    private Long latestTradeId;
}