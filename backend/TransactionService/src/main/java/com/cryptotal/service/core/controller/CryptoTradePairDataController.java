package com.cryptotal.service.core.controller;

import com.cryptotal.service.core.domain.CryptoTradePairDataDetail;
import com.cryptotal.service.core.domain.request.AddTransactionRequest;
import com.cryptotal.service.core.domain.response.GeneralResponse;
import com.cryptotal.service.core.entity.CryptoTradePairData;
import com.cryptotal.service.core.entity.Transaction;
import com.cryptotal.service.core.service.CryptoTradePairDataService;
import com.cryptotal.service.core.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/crypto-trade-pair-data")
public class CryptoTradePairDataController {

    private CryptoTradePairDataService cryptoTradePairDataService;
    private TransactionService transactionService;
    @Autowired
    private CryptoTradePairDataController(CryptoTradePairDataService cryptoTradePairDataService, TransactionService transactionService) {
        this.cryptoTradePairDataService = cryptoTradePairDataService;
        this.transactionService = transactionService;
    }

    @GetMapping
    public Object getAllCryptoTradePairData(){
        return GeneralResponse.buildGeneralResponse(null, cryptoTradePairDataService.getAllCryptoTradePairData());
    }

    @GetMapping("/{accountId}")
    public Object getCryptoTradePairDataByAccountId(@PathVariable Integer accountId){

        List<CryptoTradePairDataDetail> cryptoTradePairDataDetailList = new ArrayList<>();

        for (CryptoTradePairData cryptoTradePairData : cryptoTradePairDataService.getCryptoTradePairDataByAccountId(accountId)) {
            CryptoTradePairDataDetail cryptoTradePairDataDetail = CryptoTradePairDataDetail.builder()
                    .accountId(cryptoTradePairData.getAccountId())
                    .cryptoAsset(cryptoTradePairData.getCryptoAsset())
                    .tradePairAsset(cryptoTradePairData.getTradePairAsset())
                    .latestTransactionId(cryptoTradePairData.getLatestTransactionId())
                    .latestTradeId(transactionService.getTransactionById(cryptoTradePairData.getLatestTransactionId()).getTradeId())
                    .build();
            cryptoTradePairDataDetailList.add(cryptoTradePairDataDetail);
        }

        return GeneralResponse.buildGeneralResponse(null, cryptoTradePairDataDetailList);
    }

    @PostMapping
    public Object addTransaction(@RequestBody AddTransactionRequest request) {

        Transaction transaction = Transaction.builder()
                .accountId(request.getAccountId())
                .cryptoAsset(request.getCryptoAsset())
                .tradePairAsset(request.getTradePairAsset())
                .tradeId(request.getTradeId())
                .orderId(request.getOrderId())
                .orderListId(request.getOrderListId())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .tradePairQuantity(request.getTradePairQuantity())
                .commission(request.getCommission())
                .commissionAsset(request.getCommissionAsset())
                .time(request.getTime())
                .createAt(LocalDateTime.now())
                .isBuyer(request.getIsBuyer())
                .isMaker(request.getIsMaker())
                .isBestMatch(request.getIsBestMatch())
                .build();

        return GeneralResponse.buildGeneralResponse(null, transactionService.addNewTransaction(transaction));
    }
}
