package com.cryptotal.service.core.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Transaction")
public class Transaction implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(nullable = false)
    private Integer accountId;
    @Column(nullable = false)
    private String cryptoAsset;
    @Column(nullable = false)
    private String tradePairAsset;
    @Column
    private Long tradeId;
    @Column(nullable = false)
    private Long orderId;
    @Column
    private Long orderListId;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Double quantity;
    @Column(nullable = false)
    private Double tradePairQuantity;
    @Column
    private Double commission;
    @Column
    private String commissionAsset;
    @Column
    private Boolean isBuyer;
    @Column
    private Boolean isMaker;
    @Column
    private Boolean isBestMatch;
    @Column
    private LocalDateTime time;
    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime createAt = LocalDateTime.now();
    @Column
    private String memo;
}