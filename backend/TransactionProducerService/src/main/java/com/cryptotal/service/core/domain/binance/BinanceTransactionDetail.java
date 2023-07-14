package com.cryptotal.service.core.domain.binance;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
public class BinanceTransactionDetail implements java.io.Serializable {
    private String symbol;
    private Long id;
    private Long orderId;
    private Long orderListId;
    BigDecimal price;
    BigDecimal qty;
    BigDecimal quoteQty;
    BigDecimal commission;
    private String commissionAsset;
    private String time;
    private Boolean isBuyer;
    private Boolean isMaker;
    private Boolean isBestMatch;
}