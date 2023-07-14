package com.cryptotal.service.core.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="AccountCryptoData")
public class AccountCryptoData implements java.io.Serializable {
    @Id
    private Integer accountId;
    @Id
    private String cryptoAsset;
    @Column
    private Double cost;
    @Column
    private Double quantity;
    @Column(nullable = false)
    @Builder.Default
    private Integer buyCount = 0;
    @Column(nullable = false)
    @Builder.Default
    private Integer sellCount = 0;
    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime createAt = LocalDateTime.now();
    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime lastModificationAt = LocalDateTime.now();
    @Column(nullable = false)
    @Builder.Default
    private boolean updateFlag = true;
    @Column
    private String memo;
}