package com.cryptotal.service.core.controller;

import com.cryptotal.service.core.connector.BinanceConnector;
import com.cryptotal.service.core.domain.AccountService.SyncStatus;
import com.cryptotal.service.core.domain.response.GeneralResponse;
import com.cryptotal.service.core.entity.AccountService.AccountDetail;
import com.cryptotal.service.core.exception.AccountNotFoundException;
import com.cryptotal.service.core.service.AccountService;
import com.cryptotal.service.core.service.TransactionProducerService;
import com.cryptotal.service.core.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class TransactionProducerController {

    AccountService accountService;

    TransactionService transactionService;
    TransactionProducerService transactionProducerService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Autowired
    public void setTransactionProducerService(TransactionProducerService transactionProducerService) {
        this.transactionProducerService = transactionProducerService;
    }

    @GetMapping
    public String testConnection(){
        return "Test TransactionProducer Service connection.";
    }

    @PostMapping("/{accountId}")
    public Object produceNewTransaction(@PathVariable Integer accountId) throws AccountNotFoundException {
        AccountDetail accountDetail = accountService.getAccount(accountId);
        if (accountDetail.getSyncStatus() == SyncStatus.INPROGRESS) {
            return GeneralResponse.buildGeneralResponse("Sync already in progress.", null);
        }
        BinanceConnector connector = new BinanceConnector(accountDetail);
        transactionProducerService.summaryAssetHistory(connector);
        return GeneralResponse.buildGeneralResponse("Success", null);
    }

    @GetMapping("/{accountId}/test")
    public Object testTransaction(@PathVariable Integer accountId) throws AccountNotFoundException {
        //AccountDetail accountDetail = accountService.getAccount(accountId);

        //accountService.updateAccountBatchNumber(accountId, 3);
        //accountService.updateSyncStatus(accountId, SyncStatus.INPROGRESS);
        //accountService.updateSyncStatusAndBatchNumber(accountId, SyncStatus.INPROGRESS, 3);
        //BinanceConnector connector = new BinanceConnector(accountDetail);
        //System.out.println(connector.getDefaultCryptoTradePairDataDetailMap());
        System.out.println(transactionService.getCryptoTradePairDataDetailListByAccountId(accountId));
        return GeneralResponse.buildGeneralResponse("Success", null);
    }


}
