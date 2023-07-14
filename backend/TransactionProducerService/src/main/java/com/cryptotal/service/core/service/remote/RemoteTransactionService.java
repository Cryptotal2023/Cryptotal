package com.cryptotal.service.core.service.remote;

import com.cryptotal.service.core.domain.TransactionService.CryptoTradePairDataDetail;
import com.cryptotal.service.core.domain.response.GeneralResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "transaction")
public interface RemoteTransactionService {
    @GetMapping(value = "crypto-trade-pair-data/{accountId}")
    @Headers("Content-Type: application/json")
    GeneralResponse<List<CryptoTradePairDataDetail>> getCryptoTradePairDataByAccountId(@PathVariable Integer accountId);
}
