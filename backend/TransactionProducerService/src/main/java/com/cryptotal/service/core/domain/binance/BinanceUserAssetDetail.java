package com.cryptotal.service.core.domain.binance;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
public class BinanceUserAssetDetail implements java.io.Serializable {
    private String asset;
    private String free;
    private String locked;
    private String freeze;
    private String withdrawing;
    private String ipoable;
    private String btcValuation;

}