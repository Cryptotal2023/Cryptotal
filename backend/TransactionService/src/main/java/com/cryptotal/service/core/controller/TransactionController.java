package com.cryptotal.service.core.controller;

import com.cryptotal.service.core.domain.request.AddTransactionRequest;
import com.cryptotal.service.core.domain.response.GeneralResponse;
import com.cryptotal.service.core.entity.Transaction;
import com.cryptotal.service.core.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private TransactionService transactionService;
    @Autowired
    private TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public Object getAllTransactions(){
        return GeneralResponse.buildGeneralResponse(null, transactionService.getAllTransactions());
    }

    @GetMapping("/{transactionId}")
    public Object getTransactionById(@PathVariable Long transactionId){
        return GeneralResponse.buildGeneralResponse(null, transactionService.getTransactionById(transactionId));
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
