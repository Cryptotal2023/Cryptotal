package com.cryptotal.service.core.domain.messagequeue;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetail implements java.io.Serializable {
    private Integer accountId;
    private String cryptoAsset;
    private String tradePairAsset;
    private Long tradeId;
    private Long orderId;
    private Long orderListId;
    private Double price;
    private Double quantity;
    private Double tradePairQuantity;
    private Double commission;
    private String commissionAsset;
    private Boolean isBuyer;
    private Boolean isMaker;
    private Boolean isBestMatch;
    private LocalDateTime time;
    private LocalDateTime createdAt;
    private String memo;
}