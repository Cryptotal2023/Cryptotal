package com.cryptotal.service.core.domain.binance;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BinanceExchangeInfoDetail implements java.io.Serializable {
    List<BinanceSymbol> symbols;
}