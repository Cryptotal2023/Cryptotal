package com.cryptotal.service.core.domain.request;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class AddTransactionRequest {
    @NotNull
    private Integer accountId;
    @NotNull
    private String cryptoAsset;
    @NotNull
    private String tradePairAsset;
    private Long tradeId;
    @NotNull
    private Long orderId;
    private Long orderListId;
    @NotNull
    private Double price;
    @NotNull
    private Double quantity;
    @NotNull
    private Double tradePairQuantity;
    private Double commission;
    private String commissionAsset;
    private Boolean isBuyer;
    private Boolean isMaker;
    private Boolean isBestMatch;
    private LocalDateTime time;
}
