package com.cryptotal.service.core.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CryptoTradePairData")
public class CryptoTradePairData implements java.io.Serializable {
    @Id
    private Integer accountId;
    @Id
    private String cryptoAsset;
    @Id
    private String tradePairAsset;
    @Column
    private Long latestTransactionId;
}